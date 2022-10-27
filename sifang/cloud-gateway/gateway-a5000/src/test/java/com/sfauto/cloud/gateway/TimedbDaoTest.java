package com.sfauto.cloud.gateway;

import com.sfauto.a5000.client.Client;
import com.sfauto.a5000.client.RhdbClient;
import com.sfauto.a5000.rhdb.TimeUtils;
import com.sfauto.a5000.rhdb.TimeValue;
import org.junit.Test;

import java.util.List;

public class TimedbDaoTest {

    @Test
    public void initTest() {
        if(Client.open("172.60.100.1",9191)) {  //连接到服务
            long now = System.currentTimeMillis();
            long start = TimeUtils.startOfDay(now);
            String tagName = "scada.pnt_ana.1021000000013120001.value";
            //查询当天凌晨到现在的全部历史数据
            List<TimeValue> values = RhdbClient.query(tagName,start, now,0);
            if(values != null && values.size()>0){
                for(TimeValue tv:values){
                    System.out.println(tv);
                }
            }
            //查询当天凌晨到现在的，以10分钟（600秒）为间隔返回历史数据
            values = RhdbClient.query(tagName,start, now,600);
            if(values != null && values.size()>0){
                for(TimeValue tv:values){
                    System.out.println(tv);
                }
            }
        }
    }
}
