package cn.com.frodo.algorithm.im;

/**
 * 接线员查找上下文
 *
 * @author frodoking
 * @ClassName: FindReceiverStrategyContext
 * @date 2022/3/16
 */
public class FindReceiver {
    // 持有一个具体策略的对象
    private final FindReceiverStrategy strategy;

    /**
     * 构造函数，传入一个具体策略对象
     *
     * @param strategy 具体策略对象
     */
    public FindReceiver(FindReceiverStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 策略方法
     *
     * @param currentWorker 当前搜索的员工
     */
    public IMWorker find(IMWorker currentWorker) {
        return strategy.find(currentWorker);
    }
}
