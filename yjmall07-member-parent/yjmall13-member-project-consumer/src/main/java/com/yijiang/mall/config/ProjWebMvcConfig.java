package com.yijiang.mall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName ProjWebMvcConfig
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/29 20:51
 * @Version 1.0
 */
@Configuration
public class ProjWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // view-controller 是在 project-consumer 内部定义的，所以这里是一个不经过 Zuul访问的地址，所以这个路径前面不加路由规则中定义的前缀：“/project”
        registry.addViewController("/launch/project/page").setViewName("project-launch");
        registry.addViewController("/return/info/page.html").setViewName("project-return");
        registry.addViewController("/create/confirm/page.html").setViewName("project-confirm");
        registry.addViewController("/create/success.html").setViewName("project-success");
        registry.addViewController("/to/sorry").setViewName("sorry");
    }
}
