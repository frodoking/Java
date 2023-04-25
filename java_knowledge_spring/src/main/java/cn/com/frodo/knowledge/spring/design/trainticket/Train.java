package cn.com.frodo.knowledge.spring.design.trainticket;


import com.beust.jcommander.internal.Lists;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 当前车次作为聚合根
 *
 * 通过这样的模型设计，我们可以确保一次出票处理只会在一个车次聚合根内进行。这样的好处是：
 *
 * 不需要依赖数据库事务就能实现数据修改的强一致性，因为所有修改只在一个聚合根内发生；
 * 在保证数据强一致性的同时还能提供很高的并发处理能力，具体设计见下面的架构设计。
 */
@Data
public class Train {
    /**
     * 车次 G4210
     */
    private String trainNo;
    /**
     * 座位数
     */
    private Long seatCount;
    /**
     * 所有站点
     */
    private List<TrainSite> siteList;
    /**
     * 所有原子区间的数据
     */
    private List<TrainSiteRange> atomicSiteRangeList;
    /**
     * 所有的座位
     */
    private List<TrainSeat> allSeatList;
    /**
     * 当前班次
     */
    private TrainShift currentShift;
    /**
     * 已经出售的票
     */
    private List<Ticket> saleTicket;

    public static Train buildTrain(String trainNo, Long seatCount, List<String> sites) {
        Train train = new Train();

        train.setTrainNo(trainNo);
        train.setSeatCount(seatCount);
        train.setSiteList(sites.stream().map(site -> {
            TrainSite trainSite = new TrainSite();
            trainSite.setSiteNo(UUID.randomUUID().toString().replace("-", "").substring(0, 8));
            trainSite.setSiteName(site);
            return trainSite;
        }).collect(Collectors.toList()));

        List<TrainSiteRange> trainSiteRanges = new ArrayList<>();
        for (int i = 1; i < train.getSiteList().size(); i++) {
            TrainSiteRange siteRange = new TrainSiteRange();
            siteRange.setStartSite(train.getSiteList().get(i - 1));
            siteRange.setEndSite(train.getSiteList().get(i));
            siteRange.setTotalCount(seatCount);
            siteRange.setRestCount(seatCount);
            trainSiteRanges.add(siteRange);
        }
        train.setAtomicSiteRangeList(trainSiteRanges);

        List<TrainSeat> trainSeats = new ArrayList<>();
        for (int i = 0; i < seatCount; i++) {
            TrainSeat trainSeat = new TrainSeat();
            trainSeat.setCarNo((i + 1) / 20 + "");
            trainSeat.setSeatNo((i + 1) % 20 + "");
            trainSeats.add(trainSeat);
        }
        train.setAllSeatList(trainSeats);

        train.setSaleTicket(new ArrayList<>());
        return train;
    }

    public static Train findTrain(String trainNo) {
        return Train.buildTrain(trainNo, 100L, Lists.newArrayList("A", "B", "C"));
    }

    public TrainSiteRange hasRestSiteRange(String startSite, String endSite) {
        // 是否有可出票
        boolean isInRange = false;
        TrainSiteRange minTicketRange = null;
        for (TrainSiteRange siteRange : atomicSiteRangeList) {
            if (siteRange.getStartSite().getSiteName().equals(startSite)) {
                isInRange = true;
            } else if (siteRange.getEndSite().getSiteName().equals(endSite)) {
                isInRange = false;
            }

            if (isInRange) {
                if (minTicketRange == null) {
                    minTicketRange = siteRange;
                }
                minTicketRange = siteRange.getRestCount() < minTicketRange.getRestCount() ? siteRange : minTicketRange;
            }
        }

            return minTicketRange;
    }

    public Ticket sale(Date expectedTime, String startSite, String endSite) {
        // 是否有可出票
        TrainSiteRange minTicketRange = hasRestSiteRange(startSite, endSite);

        if (minTicketRange == null) {
            throw new IllegalStateException("没有充足的票，出票失败！");
        }

        // 分配座位
        Set<TrainSeat> saleSeatList = new HashSet<>();
        for (Ticket ticket : saleTicket) {
            if (ticket.hasRepeat(siteList, startSite, endSite)) {
                saleSeatList.add(ticket.getSeat());
            }
        }

        TrainSeat prepareSaleSeat = null;
        for (TrainSeat trainSeat : allSeatList) {
            if (!saleSeatList.contains(trainSeat)) {
                prepareSaleSeat = trainSeat;
            }
        }

        Ticket ticket = new Ticket();
        ticket.setTrainNo(trainNo);
        ticket.setStartTime(expectedTime);
        ticket.setSeat(prepareSaleSeat);
        TrainSite sSite = new TrainSite();
        sSite.setSiteName(startSite);
        TrainSite eSite = new TrainSite();
        eSite.setSiteName(endSite);
        ticket.setStartSite(sSite);
        ticket.setEndSite(eSite);

        // 是否有座位
        return ticket;
    }

}
