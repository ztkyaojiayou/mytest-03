package com.sfauto.cloud.gateway.mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MqttConfig.class)
@ConditionalOnBean(MqttPushClient.class)
@ConditionalOnProperty(prefix = "mqtt", value = "enabled", matchIfMissing = true)
public class MqttCallbackAutoConfiguration {
    @Autowired
    private MqttConfig properties;
    @Autowired
    private MqttPushClient mqttPushClient;

    @Bean
    @ConditionalOnMissingBean(PushCallback.class)
    public PushCallback pushCallback() {
        PushCallback pushCallback = new PushCallback(properties);
        mqttPushClient.setPushCallback(pushCallback);
        System.out.println("mqttPushClient");

        mqttPushClient.connect(properties.getHost(), properties.getClientid(), properties.getUsername(), properties.getPassword(), properties.getTimeout(),properties.getKeepalive());
        mqttPushClient.subscribe(properties.getTopic(), 0);

        return pushCallback;
    }
}
