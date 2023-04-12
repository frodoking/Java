package cn.com.frodo.knowledge.tcc;

import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CashServiceImpl implements CashService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    @Compensable(confirmMethod = "confirmUpdateCash", cancelMethod = "cancelUpdateCash")
    @Override
    public void updateCash(Integer cash) {
        // 同样记录一个票据，或者记录，但是这个时候不会去扣减真是的资金。可以预占资金
    }

    public void confirmUpdateCash(Integer cash) {
        // 实际扣除资金
    }


    public void cancelUpdateCash(Integer cash) {
        // 取消扣除资金
    }
}
