package com.sfauto.cloud.model.service;

import com.alibaba.fastjson.JSON;
//import com.sfauto.cloud.model.dto.CyberModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.DependsOn;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.List;

@Slf4j
@Service
public class KafkaConsumerService {
    @KafkaListener(id = "pullPatternMsg", topicPattern = "plat.*.*", concurrency = "1")
    public void pullPatternMsg(@Payload String data,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                               @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {

        log.info("key: {}, topic: {}, partition: {}", key, topic, partition);
        //ack.acknowledge();

    }


}
