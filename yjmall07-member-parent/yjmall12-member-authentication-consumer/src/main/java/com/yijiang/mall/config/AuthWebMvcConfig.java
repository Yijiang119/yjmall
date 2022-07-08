package com.yijiang.mall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName AuthWebMvcConfig
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/28 18:43
 * @Version 1.0
 */
@Configuration
public class AuthWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/auth/member/to/reg/page.html").setViewName("member-reg");
        registry.addViewController("/auth/member/to/login/page.html").setViewName("member-login");
        registry.addViewController("/auth/member/to/center/page.html").setViewName("member-center");
        registry.addViewController("/member/my/cart").setViewName("member-cart");
        registry.addViewController("/to/sorry").setViewName("sorry");
    }
}
