package com.waken.dorm.controller.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waken.dorm.common.annotation.Limit;
import com.waken.dorm.common.authentication.JWTToken;
import com.waken.dorm.common.authentication.JWTUtil;
import com.waken.dorm.common.base.ActiveUser;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.constant.CacheConstant;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.log.SysLog;
import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.form.user.QueryUserForm;
import com.waken.dorm.common.properties.DormProperties;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.controller.base.BaseController;
import com.waken.dorm.service.cache.CacheService;
import com.waken.dorm.service.cache.RedisService;
import com.waken.dorm.service.log.LogService;
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
@Api(value = "用户登录相关接口", description = "用户登录相关接口(AiShu)")
@Slf4j
@RestController
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
    @Autowired
    LogService logService;

    /**
     * 用户登录
     */
    @PostMapping("login")
    @ApiOperation(value = "login（用户登录接口）", notes = "用户登录接口 " +
            "<br/>请求参数JSON示例:{\"userName\":\"xiaozhu\",\"password\":\"xiaozhu\"}" +
            "<br/>必填参数：userName（用户名） " +
            "<br/>必填参数：password（密码） " +
            "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    @Limit(key = "login", period = 60, count = 20, name = "用户登录接口", prefix = "limit")
    public AjaxResponse login(@RequestBody QueryUserForm queryUserForm, HttpServletRequest request) {
        log.info("开始调用登陆接口：" + queryUserForm.getUserName());
        if (StringUtils.isEmpty(queryUserForm.getUserName()) || StringUtils.isEmpty(queryUserForm.getPassword())) {
            return AjaxResponse.error("用户名或密码为空！");
        }
        String username = queryUserForm.getUserName();

        User user = this.userService.queryUserInfo(username);
        String password = PasswordEncode.shiroEncode(user.getUserId(), queryUserForm.getPassword());

        if (user == null) {
            return AjaxResponse.error("用户名错误！");
        } else if (!StringUtils.equals(user.getPassword(), password)) {
            return AjaxResponse.error("密码错误！");
        } else if (CodeEnum.DISABLE.getCode().equals(user.getStatus())) {
            return AjaxResponse.error("用户已被禁用,请联系管理员!");
        } else if (CodeEnum.DELETE.getCode().equals(user.getStatus())) {
            return AjaxResponse.error("用户已失效，请联系管理员！");
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
            return AjaxResponse.error(ResultEnum.SERVER_ERROR);
        }
        this.userService.updateLoginTime(user);
        return AjaxResponse.success(this.getUserMapAndCacheUser(jwtToken, user));
    }

    @RequiresPermissions("user:online")
    @GetMapping("online")
    @ApiOperation(value = "online（查看在线用户接口）", notes = "查看在线用户接口")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ActiveUser.class)
    })
    public AjaxResponse userOnline(String username) throws Exception {
        String now = DateUtils.formatFullTime(LocalDateTime.now());
        Set<String> userOnlineStringSet = redisService.zrangeByScore(CacheConstant.ACTIVE_USERS_ZSET_PREFIX, now, "+inf");
        List<ActiveUser> activeUsers = new ArrayList<>();
        for (String userOnlineString : userOnlineStringSet) {
            ActiveUser activeUser = mapper.readValue(userOnlineString, ActiveUser.class);
            activeUser.setToken(null);
            if (StringUtils.isNotBlank(username)) {
                if (StringUtils.equalsIgnoreCase(username, activeUser.getUsername())){
                    activeUsers.add(activeUser);
                }
            } else {
                activeUsers.add(activeUser);
            }
        }
        return AjaxResponse.success(activeUsers);
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
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse logout(@NotBlank(message = "{required}") @PathVariable String id) throws Exception {
        this.kickOut(id);
        return AjaxResponse.success();
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
            @ApiResponse(code = 200, message = "success", response = AjaxResponse.class)
    })
    public AjaxResponse kickOut(@NotBlank(message = "{required}") @PathVariable String activeUserId) throws Exception {
        String now = DateUtils.formatFullTime(LocalDateTime.now());
        Set<String> userOnlineStringSet = redisService.zrangeByScore(CacheConstant.ACTIVE_USERS_ZSET_PREFIX, now, "+inf");
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
            redisService.zrem(CacheConstant.ACTIVE_USERS_ZSET_PREFIX, kickoutUserString);
            // 删除对应的 token缓存
            redisService.del(CacheConstant.TOKEN_CACHE_PREFIX + kickoutUser.getToken() + "." + kickoutUser.getIp());
        }
        return AjaxResponse.success();
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
        //保存登录日志
        SysLog sysLog = new SysLog();
        sysLog.setUserId(user.getUserId());
        sysLog.setIp(ip);
        sysLog.setLocation(AddressUtils.getCityInfo(ip));
        logService.addLoginLog(sysLog);
        // 构建在线用户
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUsername(user.getUserName());
        activeUser.setIp(ip);
        activeUser.setToken(token.getToken());
        activeUser.setLoginAddress(AddressUtils.getCityInfo(ip));

        // zset 存储登录用户，score 为过期时间戳
        this.redisService.zadd(CacheConstant.ACTIVE_USERS_ZSET_PREFIX, Double.valueOf(token.getExipreAt()), mapper.writeValueAsString(activeUser));
        // redis 中存储这个加密 token，key = 前缀 + 加密 token + .ip
        this.redisService.set(CacheConstant.TOKEN_CACHE_PREFIX + token.getToken() + Constant.SPOT + ip, token.getToken(), properties.getProperties().getJwtTimeOut() * 1000);
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
