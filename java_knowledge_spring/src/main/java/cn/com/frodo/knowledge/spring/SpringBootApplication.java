package cn.com.frodo.knowledge.spring;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;

/**
 * Created by frodoking on 2015/7/19.
 */
@org.springframework.boot.autoconfigure.SpringBootApplication(scanBasePackages = "cn.com.frodo.knowledge")
public class SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }

    @KafkaListener(id = "frodo-test2", topics = "frodo2", concurrency = "1")
    public void listen(ConsumerRecord<String, Object> consumerRecord, Acknowledgment ack, Consumer<?, ?> consumer) {
        try {
            System.out.println(Thread.currentThread().getName() +"-----"+consumerRecord.value());

            //ack.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (consumerRecord.value() == "value_1668916464010") {
            throw new UnsupportedOperationException("xxxxx");
        }
    }

}
