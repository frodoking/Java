package cn.com.frodo.knowledge.spring.design.sms;

import lombok.Data;

import java.util.Date;

@Data
public class SmsVerifyCode {
    private Long userId;
    private String smsId;
    // 1 手机 2 邮箱
    private Integer type;

    // 业务类型
    private Integer bizType;
    private String smsVerifyCode;
    /**
     * 是否可用
     */
    private Boolean isValid;
    private Date createTime;
}
