package com.yijiang.mall.entity.vo;

import java.io.Serializable;

/**
 * @ClassName MemberLoginVO
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/29 10:16
 * @Version 1.0
 */
public class MemberLoginVO implements Serializable {

    public static final Long serialVersionUID = 1L;

    private String username;

    private String email;

    private Integer id;

    @Override
    public String toString() {
        return "MemberLoginVO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MemberLoginVO() {
    }

    public MemberLoginVO(String username, String email, Integer id) {
        this.username = username;
        this.email = email;
        this.id = id;
    }
}
