package cn.com.frodo.algorithm.im;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 一个接线员实体
 *
 * @author frodoking
 * @ClassName: IMReceiver
 * @date 2022/3/16
 */
public class IMReceiver implements IMWorker {

    /**
     * 自己的主管
     */
    private final IMWorker superior;
    /**
     * 自己的员工
     */
    private List<IMWorker> subordinateList;
    /**
     * 身份
     */
    private final IMReceiverIdentityEnum identityEnum;
    /**
     * 当前为了更好的顺序性，获取位置为了标识当前入口的顺序性
     */
    private int position;
    /**
     * 接线员状态, 初始状态false表示idle
     */
    private final AtomicBoolean state = new AtomicBoolean(false);

    public IMReceiver(IMReceiverIdentityEnum identity) {
        this(null, identity);
    }

    public IMReceiver(IMWorker superior, IMReceiverIdentityEnum identity) {
        this.superior = superior;
        this.identityEnum = identity;
    }

    @Override
    public IMWorker getSuperior() {
        return this.superior;
    }

    @Override
    public void addSubordinate(IMWorker subordinate) {
        if (subordinateList == null) {
            subordinateList = new ArrayList<>();
        }
        subordinateList.add(subordinate);
    }

    @Override
    public List<IMWorker> getSubordinates() {
        return this.subordinateList;
    }

    @Override
    public IMWorker getAnyOne() {
        List<IMWorker> workers = allWorker();
        if (position >= workers.size()) {
            position = 0;
        }
        return allWorker().get(position++);
    }

    @Override
    public boolean isIdle() {
        return !state.get();
    }

    private List<IMWorker> allWorker() {
        List<IMWorker> workers = new ArrayList<>();
        workers.add(this);
        if (subordinateList != null && !subordinateList.isEmpty()) {
            for (IMWorker subordinate : subordinateList) {
                workers.add(subordinate);
                if (subordinate.getSubordinates() != null && !subordinate.getSubordinates().isEmpty()) {
                    workers.addAll(subordinate.getSubordinates());
                }
            }
        }
        return workers;
    }

    @Override
    public IMReceiverIdentityEnum getIdentity() {
        return this.identityEnum;
    }

    @Override
    public void call(long callTime) {
        try {
            state.set(true);
            TimeUnit.SECONDS.sleep(callTime);
            state.set(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
