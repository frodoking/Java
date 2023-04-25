package cn.com.frodo.knowledge.spring.tcc;

import org.mengyun.tcctransaction.api.Compensable;

public interface OrderService {
    @Compensable
    void createOrder(Order order);
}
