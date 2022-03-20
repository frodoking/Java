package cn.com.frodo.algorithm.im;

/**
 * IM服务接口
 *
 * @author frodoking
 * @ClassName: IMService
 * @date 2022/3/16
 */
public interface IMService {
    /**
     * 初始化组织结构
     *
     * @param imDirector 经理
     */
    void setOrganization(IMWorker imDirector);

    /**
     * 找到一个可用的接线员
     *
     * @param callTime 目标信息
     * @return IMWorker 链接的接线员
     */
    IMWorker findAndConnectReceiver(long callTime);
}
