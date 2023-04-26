package cn.com.frodo.knowledge.spring.design.im;

import lombok.Data;

import java.io.Serializable;

/**
 * 存储客户端连接的信息，根据自己的情况，可以存一些业务参数，方便后面使用
 */
@Data
public class SocketUserInfoSessionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    //WebSocket Session 的sessionId
    private String sessionId;
    //userId
    private String userId;
    //客户端创建连接时候的服务器IP+端口号
    private String ip;
}
