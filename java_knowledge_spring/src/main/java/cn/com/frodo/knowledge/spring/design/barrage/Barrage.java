package cn.com.frodo.knowledge.spring.design.barrage;

import lombok.Data;

@Data
public class Barrage {
    /**
     * 视频ID
     */
    private Long videoId;
    /**
     * 发送弹幕用户ID
     */
    private Long userId;
    /**
     * 视频相对时间
     */
    private Long videoTime;
    /**
     * 弹幕内容
     */
    private String message;

}
