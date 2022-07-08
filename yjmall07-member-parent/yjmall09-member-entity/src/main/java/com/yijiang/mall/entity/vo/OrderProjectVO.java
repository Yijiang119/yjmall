package com.yijiang.mall.entity.vo;

import java.io.Serializable;

/**
 * @ClassName OrderProjectVO
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/30 18:56
 * @Version 1.0
 */
public class OrderProjectVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String projectName;

    private String launchName;

    private String returnContent;

    private Integer returnCount;

    private Integer supportPrice;

    private Integer freight;

    private Integer orderId;

    //	是否限制单笔购买数量，0 表示不限购，1 表示限购
    private Integer signalPurchase;

    //	如果单笔限购，那么具体的限购数量
    private Integer purchase;

    @Override
    public String toString() {
        return "OrderProjectVO{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", launchName='" + launchName + '\'' +
                ", returnContent='" + returnContent + '\'' +
                ", returnCount=" + returnCount +
                ", supportPrice=" + supportPrice +
                ", freight=" + freight +
                ", orderId=" + orderId +
                ", signalPurchase=" + signalPurchase +
                ", purchase=" + purchase +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLaunchName() {
        return launchName;
    }

    public void setLaunchName(String launchName) {
        this.launchName = launchName;
    }

    public String getReturnContent() {
        return returnContent;
    }

    public void setReturnContent(String returnContent) {
        this.returnContent = returnContent;
    }

    public Integer getReturnCount() {
        return returnCount;
    }

    public void setReturnCount(Integer returnCount) {
        this.returnCount = returnCount;
    }

    public Integer getSupportPrice() {
        return supportPrice;
    }

    public void setSupportPrice(Integer supportPrice) {
        this.supportPrice = supportPrice;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getSignalPurchase() {
        return signalPurchase;
    }

    public void setSignalPurchase(Integer signalPurchase) {
        this.signalPurchase = signalPurchase;
    }

    public Integer getPurchase() {
        return purchase;
    }

    public void setPurchase(Integer purchase) {
        this.purchase = purchase;
    }

    public OrderProjectVO() {
    }

    public OrderProjectVO(Integer id, String projectName, String launchName, String returnContent, Integer returnCount, Integer supportPrice, Integer freight, Integer orderId, Integer signalPurchase, Integer purchase) {
        this.id = id;
        this.projectName = projectName;
        this.launchName = launchName;
        this.returnContent = returnContent;
        this.returnCount = returnCount;
        this.supportPrice = supportPrice;
        this.freight = freight;
        this.orderId = orderId;
        this.signalPurchase = signalPurchase;
        this.purchase = purchase;
    }
}
