package cn.com.frodo.knowledge.spring.design.im;

import lombok.Data;

@Data
public class ForwardSocketUserInfoRequest {
    private String sessionId;
    private String userId;
    //需要发送消息时携带的信息，可以自定义
    private Object object;
}
