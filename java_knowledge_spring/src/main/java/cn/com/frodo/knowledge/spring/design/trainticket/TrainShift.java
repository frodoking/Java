package cn.com.frodo.knowledge.spring.design.trainticket;

import lombok.Data;

import java.util.Date;

/**
 * 火车班次
 */
@Data
public class TrainShift {
    private Date startTime;
    private Date endTime;
}
