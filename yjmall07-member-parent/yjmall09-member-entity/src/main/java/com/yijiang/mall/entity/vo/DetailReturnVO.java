package com.yijiang.mall.entity.vo;

/**
 * @ClassName DetailReturnVO
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/30 15:15
 * @Version 1.0
 */
public class DetailReturnVO {

    // 回报主键信息
    private Integer returnId;

    // 当前栏位需要支持的金额
    private Integer supportMoney;

    // 是否限购，0时无限额，1时有限额
    private Integer signalPurchase;

    // 具体的限额数量
    private Integer purchase;

    // 当前栏位的支持者数量
    private Integer supporterCount;

    // 运费 0时表示包邮
    private Integer freight;

    // 众筹成功多少天后发货
    private Integer returnDate;

    // 回报的详细内容
    private String content;

    @Override
    public String toString() {
        return "DetailReturnVO{" +
                "returnId=" + returnId +
                ", supportMoney=" + supportMoney +
                ", signalPurchase=" + signalPurchase +
                ", purchase=" + purchase +
                ", supporterCount=" + supporterCount +
                ", freight=" + freight +
                ", returnDate=" + returnDate +
                ", content='" + content + '\'' +
                '}';
    }

    public Integer getReturnId() {
        return returnId;
    }

    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
    }

    public Integer getSupportMoney() {
        return supportMoney;
    }

    public void setSupportMoney(Integer supportMoney) {
        this.supportMoney = supportMoney;
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

    public Integer getSupporterCount() {
        return supporterCount;
    }

    public void setSupporterCount(Integer supporterCount) {
        this.supporterCount = supporterCount;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public Integer getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Integer returnDate) {
        this.returnDate = returnDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DetailReturnVO(Integer returnId, Integer supportMoney, Integer signalPurchase, Integer purchase, Integer supporterCount, Integer freight, Integer returnDate, String content) {
        this.returnId = returnId;
        this.supportMoney = supportMoney;
        this.signalPurchase = signalPurchase;
        this.purchase = purchase;
        this.supporterCount = supporterCount;
        this.freight = freight;
        this.returnDate = returnDate;
        this.content = content;
    }

    public DetailReturnVO() {
    }
}
