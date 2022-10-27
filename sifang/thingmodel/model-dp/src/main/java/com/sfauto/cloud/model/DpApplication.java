package com.sfauto.cloud.model;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication(scanBasePackages = {"com.sfauto.cloud.model"})
@MapperScan({"com.sfauto.cloud.model.mapper"})
@EnableKafka
public class DpApplication {
    public static void main(String[] args) {
        SpringApplication.run(DpApplication.class, args);
    }
}
