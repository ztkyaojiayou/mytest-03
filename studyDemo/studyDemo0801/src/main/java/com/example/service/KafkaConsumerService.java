package com.example.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

/**
 * @author :zoutongkun
 * @date :2022/8/4 3:52 下午
 * @description :
 * @modyified By:
 */
public class KafkaConsumerService {
  @Autowired KafkaConsumer kafkaConsumer;

  public void consumeKafkaMsg() throws ExecutionException, InterruptedException {
    // 先指定要订阅哪个主题--是一个集合，即可以订阅多个主题
    // 订阅单个
    kafkaConsumer.subscribe(Collections.singleton("fisrt"));
    //    // 订阅多个
    //    kafkaConsumer.subscribe(Arrays.asList("first", "second"));
    // 消费/获取消息--返回的是一个集合，因为是批量获取的，效率更高
    ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
    // 因此需要遍历处理
    for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
      // key,但消费时是不会展示的，只展示value
      String key = consumerRecord.key();
      // 这是具体的消息内容
      String value = consumerRecord.value();
      // 打印
      System.out.println("key= " + key + "," + "value" + value);

    }
  }
}
