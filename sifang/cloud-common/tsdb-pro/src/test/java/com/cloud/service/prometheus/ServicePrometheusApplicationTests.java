package com.cloud.service.prometheus;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import io.prometheus.client.exporter.PushGateway;
import lombok.SneakyThrows;
import org.junit.internal.Classes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Random;

@SpringBootTest(classes = ServicePrometheusApplication.class)
class ServicePrometheusApplicationTests {

    @Autowired
    private PushGateway pushGateway;

    @Test
    void contextLoads() {

        Random rnd = new Random();

        Counter counter = Counter.build()
                .name("blog_visit") //这里模拟博客访问量
                .labelNames("blog_id") //博客id
                .help("counter_blog_visit") //这个名字随便起
                .register(); //注：通常只能注册1次，1个实例中重复注册会报错

        Gauge gauge = Gauge.build()
                .name("blog_fans") //这里模拟粉丝数(注：这里我们没设置label)
                .help("gauge_blog_fans")
                .register();

        gauge.inc(50);
        while (true) {
            //随机生成1个blogId
            int blogId = rnd.nextInt(100000);
            //该blogId的访问量+1
            counter.labels(blogId + "").inc();
            //模拟粉丝数的变化
            if (blogId % 2 == 0) {
                gauge.inc();
            } else {
                gauge.dec();
            }
            //利用网关采集数据
            try {
                pushGateway.push(counter, "prometheus");
                pushGateway.push(gauge, "prometheus");

                //辅助输出日志
                System.out.println("blogId:" + blogId);
                Thread.sleep(5000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

}
