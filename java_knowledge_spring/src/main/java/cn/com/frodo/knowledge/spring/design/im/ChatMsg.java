package cn.com.frodo.knowledge.spring.design.im;

import lombok.Data;

import java.util.Date;

@Data
public class ChatMsg {
    private Long groupId;
    private Long userId;
    private String msg;
    private Date date;
}
