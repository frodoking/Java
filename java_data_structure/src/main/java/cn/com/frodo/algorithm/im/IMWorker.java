package cn.com.frodo.algorithm.im;

import java.util.List;

/**
 * 抽象定义一个IM工作人员对象
 *
 * @author frodoking
 * @ClassName: IMWorker
 * @date 2022/3/16
 */
public interface IMWorker {

    /**
     * 获取自己的领导
     */
    IMWorker getSuperior();

    /**
     * 添加一个下属
     *
     * @param subordinate 下属
     */
    void addSubordinate(IMWorker subordinate);

    /**
     * 获取自己直接下属
     *
     * @return List<IMWorker> 员工
     */
    List<IMWorker> getSubordinates();

    /**
     * 随机找到一个自己组织里的员工
     *
     * @return IMWorker
     */
    IMWorker getAnyOne();

    /**
     * 是否是空闲状态
     *
     * @return boolean
     */
    boolean isIdle();

    /**
     * 获取自己的身份类型
     *
     * @return IMReceiverIdentityEnum
     */
    IMReceiverIdentityEnum getIdentity();

    /**
     * 发起接线动作
     *
     * @param callTime 通话时间
     */
    void call(long callTime);
}
