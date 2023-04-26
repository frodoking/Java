package cn.com.frodo.knowledge.spring.design.im;


import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 为了使用方便，将保存的客户端信息保存为map
 */
@Data
public class SocketUserInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<String, Map<String, SocketUserInfoSessionDTO>> listMap;
}
