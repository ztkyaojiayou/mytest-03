package com.example.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.internals.FutureRecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author :zoutongkun
 * @date :2022/8/4 1:38 下午
 * @description :
 * @modyified By:
 */
@Service
public class KafkaTemplateProducerService {
  @Autowired KafkaTemplate kafkaTemplate;

  public void sendKafkaMsg(String msg) {
    kafkaTemplate.send("fistTopic", msg);
  }
}
