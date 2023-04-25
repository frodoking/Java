package cn.com.frodo.knowledge.spring.tcc;

import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    @Compensable(confirmMethod = "confirmUpdateCash", cancelMethod = "cancelUpdateCash")
    @Override
    public void decrease(Inventory inventory) {
        // 同样记录一个票据，或者记录，但是这个时候不会去扣减真是的资金。可以预占库存
    }

    public void confirmDecrease(Inventory inventory) {
        // 扣库存
    }

    public void cancelDecrease(Inventory inventory) {
        // 释放库存
    }
}
