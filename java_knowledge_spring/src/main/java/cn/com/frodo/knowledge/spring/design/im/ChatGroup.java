package cn.com.frodo.knowledge.spring.design.im;

import lombok.Data;

import java.util.List;

@Data
public class ChatGroup {
    private Long groupId;
    private List<Long> uIds;
    private List<ChatMsg> messages;
}
