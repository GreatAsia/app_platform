package com.okay.appplatform;


import org.springframework.boot.web.server.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ErrorPageConfig {
    /**
     * SpringBoot2.0以上版本WebServerFactoryCustomizer代替之前版本的EmbeddedWebServerFactoryCustomizerAutoConfiguration
     *
     * @return
     */

    //@Bean必须加上
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
//        //第一种：java7 常规写法
//        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
//            @Override
//            public void customize(ConfigurableWebServerFactory factory) {
//                ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
//                factory.addErrorPages(errorPage404);
//            }
//        };
        //第二种写法：java8 lambda写法
        return (factory -> {
            /*ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404");*/
            /*factory.addErrorPages(errorPage404);*/
        });
    }
}