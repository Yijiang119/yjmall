package com.yijiang.mall.entity.vo;

import java.util.List;

/**
 * @ClassName PortalTypeVO
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/30 14:55
 * @Version 1.0
 */
public class PortalTypeVO {

    private Integer id;
    private String name;
    private String remark;

    private List<PortalProjectVO> portalProjectVOList;

    @Override
    public String toString() {
        return "PortalTypeVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", portalProjectVOList=" + portalProjectVOList +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<PortalProjectVO> getPortalProjectVOList() {
        return portalProjectVOList;
    }

    public void setPortalProjectVOList(List<PortalProjectVO> portalProjectVOList) {
        this.portalProjectVOList = portalProjectVOList;
    }

    public PortalTypeVO(Integer id, String name, String remark, List<PortalProjectVO> portalProjectVOList) {
        this.id = id;
        this.name = name;
        this.remark = remark;
        this.portalProjectVOList = portalProjectVOList;
    }

    public PortalTypeVO() {
    }
}
