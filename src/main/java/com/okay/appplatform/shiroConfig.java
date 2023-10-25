package com.okay.appplatform;


import com.okay.appplatform.tools.Base64Utils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author XieYangYang
 * @date 2019/11/20 16:58
 */
@Configuration
public class shiroConfig {


    //不加这个注解不生效
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    //将自己的验证方式加入容器
    @Bean
    public CustomRealm myShiroRealm() {
        CustomRealm customRealm = new CustomRealm();
        return customRealm;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        // 注入记住我管理器;
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * cookie对象;
     *
     * @return
     */
    public SimpleCookie rememberMeCookie() {
        // 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // <!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }

    /**
     * cookie管理对象;记住我功能
     *
     * @return
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        try {
            cookieRememberMeManager.setCipherKey(Base64Utils.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookieRememberMeManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //访问权限配置
        filtersMap.put("loginUrl", new LoginFormAuthenticationFilter());
        filtersMap.put("reqUrl", new LoginInterceptor());

        shiroFilterFactoryBean.setFilters(filtersMap);
        Map<String, String> map = new LinkedHashMap<>();
        //登出
        map.put("/logout", "logout");
        map.put("/404", "anon");
        map.put("/405", "anon");
        map.put("/css/**", "anon");
        map.put("/image/**", "anon");
        map.put("/file/**", "anon");
        map.put("/js/**", "anon");
        map.put("/", "anon");
        map.put("/favicon.ico", "anon");
        shiroFilterFactoryBean.setLoginUrl("/login");
        map.put("/login", "loginUrl");

        //兼容之前
        //排除路径

        map.put("/api/padautocase/run","anon");

        map.put("/performance/pad/getLastRunId","anon");
        map.put("/performance/pad/insert","anon");

        map.put("/uiPad/report/lastRunId","anon");
        map.put("/uiPad/report/insertFile","anon");
        map.put("/uiPad/report/serialnolist/insert","anon");
        map.put("/uiPad/report/caseList/insert","anon");
        map.put("/uiPad/report/update","anon");

        // job
        map.put("/job/refreshTrigger","anon");
        //对所有用户认证
        map.put("/**", "reqUrl");
        //错误页面，无权限跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/405");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/api/home");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
