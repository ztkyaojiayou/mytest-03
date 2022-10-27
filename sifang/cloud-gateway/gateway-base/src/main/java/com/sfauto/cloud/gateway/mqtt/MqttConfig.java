package com.sfauto.cloud.gateway.mqtt;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@ConfigurationProperties(MqttConfig.PREFIX)
public class MqttConfig {
    public static final  String PREFIX="mqtt";
    private String host;
    private String clientid;
    private String username;
    private String password;
    private String topic;
    private int timeout;
    private int keepalive;
    private String plattopic;

//    public MqttPushClient getMqttPushClient() {
//        mqttPushClient.connect(host, clientid, username, password, timeout,keepalive);
//        mqttPushClient.subscribe(topic, 0);
//        return mqttPushClient;
//    }
}
