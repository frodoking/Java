package cn.com.frodo.knowledge.spring.design.im;

import lombok.Data;

import java.util.Date;

@Data
public class Chat {
    /**
     * 当前人信息
     */
    private Long userId;
    /**
     * 组信息
     */
    private ChatGroup chatGroup;
    /**
     * 上次读取时间
     */
    private Date lastAckDate;
}
