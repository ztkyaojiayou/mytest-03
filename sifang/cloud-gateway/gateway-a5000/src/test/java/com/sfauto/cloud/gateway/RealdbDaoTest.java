package com.sfauto.cloud.gateway;

import com.sfauto.a5000.client.Client;
import com.sfauto.a5000.client.RtdbClient;
import com.sfauto.a5000.rtdb.RtdbValue;
import org.junit.Test;

public class RealdbDaoTest {

    @Test
    public void initTest() {
        if(Client.open("172.60.100.1",9191)) {  //连接到服务
            RtdbValue value = RtdbClient.get("real#scada", "pnt_ana", "value", 1021000002143120245L);
            if (value != null) {
                System.out.println(value.type); //字段类型
                System.out.println(value.value); //字段值
            }
        }
    }
}
