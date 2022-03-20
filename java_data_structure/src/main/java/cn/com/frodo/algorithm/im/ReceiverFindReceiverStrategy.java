package cn.com.frodo.algorithm.im;

/**
 * 接线员策略，这里需要继承管理者策略。因为可能需要上升，所以可以复用这部分逻辑
 *
 * @author frodoking
 * @ClassName: ReceiverBusyStategy
 * @date 2022/3/16
 */
public class ReceiverFindReceiverStrategy extends ManagerFindReceiverStrategy implements FindReceiverStrategy {
    @Override
    public IMWorker find(IMWorker currentWorker) {
        // 如果当前繁忙的画，需要找自己的同伴是否有空闲。否则就上升
        if (!currentWorker.isIdle()) {
            IMWorker superior = currentWorker.getSuperior();
            for (IMWorker worker : superior.getSubordinates()) {
                if (worker.isIdle()) {
                    return worker;
                }
            }
            return super.find(superior);
        }
        // 如果自己空闲肯定自己接入
        return currentWorker;
    }
}
