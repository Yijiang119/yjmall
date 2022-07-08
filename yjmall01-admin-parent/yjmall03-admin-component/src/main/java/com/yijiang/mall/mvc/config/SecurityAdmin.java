package com.yijiang.mall.mvc.config;

import com.yijiang.mall.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * @ClassName SecurityAdmin
 * @Description 考虑到 User 对象中仅仅包含账号和密码，为了能够获取到原始的 Admin 对象，
 * 专门创建 这个类对 User 类进行扩展
 * @Author 姜泽昊
 * @Date 2022/3/24 18:32
 * @Version 1.0
 */
public class SecurityAdmin extends User {
    private static final long serialVersionUID = 1L;
    // 原始的 Admin 对象，包含 Admin 对象的全部属性
    private Admin originalAdmin;

    public SecurityAdmin(
            // 传入原始的 Admin 对象
            Admin originalAdmin,
            // 创建角色、权限信息的集合
            List<GrantedAuthority> authorities) {
        // 调用父类构造器
        super(originalAdmin.getLoginAcct(), originalAdmin.getUserPswd(), authorities);
        // 给本类的 this.originalAdmin 赋值
        this.originalAdmin = originalAdmin;
        // 将原始 Admin 对象中的密码擦除
        this.originalAdmin.setUserPswd(null);
    }

    // 对外提供的获取原始 Admin 对象的 getXxx()方法
    public Admin getOriginalAdmin() {
        return originalAdmin;
    }
}

