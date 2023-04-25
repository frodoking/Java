package cn.com.frodo.knowledge.spring.tcc;

import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private CashService cashService;
    @Resource
    private InventoryService inventoryService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    @Compensable(confirmMethod = "confirmCreateOrder", cancelMethod = "cancelCreateOrder")
    @Override
    public void createOrder(Order order) {

        order.updateStatus(Order.STATUS_DRAFT);

        cashService.updateCash(order.getCash());

        Inventory inventory = new Inventory();
        inventory.setSkuId(order.getSkuId());
        inventory.setCount(order.getCount());
        inventoryService.decrease(inventory);
    }

    public void confirmCreateOrder(Order order) {
        order.updateStatus(Order.STATUS_FINISH);
    }

    public void cancelCreateOrder(Order order) {
        order.updateStatus(Order.STATUS_DELETE);
    }
}
