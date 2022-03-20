package cn.com.frodo.algorithm.im;

import java.util.List;

/**
 * 管理者视角下的接线员查找策略
 *
 * @author frodoking
 * @ClassName: ManagerBusyStrategy
 * @date 2022/3/16
 */
public class ManagerFindReceiverStrategy implements FindReceiverStrategy {
    @Override
    public IMWorker find(IMWorker currentWorker) {
        // 这里有一个技巧，如果当前是主管，可以将当前角色上升到经理开始来搜索。因为自己本身也是要搜索的，所以情况一样
        if (currentWorker.getIdentity() == IMReceiverIdentityEnum.MANAGER) {
            currentWorker = currentWorker.getSuperior();
        }

        // 找到下属
        List<IMWorker> subordinates = currentWorker.getSubordinates();

        IMWorker currentSubordinateGroupIdleWorker = findIdleWorkerInGroup(subordinates);
        if (currentSubordinateGroupIdleWorker != null) {
            return currentSubordinateGroupIdleWorker;
        }

        if (currentWorker.isIdle()) {
            return currentWorker;
        }

        return null;
    }

    /**
     * 找到所有主管组内的空闲接线员
     */
    private IMWorker findIdleWorkerInGroup(List<IMWorker> groupWorker) {
        for (IMWorker brother : groupWorker) {
            IMWorker currentBrotherGroupIdleWorker = findIdleWorkerInGroup(brother);
            if (currentBrotherGroupIdleWorker != null) {
                return currentBrotherGroupIdleWorker;
            }
        }
        return null;
    }

    /**
     * 查找单个组的空闲接线员
     */
    private IMWorker findIdleWorkerInGroup(IMWorker currentWorker) {
        for (IMWorker worker : currentWorker.getSubordinates()) {
            if (worker.isIdle()) {
                return worker;
            }
        }
        if (currentWorker.isIdle()) {
            return currentWorker;
        }
        return null;
    }
}
