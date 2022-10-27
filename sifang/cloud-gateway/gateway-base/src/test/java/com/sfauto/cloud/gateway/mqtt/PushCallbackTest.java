package com.sfauto.cloud.gateway.mqtt;

import com.alibaba.fastjson.JSON;
import com.sfauto.cloud.gateway.entity.RealDataParam;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PushCallbackTest {
    @Test
    public void test() {
        RealDataParam p = new RealDataParam();
        p.setOpType("sub");
        p.setPeriod("10");
        List<String> l = new ArrayList<>();
        l.add("");
        p.setDevList(l);

        String s = JSON.toJSONString(p);
        System.out.println(s);
    }
}