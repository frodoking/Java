package cn.com.frodo.knowledge.kafka;

import cn.com.frodo.MockInterface;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.utils.CollectionUtils;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class KafkaClient implements MockInterface {

    public void produce() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.3.3:9092");
        properties.put("acks", "all");
        properties.put("retries", 3);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(properties);
        for (int i = 0; i < 10; i++) {
            String v = "value_" + System.currentTimeMillis();
            String key = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
            ProducerRecord<String, String> record = new ProducerRecord<>("frodo2", key, v);
            try {
                RecordMetadata recordMetadata = producer.send(record).get();
                System.out.println("AAA - " +recordMetadata.partition() + " - "+ recordMetadata.offset());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        List<PartitionInfo> partitionInfos = producer.partitionsFor("frodo2");
        for (PartitionInfo partition : partitionInfos) {
            System.out.println("BBB - " + partition);
        }
        producer.close(Duration.ofSeconds(1));
    }

    public void consumer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.3.3:9092");
        properties.put("group.id", "frodo-cgs");
        properties.put("enable.auto.commit", "false");
        properties.put("max.poll.records", 5);
        properties.put("auto.offset.reset", "earliest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.put("value.deserializer", "cn.com.frodo.knowledge.kafka.CustomStringDeserializer");
        Consumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Lists.newArrayList("frodo2"));
        ConsumerRecords<String, String> records = null;
        int batch = 0;
        int pa = 0;
        do {
            try {
                records = consumer.poll(Duration.ofSeconds(1));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (records != null) {
                for (ConsumerRecord<String, String> consumerRecord : records) {
                    pa = consumerRecord.partition();
                    System.out.println(batch + " >> " + consumerRecord.offset() + " >> " + consumerRecord.key() + " >> " + consumerRecord.value());
                }
                Map<TopicPartition, OffsetAndMetadata> offsets = Maps.newConcurrentMap();
                TopicPartition topicPartition = new TopicPartition("frodo2", pa);
                OffsetAndMetadata offsetAndMetadata = new OffsetAndMetadata(330, null);
                offsets.put(topicPartition, offsetAndMetadata);
//                consumer.commitSync(offsets);
                batch++;
            }
        } while (true);
    }

    @Override
    public void doTest() {
//        produce();
        consumer();
    }
}
