package com.sfauto.cloud.gateway;

import com.sfauto.cloud.gateway.service.MqttResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MqttResponseTest {

//    @Autowired
//    private MqttResponse mqttResponse;

    @Test
    public void testModel(){
        try {
            MqttResponse mqttResponse = new MqttResponse();
            String str = mqttResponse.respModel();
            System.out.println(str);
        }catch (Exception exc){
            System.out.println(exc.getStackTrace());
        }
    }
}
