package cn.com.frodo.knowledge.spring.tcc;

import lombok.Data;

public class Inventory {
    Long skuId;
    Integer count;

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
