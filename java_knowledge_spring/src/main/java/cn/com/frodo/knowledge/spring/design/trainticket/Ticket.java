package cn.com.frodo.knowledge.spring.design.trainticket;


import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class Ticket {
    private String trainNo;
    private TrainSeat seat;
    private TrainSite startSite;
    private TrainSite endSite;
    private Date startTime;

    public boolean hasRepeat(List<TrainSite> siteList , String startSite, String endSite) {
        List<String> ticketRange = new ArrayList<>();
        List<String> expectRange = new ArrayList<>();
        boolean inTicketRange = false;
        boolean inExpectRange = false;
        for (TrainSite trainSite: siteList) {
            if (Objects.equals(this.startSite.getSiteName(), trainSite.getSiteName())) {
                inTicketRange = true;
            }else if (Objects.equals(this.endSite.getSiteName(), trainSite.getSiteName())) {
                inTicketRange = false;
            }

            if (inTicketRange) {
                ticketRange.add(trainSite.getSiteName());
            }

            if (Objects.equals(startSite, trainSite.getSiteName())) {
                inExpectRange = true;
            }else if (Objects.equals(endSite, trainSite.getSiteName())) {
                inExpectRange = false;
            }

            if (inExpectRange) {
                expectRange.add(trainSite.getSiteName());
            }
        }

       return ticketRange.retainAll(expectRange);
    }
}
