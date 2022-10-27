package com.sfauto.cloud.gateway.quartz;

import com.sfauto.cloud.gateway.mqtt.MqttConfig;
import com.sfauto.cloud.gateway.mqtt.MqttPushClient;
import com.sfauto.cloud.gateway.service.IMqttResponse;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class RealDataJob implements Job{
    private void before(){
        log.info("任务开始执行");
    }

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        //before();
        System.out.println("开始："+System.currentTimeMillis());

        IMqttResponse mqttResponse = SpringUtil.getBean(IMqttResponse.class);
        MqttPushClient mqttPushClient = SpringUtil.getBean(MqttPushClient.class);
        MqttConfig properties = SpringUtil.getBean(MqttConfig.class);
        // TODO 业务
        String str = mqttResponse.subRealData();
        String topicResp = properties.getPlattopic()+"/"+properties.getTopic().substring(0, properties.getTopic().length()-2)+"/";
        mqttPushClient.pushlish(0, false, topicResp+"realdata", str);

        System.out.println("结束："+System.currentTimeMillis());
        //after();
    }

    private void after(){
        log.info("任务开始执行");
    }
}
