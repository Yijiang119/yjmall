package com.yijiang.mall.mvc.config;

import com.yijiang.mall.constant.MallConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName WebAppSecurityConfig
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/24 15:50
 * @Version 1.0
 */
// 注意！这个类一定要放在自动扫描的包下，否则所有配置都不会生效！
// 将当前类标记为配置类
@Configuration
// 启用 Web 环境下权限控制功能
@EnableWebSecurity
// 启用全局方法权限控制功能，并且设置 prePostEnabled = true。保证@PreAuthority、@PostAuthority、@PreFilter、@PostFilter 生效
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        // 临时使用内存版登录的模式测试代码
//        builder.inMemoryAuthentication().withUser("tom").password("123123").roles("ADMIN");

        // 正式功能中使用基于数据库的认证
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .authorizeRequests()                                    // 对请求进行授权
                .antMatchers("/index.jsp")
                .permitAll()
                .antMatchers("/admin/to/login/page.html")  // 针对登录页进行设置
                .permitAll()                                            // 无条件访问
                .antMatchers("/bootstrap/**")              // 针对静态资源进行设置，无条件访问
                .permitAll()
                .antMatchers("/crowd/**")
                .permitAll()
                .antMatchers("/css/**")
                .permitAll()
                .antMatchers("/fonts/**")
                .permitAll()
                .antMatchers("/img/**")
                .permitAll()
                .antMatchers("/jquery/**")
                .permitAll()
                .antMatchers("/layer/**")
                .permitAll()
                .antMatchers("/script/**")
                .permitAll()
                .antMatchers("/ztree/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        request.setAttribute("exception", new Exception(MallConstant.MESSAGE_ACCESS_DENIED));
                        request.getRequestDispatcher("/WEB-INF/system-error.jsp").forward(request, response);
                    }
                })
                .and()
                .csrf()                                                 // 防跨站请求伪造功能
                .disable()                                              // 禁用
                .formLogin()                                            // 开启表单登录的功能
                .loginPage("/admin/to/login/page.html")                 // 指定登录页面
                .loginProcessingUrl("/security/do/login.html")          // 指定处理登录请求的地址
                .defaultSuccessUrl("/admin/to/main/page.html")          // 指定登录成功后前往的地址
                .usernameParameter("loginAcct")                         // 账号的请求参数名称
                .passwordParameter("userPswd")                          // 密码的请求参数名称
                .and()
                .logout()                                               // 开启退出登录功能
                .logoutUrl("/security/do/logout.html")                  // 指定退出登录地址
                .logoutSuccessUrl("/admin/to/login/page.html")          // 指定退出成功以后前往的地址
        ;

    }


}
