package com.example.service;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

/**
 * @author :zoutongkun
 * @date :2022/8/4 3:52 下午
 * @description :
 * @modyified By:
 */
public class KafkaConsumerService {
  @Autowired KafkaConsumer<String, String> kafkaConsumer;

  public void consumeKafkaMsg() throws ExecutionException, InterruptedException {
    // 先指定要订阅哪个主题--是一个集合，即可以订阅多个主题
    // 订阅单个
    kafkaConsumer.subscribe(Collections.singleton("fisrt"));
    //    // 订阅多个
    //    kafkaConsumer.subscribe(Arrays.asList("first", "second"));

    // 还可以添加监听器，将offset自定义存储，一般会存入本地mysql中，以便自己能控制消费的offset
    kafkaConsumer.subscribe(
        Arrays.asList("test01"),
        new ConsumerRebalanceListener() {
          // 该方法会在Rebalance之前调用
          @Override
          public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
            // 获取某分区的最新offset--从muysql取
          }

          // 该方法会在Rebalance之后调用
          @Override
          public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
            // 提交该消费者所有分区的offset--可以存入mysql
          }
        });

    // 消费/获取消息--返回的是一个集合/ConsumerRecords对象，因为是批量获取的，效率更高
    ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
    // 因此需要遍历处理
    for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
      // key,但消费时是不会展示的，只展示value
      String key = consumerRecord.key();
      // 这是具体的消息内容
      String value = consumerRecord.value();
      // 打印--默认只消费订阅之后生产者生产的消息（参考订阅公众号）
      System.out.println("key= " + key + "," + "value" + value);
    }

    // 当关闭自动提交时，则需要在处理完这些消息时手动提交
    // 且在实际生产过程中，一般还会以事务的方式同时存入一份到mysql，
    // 即自己维护这个offset，这样就可以解决很多问题了！！！
    // 比如，消费端就完全不会丢失数据啦，即所有的消息都可以消费完毕。
    // 分为两种：同步提交和异步提交
    // 1.同步提交：当前线程会阻塞，直到offset提交成功，才会去拉取第二批消息
    kafkaConsumer.commitSync();
    // 2.异步提交：即会启动一个新的线程提交，不影响拉取下一个批次的消息，很明显效率更高
    // 它也有两种方式，一般会使用带回调的方式
    kafkaConsumer.commitAsync();
    kafkaConsumer.commitAsync(
        // 当一个接口中只有一个方法时，可以使用匿名内部类来实现，也可以使用lambda表达式（推荐）
        (offsets, exception) -> {
          // 若提交失败，可以打印/日志输出对应的offsets，以便后续手动处理
          if (exception != null) {
            System.out.println("手动提交失败，offsets：" + offsets);
          }
        });
  }
}
