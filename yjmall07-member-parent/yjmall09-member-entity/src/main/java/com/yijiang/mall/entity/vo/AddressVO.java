package com.yijiang.mall.entity.vo;

import java.io.Serializable;

/**
 * @ClassName AddressVO
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/30 19:12
 * @Version 1.0
 */
public class AddressVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String receiveName;

    private String phoneNum;

    private String address;

    private Integer memberId;

    @Override
    public String toString() {
        return "AddressVO{" +
                "id=" + id +
                ", receiveName='" + receiveName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", address='" + address + '\'' +
                ", memberId=" + memberId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public AddressVO() {
    }

    public AddressVO(Integer id, String receiveName, String phoneNum, String address, Integer memberId) {
        this.id = id;
        this.receiveName = receiveName;
        this.phoneNum = phoneNum;
        this.address = address;
        this.memberId = memberId;
    }
}
