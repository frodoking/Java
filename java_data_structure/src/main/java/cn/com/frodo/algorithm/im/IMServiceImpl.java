package cn.com.frodo.algorithm.im;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * IM服务实现类
 * @author frodoking
 * @ClassName: IMServiceImpl
 * @date 2022/3/16
 */
public class IMServiceImpl implements IMService {

    private IMWorker imDirector;
    private final Queue<IMWorker> cacheQueue = new ConcurrentLinkedDeque<>();

    public void setOrganization(IMWorker imDirector) {
        this.imDirector = imDirector;
    }

    @Override
    public IMWorker findAndConnectReceiver(long callTime) {
        // 从缓冲队列里来获取
        IMWorker currentWorker = cacheQueue.peek();
        // 如果未找到 则随机找一个
        if (currentWorker == null) {
            currentWorker = imDirector.getAnyOne();
        }

        // 实现找接线员策略
        FindReceiverStrategy findReceiverStrategy = null;
        switch (currentWorker.getIdentity()) {
            case RECEIVER: // 如果当前是接线员，则走接线员策略
                findReceiverStrategy = new ReceiverFindReceiverStrategy();
                break;
            case MANAGER:
            case DIRECTOR: // 如果是管理者，走管理者接线策略
                findReceiverStrategy = new ManagerFindReceiverStrategy();
                break;
        }

        // 交给策略上下文去执行
        FindReceiver findReceiver = new FindReceiver(findReceiverStrategy);
        IMWorker imWorker = findReceiver.find(currentWorker);

        // 这里临时处理成找不到则让上游重试,未做等待设计
        if (imWorker == null) {
            return null;
        }

        // 如果找到则表示要使用，所以需要移除可用队列
        cacheQueue.remove(imWorker);

        // 连线通话动作
        imWorker.call(callTime);

        // 使用后释放的资源需要加回缓冲队列里
        cacheQueue.offer(imWorker);

        return imWorker;
    }
}
