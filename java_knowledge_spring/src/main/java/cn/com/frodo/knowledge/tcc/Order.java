package cn.com.frodo.knowledge.tcc;


public class Order {
    public static final Integer STATUS_DELETE = -1;
    public static final Integer STATUS_DRAFT = 0;
    public static final Integer STATUS_FINISH = 1;
    String user;
    Long skuId;
    Integer cash;
    Integer count;
    Integer status;

    public Integer getCash() {
        return cash;
    }


    public Integer getCount() {
        return count;
    }

    public Integer getStatus() {
        return status;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public void updateStatus(Integer status) {
        this.status = status;
    }

}
