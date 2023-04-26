package cn.com.frodo.knowledge.spring.design.im;

public interface WebSocketService {
    /**
     * 处理需要发送socket消息的任务
     */
    String socketSend(ForwardSocketUserInfoRequest request);

    /**
     * 该接口供本服务内部通过转发到对用的服务器发送http调用
     */
    String forwardSend(ForwardSocketUserInfoRequest request);
}
