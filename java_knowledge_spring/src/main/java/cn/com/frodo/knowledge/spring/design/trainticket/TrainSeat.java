package cn.com.frodo.knowledge.spring.design.trainticket;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 火车座位
 */
@EqualsAndHashCode
@Data
public class TrainSeat {
    /**
     * 车箱编号
     */
    private String carNo;

    /**
     * 座位号
     */
    private String seatNo;


}
