package cn.com.frodo.knowledge.callback;

/**
 * 回调接口
 *
 * @author frodoking </br> 这使得我们可以控制实现该接口的类得任何对象。因此，我们不必关心任何外部类型信息。
 * 发出事件信号的类必须等待实现了InterestingEvent接口的对象，并在适当时候调用interestingEvent()方法。
 */
public interface InterestingEvent {
    /**
     * 这是一个常规方法，如果需要，可以有返回值，也可以接受参数
     */
    public void interestingEvent();
}
