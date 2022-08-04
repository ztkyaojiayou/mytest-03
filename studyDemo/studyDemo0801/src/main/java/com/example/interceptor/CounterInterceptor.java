package com.example.interceptor;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.Map;

/**
 * 计数拦截器
 *
 * @author :zoutongkun
 * @date :2022/8/4 7:51 下午
 * @description :
 * @modyified By:
 */
public class CounterInterceptor implements ProducerInterceptor<String, String> {
  public int successCnt = 0;
  public int failCnt = 0;
  /**
   * 在发送消息之前执行 需求：给每一个消息值加一个时间戳
   *
   * @param record
   * @return
   */
  @Override
  public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
    return null;
  }

  /**
   * 在消息从 RecordAccumulator 成功发送到 Kafka Broker 之后， 或者在发送过程 中失败时调用
   *
   * @param metadata
   * @param exception
   */
  @Override
  public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
    if (metadata != null) {
      successCnt++;
    } else {
      failCnt++;
    }
  }

  @Override
  public void close() {
    System.out.println("发送成功的消息数为：" + successCnt);
    System.out.println("发送失败的消息数为：" + failCnt);
  }

  @Override
  public void configure(Map<String, ?> configs) {}
}
