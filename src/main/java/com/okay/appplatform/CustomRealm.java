package com.okay.appplatform;

import com.okay.appplatform.common.constant.Constant;
import com.okay.appplatform.domain.bean.User;
import com.okay.appplatform.domain.user.UserResponse;
import com.okay.appplatform.service.user.UserService;
import com.okay.appplatform.tools.AppMd5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author XieYangYang
 * @date 2019/11/20 16:55
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Session session = SecurityUtils.getSubject().getSession();
        UserResponse user = (UserResponse) session.getAttribute(Constant.ONLINE_USER);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> perm = new HashSet();
        perm.add(user.getRoleResponse().getRoleCode());
        if (null != user) {
            authorizationInfo.setStringPermissions(perm);
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken authcToken = (UsernamePasswordToken) authenticationToken;
        UserResponse userResponse = null;
        try {
            User userParam = new User();
            userParam.setIsDelete("0");
            userParam.setUserName(authcToken.getUsername());
            userParam.setUserPassword(AppMd5Util.MD5toLower(String.valueOf(authcToken.getPassword())));
            userResponse = userService.findByUserRes(userParam);
        } catch (Exception e) {
            log.info("身份认证发生异常", e);
            throw new AuthenticationException("身份认证发生异常");
        }

        if (null == userResponse) {
            log.info("身份认证失败，用户名密码不匹配");
            throw new DisabledAccountException("身份认证失败，用户名密码不匹配");
        }
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(Constant.ONLINE_USER, userResponse);
        session.setAttribute(Constant.ONLINE_ROLE, userResponse.getRoleResponse());

        return new SimpleAuthenticationInfo(authcToken.getUsername(), authcToken.getPassword(), getName());
    }
}
