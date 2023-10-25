package com.okay.appplatform;

import com.okay.appplatform.common.constant.Constant;
import com.okay.appplatform.domain.bean.Menu;
import com.okay.appplatform.domain.user.UserResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class LoginInterceptor extends AuthorizationFilter {


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //请求的url
        String requestURL = getPathWithinApplication(request);
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        //没有登录跳转登录页面
        if (!subject.isAuthenticated()) {
            return false;
        }

        UserResponse user = (UserResponse) session.getAttribute(Constant.ONLINE_USER);
        List<Menu> menuMaps = user.getRoleResponse().getMenus();
        boolean hasPermission = false;
        for (Menu menu : menuMaps) {
            if (menu.getMenuUrl() != null && Pattern.matches(menu.getMenuUrl(), requestURL)) {
                hasPermission = true;
                break;
            }
        }
        if (hasPermission) {
            return true;
        } else {
            UnauthorizedException ex = new UnauthorizedException("当前用户没有访问路径" + requestURL + "的权限");
            subject.getSession().setAttribute("ex", ex);
            return false;

        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = getSubject(request, response);

        if (subject.getPrincipal() == null) {
            // 如果未登录，保存当前页面，重定向到登录页面
            saveRequestAndRedirectToLogin(request, response);
        } else {
            //匿名访问地址
            String unauthorizedUrl = getUnauthorizedUrl();
            if (StringUtils.hasText(unauthorizedUrl)) {
                //如果匿名访问地址存在，则跳转去匿名访问地址
                WebUtils.issueRedirect(request, response, unauthorizedUrl);
            }
        }
        return false;
    }

}

