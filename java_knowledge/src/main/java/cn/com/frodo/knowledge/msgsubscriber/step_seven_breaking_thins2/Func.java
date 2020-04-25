package cn.com.frodo.knowledge.msgsubscriber.step_seven_breaking_thins2;

/**
 * Func接口有两个类型成员
 *
 * @param <T> 参数类型
 * @param <R> 返回类型。
 */
public interface Func<T, R> {
    R call(T t);
}
