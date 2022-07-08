package com.yijiang.mall.mvc.interceptor;

import com.yijiang.mall.constant.MallConstant;
import com.yijiang.mall.entity.Admin;
import com.yijiang.mall.exception.AccessForbiddenException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginInterceptor
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/21 14:44
 * @Version 1.0
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        // 1.通过 request 对象获取 Session 对象
        HttpSession session = httpServletRequest.getSession();
        // 2.尝试从 Session 域中获取 Admin 对象
        Admin admin = (Admin) session.getAttribute(MallConstant.ATTR_NAME_LOGIN_ADMIN);
        // 3.判断 admin 对象是否为空
        if(admin == null) {
            // 4.抛出异常
            throw new AccessForbiddenException(MallConstant.MESSAGE_ACCESS_FORBIDEN);
        }
        // 5.如果 Admin 对象不为 null，则返回 true 放行
        return true;
    }

}
