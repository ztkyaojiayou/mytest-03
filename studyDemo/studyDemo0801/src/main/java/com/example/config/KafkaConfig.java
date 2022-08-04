package com.example.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Properties;

/**
 * @author :zoutongkun
 * @date :2022/8/2 1:16 上午
 * @description :
 * @modyified By:
 */
@Configuration
public class KafkaConfig {
  @Bean
  public KafkaProducer getKafkaProducer() {
    Properties properties = new Properties();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "loal102:9092");
    properties.put(ProducerConfig.ACKS_CONFIG, "ack");
    // 定义为自定义的分区逻辑
    properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "MyPartitioner.class");
    KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
    return kafkaProducer;
  }

  @Bean
  public KafkaConsumer getKafkaConsumer() {
    Properties properties = new Properties();
    // 连接服务端--使用kafka本地的服务器
    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "handoop102:9092");
    // 开启自动提交--提交ofset
    properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
    // 开启自动提交的延迟
    properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
    // 对拉取的信息进行反序列化（因为生产者生产的消息会先序列化后再发送到kafka）
    // kafka提供了StringDeserializer这个类来实现反序列化
    properties.put(
        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringDeserializer");
    properties.put(
        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringDeserializer");
    // 配置消费者组--即配置消费者组名
    properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
    KafkaConsumer<String, String> KafkaConsumer = new KafkaConsumer<>(properties);
    // 注意：当前消费者订阅哪些主题并不在这里配置，而是在实际要消费时指定！！！
    return KafkaConsumer;
  }
}
