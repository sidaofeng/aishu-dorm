package com.waken.dorm.shiro;

import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.form.resource.ResourceMenuForm;
import com.waken.dorm.common.form.user.QueryUserForm;
import com.waken.dorm.common.utils.ShiroUtils;
import com.waken.dorm.common.view.resource.ResourceMenuView;
import com.waken.dorm.service.resource.ResourceService;
import com.waken.dorm.service.user.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName DormShiroRealm
 * @Description 自定义realm
 * @Author zhaoRong
 * @Date 2019/3/21 19:45
 **/
public class DormShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.用户名或者手机号都可以登录
        String username = (String)token.getPrincipal();
        QueryUserForm userForm = new QueryUserForm();
        userForm.setUserName(username);
        User user = userService.queryUserInfo(userForm);
        if(user==null) throw new UnknownAccountException();
//        if (CodeEnum.LOCKED==user.getStatus()) {
//            throw new LockedAccountException(); // 帐号锁定
//        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户
                user.getPassword(), //密码
                ByteSource.Util.bytes(username),
                getName()  //realm name
        );

        return authenticationInfo;
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userId = ShiroUtils.getUserId();
        ResourceMenuForm resourceMenuForm = new ResourceMenuForm();
        resourceMenuForm.setUserId(userId);
        List<ResourceMenuView> resourcesList = resourceService.listMenuResources(resourceMenuForm);
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（Resources）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for(ResourceMenuView resources: resourcesList){
            info.addStringPermission(resources.getResourceUrl());
        }
        return info;
    }
}
