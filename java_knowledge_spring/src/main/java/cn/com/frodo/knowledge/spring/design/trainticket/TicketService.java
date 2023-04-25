package cn.com.frodo.knowledge.spring.design.trainticket;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    /**
     * 查询可以坐哪些车次
     * @param expectedTime 乘车时间
     * @param startSite 出发站点
     * @param endSite 到达站点
     * @return 所有可选的列车
     */
    public List<Train> query(Date expectedTime, String startSite, String endSite) {
        // 先假设随机一个车次
        List<Train> list = new ArrayList<>();
        Train train = Train.findTrain("Random");
        if (train.hasRestSiteRange(startSite, endSite) != null) {
            list.add(train);
        }
        train = Train.findTrain("Random2");
        if (train.hasRestSiteRange(startSite, endSite) != null) {
            list.add(train);
        }
        train = Train.findTrain("Random3");
        if (train.hasRestSiteRange(startSite, endSite) != null) {
            list.add(train);
        }

        return list;
    }

    /**
     * CQRS缓存设计, 将当前车次+班次的每一个站点区间作为KEY，当前区间可用位置数就是票数。
     * 每次卖票的时候会获取到当前车次，以及对应的多个原子区间，如果票数最小的原子区间还有票，说明可以售卖
     * @param expectedTime 期望时间
     * @param trainNo 车牌号
     * @param site 当前站点
     * @param nextSite 下一个站点
     * @return 缓存key
     */
    private String cacheKey(Date expectedTime, String trainNo, String site, String nextSite) {
        return String.format("%s:%s:%s:%s", expectedTime.getTime(), trainNo, site, nextSite);
    }

    /**
     * 出票
     * @param expectedTime 乘车时间
     * @param trainNo 选择的列车编号
     * @param startSite 出发站点
     * @param endSite 到达站点
     * @return 所有可选的列车
     */
    public Ticket sale(Date expectedTime, String trainNo, String startSite, String endSite) {
        Train train = Train.findTrain(trainNo);
        return train.sale(expectedTime, startSite, endSite);
    }
}
