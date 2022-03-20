package cn.com.frodo.algorithm.im;

/**
 * @author frodoking
 * @ClassName: FindReceiverStategy
 * @date 2022/3/16
 */
public interface FindReceiverStrategy {
    IMWorker find(IMWorker currentWorker);
}
