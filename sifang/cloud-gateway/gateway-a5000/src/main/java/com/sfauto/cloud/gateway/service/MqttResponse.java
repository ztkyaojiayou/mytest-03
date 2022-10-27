package com.sfauto.cloud.gateway.service;

import com.sfauto.cloud.gateway.dao.RealdbDao;
import com.sfauto.cloud.gateway.entity.HisDataParam;
import com.sfauto.cloud.gateway.entity.RealDataParam;
import com.sfauto.cloud.gateway.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MqttResponse implements IMqttResponse{

    @Autowired
    private RealdbDao realdbDao;

    @Override
    public String subRealData() {
        return "sub real data";
    }

    @Override
    public String subRealAlarm() {
        return "sub real alarm";
    }

    @Override
    public String respModel() {
        return JsonUtil.toJsonString(realdbDao.getModel());
//        return "test333";
    }

    @Override
    public String respHisData(HisDataParam hisDataParam) {
        return null;
    }

    @Override
    public String respHisSta() {
        return null;
    }

    @Override
    public String respRealData(RealDataParam realDataParam) {
        return null;
    }

    @Override
    public String respFile() {
        return null;
    }

    @Override
    public String respFileSend() {
        return null;
    }

    @Override
    public String respHisAlarm(HisDataParam hisDataParam) {
        return null;
    }

    @Override
    public String respRealAlarm(RealDataParam realDataParam) {
        return null;
    }

    @Override
    public String respControl() {
        return null;
    }
}
