package cn.com.frodo.knowledge.spring;


import cn.com.frodo.knowledge.spring.design.trainticket.Ticket;
import cn.com.frodo.knowledge.spring.design.trainticket.TicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApplication.class)
public class TicketServiceTest {

    private static final Logger log = LoggerFactory.getLogger(TicketServiceTest.class);

    @Autowired
    private TicketService ticketService;

    @Test
    public void testSale() {
        Ticket ticket = ticketService.sale(new Date(), "K-817", "A", "B");
        log.info("{} >> {}", ticket.getSeat().getSeatNo(), ticket.getSeat().getCarNo());
    }
}
