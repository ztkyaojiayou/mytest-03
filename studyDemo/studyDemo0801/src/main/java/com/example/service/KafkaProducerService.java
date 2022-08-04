package com.example.service;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.internals.FutureRecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
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
public class KafkaProducerService {
  @Autowired KafkaProducer kafkaProducer;

  public void sendKafkaMsg() throws ExecutionException, InterruptedException {
    // 发消息--send
    for (int i = 0; i < 10; i++) {
      // 轮询分区
      kafkaProducer.send(new ProducerRecord("tkzou", "tstMsg--" + i));
      // 带回调
      kafkaProducer.send(
          new ProducerRecord("tkzou", "testMsg--2" + i),
          // 回调接口信息，metadata为基本信息，当失败时会报异常exception，成功时则无异常
          (metadata, exception) -> {
            if (exception == null) { // 即表示消息发送成功
              // 此时可以打印出分区和offset信息
              System.out.println(metadata.partition() + "---" + metadata.offset());
            }
          });
      // 指定分区
      kafkaProducer.send(new ProducerRecord("tkzou", "0", "tstMsg--" + i));

      // 那如何同步发送呢--使用send的返回值来实现，因为返回值是一个Future，因此可以调用它的get方法来实现
      // 调用get方法时，会：
      // 1.获取值，这是基本功能。
      // 2.更重要的是可以阻塞其他线程，这点才是最重要的，这样就可以做到同步啦！！！
      // 而在这里，kafka帮我们写了一个Future的实现类，也就是说在调用get方法时是调用的下面这个实现类中的方法
      //    @Override
      //    public RecordMetadata get() throws InterruptedException, ExecutionException {
      //        this.result.await();
      //        if (nextRecordMetadata != null)
      //            return nextRecordMetadata.get();
      //        return valueOrError();
      //    }
      Future res = kafkaProducer.send(new ProducerRecord("tkzou", "tstMsg--" + i));
      res.get();
      // 即相当于是调用的FutureRecordMetadata中的get方法！
      FutureRecordMetadata res1 = (FutureRecordMetadata) res;
      res1.get();
    }
  }
}
