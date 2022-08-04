package com.example.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * 自定义分区逻辑--根据具体业务逻辑设计即可
 *
 * @author :zoutongkun
 * @date :2022/8/4 2:56 下午
 * @description :
 * @modyified By:
 */
public class MyPartitioner implements Partitioner {
  @Override
  public int partition(
      String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
    return 0;
  }

  @Override
  public void close() {}

  @Override
  public void configure(Map<String, ?> configs) {}
}
