package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

/**
 * @author :zoutongkun
 * @date :2022/8/4 1:38 下午
 * @description :
 * @modyified By:
 */
// 要声明为一个配置类才可以消费消息
@Configuration
public class KafkaTemplateConsumerService {
  @Autowired KafkaTemplate kafkaTemplate;

  // 即订阅某个topic
  @KafkaListener(topics = "first")
  public void consumeKafkaMsg(String msg) {
    System.out.println("收到了消息" + msg);
  }
}
