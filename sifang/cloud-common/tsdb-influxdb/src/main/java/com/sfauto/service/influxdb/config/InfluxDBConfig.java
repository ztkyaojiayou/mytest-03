package com.sfauto.service.influxdb.config;

import com.influxdb.LogLevel;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.flux.FluxClient;
import com.influxdb.client.flux.FluxClientFactory;
import com.influxdb.client.flux.FluxConnectionOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfluxDBConfig {

    @Value("${spring.influxdb.url:''}")
    private String url;

    @Value("${spring.influxdb.token:''}")
    private String token;

    @Value("${spring.influxdb.org:''}")
    private String org;

    @Value("${spring.influxdb.bucket:''}")
    private String bucket;

//    /***
//     * influxdb 2.x 配置
//     * @return influxdb2.0 实例
//     */
//    @Bean
//    public FluxClient fluxClient() {
//        FluxConnectionOptions fluxConnectionOptions = FluxConnectionOptions.builder().url(url).build();
//        FluxClient fluxClient = FluxClientFactory.create(fluxConnectionOptions);
//        fluxClient.setLogLevel(LogLevel.BASIC);
//        return fluxClient;
//    }

    /***
     * influxdb 2.x 配置
     * @return influxdb2.0 实例
     */
    @Bean
    public InfluxDBClient influxDBClient() {
        InfluxDBClient influxDBClient = InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
        influxDBClient.setLogLevel(LogLevel.BASIC);
        return influxDBClient;
    }
}
