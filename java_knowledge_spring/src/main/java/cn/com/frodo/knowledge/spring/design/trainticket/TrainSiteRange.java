package cn.com.frodo.knowledge.spring.design.trainticket;

import lombok.Data;

/**
 * 站点和站点之间信息
 */
@Data
public class TrainSiteRange {
    private TrainSite startSite;
    private TrainSite endSite;
    /**
     * 总票数
     */
    private Long totalCount;
    /**
     * 卖出的票数
     */
    private Long saleCount;
    /**
     * 剩余票数
     */
    private Long restCount;
}
