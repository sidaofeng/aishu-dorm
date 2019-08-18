package com.waken.dorm.controller.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waken.dorm.common.annotation.Log;
import com.waken.dorm.common.authentication.JWTToken;
import com.waken.dorm.common.authentication.JWTUtil;
import com.waken.dorm.common.base.ActiveUser;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.user.QueryUserForm;
import com.waken.dorm.common.properties.DormProperties;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.cache.CacheService;
import com.waken.dorm.service.cache.RedisService;
import com.waken.dorm.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @Description 用户登录相关接口
 * @Author zhaoRong
 * @Date 2019/8/14 23:57
 **/
@RestController
@Api(value = "用户登录相关接口", description = "用户登录相关接口(AiShu)")
@Slf4j
public class LoginController extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    DormProperties properties;
    @Autowired
    RedisService redisService;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    CacheService cacheService;

    /**
     * 用户登录
     */
    @Log("用户登录")
    @PostMapping("login")
    @ApiOperation(value = "login（用户登录接口）", notes = "用户登录接口")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public ResultView login(@RequestBody QueryUserForm queryUserForm, HttpServletRequest request) {
        log.info("开始调用登陆接口：" + queryUserForm.getUserName());
        if (StringUtils.isEmpty(queryUserForm.getUserName()) || StringUtils.isEmpty(queryUserForm.getPassword())) {
            return ResultUtil.errorByMsg("用户名或密码为空！");
        }
        String username = queryUserForm.getUserName();
        String password = PasswordEncode.shiroEncode(username, queryUserForm.getPassword());
        User user = this.userService.queryUserInfo(username);
        if (user == null) {
            return ResultUtil.errorByMsg("用户名错误！");
        } else if (!StringUtils.equals(user.getPassword(), password)) {
            return ResultUtil.errorByMsg("密码错误！");
        } else if (CodeEnum.DISABLE.getCode() == user.getStatus()) {
            return ResultUtil.errorByMsg("用户已被禁用,请联系管理员!");
        } else if (CodeEnum.DELETE.getCode() == user.getStatus()) {
            return ResultUtil.errorByMsg("用户已失效，请联系管理员！");
        }

        String token = TokenUtils.encryptToken(JWTUtil.sign(username, password));

        log.info("获取到的用户token为：" + token);
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(properties.getProperties().getJwtTimeOut());

        String expireTimeStr = DateUtils.formatFullTime(expireTime);

        JWTToken jwtToken = new JWTToken(token, expireTimeStr);
        try {
            String activeUserId = this.saveTokenToRedis(user, jwtToken, request);
            user.setActiveUserId(activeUserId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(ResultEnum.SERVER_ERROR);
        }
        this.userService.updateLoginTime(user);
        return ResultUtil.success(this.getUserMapAndCacheUser(jwtToken, user));
    }

    @RequiresPermissions("user:online")
    @GetMapping("online")
    @ApiOperation(value = "online（查看在线用户接口）", notes = "查看在线用户接口")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ActiveUser.class)
    })
    public ResultView userOnline(String username) throws Exception {
        String now = DateUtils.formatFullTime(LocalDateTime.now());
        Set<String> userOnlineStringSet = redisService.zrangeByScore(Constant.ACTIVE_USERS_ZSET_PREFIX, now, "+inf");
        List<ActiveUser> activeUsers = new ArrayList<>();
        for (String userOnlineString : userOnlineStringSet) {
            ActiveUser activeUser = mapper.readValue(userOnlineString, ActiveUser.class);
            activeUser.setToken(null);
            if (StringUtils.isNotBlank(username)) {
                if (StringUtils.equalsIgnoreCase(username, activeUser.getUsername()))
                    activeUsers.add(activeUser);
            } else {
                activeUsers.add(activeUser);
            }
        }
        return ResultUtil.success(activeUsers);
    }

    /**
     * 注销登录
     *
     * @param id
     * @throws Exception
     */
    @GetMapping("logout/{id}")
    @ApiOperation(value = "logout（注销登录接口）", notes = "注销登录接口")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public void logout(@NotBlank(message = "{required}") @PathVariable String id) throws Exception {
        this.kickOut(id);
    }

    /**
     * 下线用户
     *
     * @param activeUserId 在线用户唯一id
     * @throws Exception
     */
    @DeleteMapping("kickout/{activeUserId}")
    @RequiresPermissions("user:kickout")
    @ApiOperation(value = "kickout（下线用户接口）", notes = "下线用户接口")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ResultView.class)
    })
    public void kickOut(@NotBlank(message = "{required}") @PathVariable String activeUserId) throws Exception {
        String now = DateUtils.formatFullTime(LocalDateTime.now());
        Set<String> userOnlineStringSet = redisService.zrangeByScore(Constant.ACTIVE_USERS_ZSET_PREFIX, now, "+inf");
        ActiveUser kickoutUser = null;
        String kickoutUserString = "";
        for (String userOnlineString : userOnlineStringSet) {
            ActiveUser activeUser = mapper.readValue(userOnlineString, ActiveUser.class);
            if (org.apache.commons.lang3.StringUtils.equals(activeUser.getId(), activeUserId)) {
                kickoutUser = activeUser;
                kickoutUserString = userOnlineString;
            }
        }
        if (kickoutUser != null && org.apache.commons.lang3.StringUtils.isNotBlank(kickoutUserString)) {
            // 删除 zset中的记录
            redisService.zrem(Constant.ACTIVE_USERS_ZSET_PREFIX, kickoutUserString);
            // 删除对应的 token缓存
            redisService.del(Constant.TOKEN_CACHE_PREFIX + kickoutUser.getToken() + "." + kickoutUser.getIp());
        }
    }

    /**
     * 保存token信息,并返回生成的在线用户id
     *
     * @param user
     * @param token
     * @param request
     * @throws Exception
     */
    private String saveTokenToRedis(User user, JWTToken token, HttpServletRequest request) throws Exception {
        String ip = IPUtils.getIpAddr(request);
        // 构建在线用户
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUsername(user.getUserName());
        activeUser.setIp(ip);
        activeUser.setToken(token.getToken());
        activeUser.setLoginAddress(AddressUtils.getCityInfo(ip));

        // zset 存储登录用户，score 为过期时间戳
        this.redisService.zadd(Constant.ACTIVE_USERS_ZSET_PREFIX, Double.valueOf(token.getExipreAt()), mapper.writeValueAsString(activeUser));
        // redis 中存储这个加密 token，key = 前缀 + 加密 token + .ip
        this.redisService.set(Constant.TOKEN_CACHE_PREFIX + token.getToken() + Constant.SPOT + ip, token.getToken(), properties.getProperties().getJwtTimeOut() * 1000);
        return activeUser.getId();
    }

    /**
     * 封装返回给前端的视图信息，并将用户信息、权限、角色等信息存储到redis中
     *
     * @param token
     * @param user
     * @return
     */
    private Map<String, Object> getUserMapAndCacheUser(JWTToken token, User user) {
        String username = user.getUserName();
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        try {
            //登录时将用户信息添加到缓存，用户信息修改后需要重新登录才可获得最新信息
            this.cacheService.saveUser(user);
            roles = this.cacheService.saveRoles(username);
            permissions = this.cacheService.savePermissions(username);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("缓存用户信息错误！");
        }
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("token", token.getToken());

        userInfo.put("exipreTime", token.getExipreAt());

        userInfo.put("roles", roles);

        userInfo.put("permissions", permissions);

        user.setPassword("no password");
        userInfo.put("user", user);
        return userInfo;
    }
}
