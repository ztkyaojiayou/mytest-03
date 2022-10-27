package com.sfauto.cloud.gateway.service;

import com.sfauto.cloud.gateway.entity.HisDataParam;
import com.sfauto.cloud.gateway.entity.RealDataParam;

public interface IMqttResponse {
    String respModel();
    String respHisData(HisDataParam hisDataParam);
    String respHisSta();
    String respRealData(RealDataParam realDataParam);
    String respFile();
    String respFileSend();
    String respHisAlarm(HisDataParam hisDataParam);
    String respRealAlarm(RealDataParam realDataParam);
    String respControl();

    String subRealData();
    String subRealAlarm();
}
