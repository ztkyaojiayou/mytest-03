package com.sfauto.cloud.gateway.mqtt;

import com.alibaba.fastjson2.JSON;
import com.sfauto.cloud.gateway.entity.HisDataParam;
import com.sfauto.cloud.gateway.entity.RealDataParam;
import com.sfauto.cloud.gateway.quartz.QuartzScheduler;
import com.sfauto.cloud.gateway.service.IMqttResponse;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Classname PushCallback
 * @Description 消费监听类
 */
@Slf4j
public class PushCallback implements MqttCallback {
    private MqttConfig mqttConfig;
    @Autowired
    private IMqttResponse mqttResponse;
    @Autowired
    MqttPushClient mqttPushClient;
    @Autowired
    QuartzScheduler quartzScheduler;

    public PushCallback(MqttConfig mqttConfiguration) {
        this.mqttConfig = mqttConfiguration;
    }

    @Override
    public void connectionLost(Throwable throwable) {
        log.info("连接断开，正在重连....");
        if (MqttPushClient.getClient() == null || !MqttPushClient.getClient().isConnected()) {
            log.info("连接断开，正在重连....");
            //mqttPushClient.;
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        log.info("接收消息主题 : " + topic);
        //log.info("接收消息Qos : " + message.getQos());
        //log.info("接收消息内容 : " + new String(message.getPayload()));

        String[] topics = topic.split("/");
        if(topics.length == 3) {
            if(topics[1].equals(mqttConfig.getPlattopic())) {

                String topicResp = mqttConfig.getPlattopic()+"/"+mqttConfig.getTopic().substring(0, mqttConfig.getTopic().length()-2)+"/";

                if(topics[2].equals("model")) {
                    String str = mqttResponse.respModel();
                    //log.info(str);
                    mqttPushClient.pushlish(0, false, topicResp+"model", str);
                }
                else if(topics[2].equals("realdata")) {
                    String payload = new String(message.getPayload());
                    RealDataParam param = JSON.parseObject(payload, RealDataParam.class);

                    if(param.getOpType().equals("sub")) {
                        try {
                            quartzScheduler.startJob("0/10 * * * * ?", "RealData");
                        }
                        catch (Exception e) {
                            log.error(e.getMessage());
                        }
                    }
                    else if(param.getOpType().equals("unsub")) {
                        try {
                            quartzScheduler.deleteJob("RealData", "RealData");
                        }
                        catch (Exception e) {
                            log.error(e.getMessage());
                        }
                    }
                    else if(param.getOpType().equals("query")) {
                        String str = mqttResponse.respRealData(param);
                        //log.info(str);
                        mqttPushClient.pushlish(0, false, topicResp+"realdata", str);
                    }
                }
                else if(topics[2].equals("hisdata")) {
                    String payload = new String(message.getPayload());

                    HisDataParam param = (HisDataParam) JSON.parse(payload);

                    String str = mqttResponse.respHisData(param);
                    //log.info(str);
                    mqttPushClient.pushlish(0, false, topicResp+"hisdata", str);
                }
            }
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
//        try {
//            log.info("deliveryComplete---------" + token.getMessage());
//        }
//        catch (Exception e) {
//            log.error(e.getMessage());
//        }
    }
}
