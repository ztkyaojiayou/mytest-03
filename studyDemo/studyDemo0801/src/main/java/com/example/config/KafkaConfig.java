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

import java.util.ArrayList;
import java.util.List;
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
  public KafkaProducer<String, String> getKafkaProducer() {
    Properties properties = new Properties();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "loal102:9092");
    properties.put(ProducerConfig.ACKS_CONFIG, "ack");
    // 定义为自定义的分区逻辑
    properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "MyPartitioner.class");

    // 添加拦截器--生产端无需做任何改变
    List<String> interceptors = new ArrayList<>();
    // 使用全类名，要注意顺序，但当前这个逻辑由于没有先后循序，因此无所谓
    interceptors.add("com.example.interceptor.TimeInterceptor");
    interceptors.add("com.example.interceptor.CounterInterceptor");
    properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);
    return new KafkaProducer<>(properties);
  }

  @Bean
  public KafkaConsumer<String, String> getKafkaConsumer() {
    Properties properties = new Properties();
    // 连接服务端--使用kafka本地的服务器
    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "handoop102:9092");
    // 开启自动提交--提交ofset
    // 即在消费消息之前，先拿到offset值（但只拿一次，之后就不会再去zookeeper或kafka本地拿了，
    // 而是会在内存中获取，即该值也会在内存中存储一份），开始从该值对应的消息消费起，
    // 每次消费完一条消息，就会更新offset值（即写入zookeeper或kafka本地，同时也会更新内存中的该值），
    // 下次消费就会从该值对应的消息消费，
    // 因此若没有提交该值，则下次消费时还会从原来的offset对应的消息消费，
    // 也易知，会发生消息的重复消费（要明确的是，kafka的消息由于是通过发布/订阅的方式实现的，
    // 因此它的消息是会被持久化的，也即即便消息被消费了，但依然不会被删除！！！）。
    properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);

    properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
    //
    //    // 若关闭自动提交，则需要手动提交！
    // 也会出现消息重复消费（即当异步提交时突然挂掉时）和消息未被成功处理但被跳过了（也是当消息执行失败，但是offset却已经更新了时）的问题
    // 因此易知，重复消费无可避免，消息未被成功处理但被跳过了也无可避免
    // 对于重复消费的问题，我们可以使用幂等性来确保不影响业务；
    // 对于消息未被成功处理但被跳过了的问题，
    //    properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

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
    KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
    // 重置消费者的offset
    // 表示：当当前消费者组订阅了一个新topic后，订阅之前的消息也会被消费，默认是不开启的
    // 默认使用的是latest，即从最新的一条消息消费，也即只能消费订阅之后的产生的消息
    properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    // 注意：当前消费者订阅哪些主题并不在这里配置，而是在实际要消费时指定！！！
    return kafkaConsumer;
  }
}
