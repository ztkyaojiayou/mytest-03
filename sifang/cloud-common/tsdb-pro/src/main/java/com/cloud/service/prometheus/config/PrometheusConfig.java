package com.cloud.service.prometheus.config;

import io.prometheus.client.exporter.PushGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrometheusConfig {

    @Value("${management.metrics.host:192.188.1.245}")
    private String host;

    @Value("${management.metrics.port:9090}")
    private Integer port;

    @Bean
    public PushGateway pushGateway() {
        return new PushGateway(host + ":" + port);
    }

}
