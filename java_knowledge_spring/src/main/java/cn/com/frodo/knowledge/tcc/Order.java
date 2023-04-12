package cn.com.frodo.knowledge.tcc;

import lombok.Data;

@Data
public class Order {
    public static final Integer STATUS_DELETE = -1;
    public static final Integer STATUS_DRAFT = 0;
    public static final Integer STATUS_FINISH = 1;
    String user;
    Long skuId;
    Integer cash;
    Integer count;
    Integer status;

    public void updateStatus(Integer status) {
        this.status = status;
    }

}
