package com.sfauto.cloud.gateway;

import com.alibaba.fastjson.JSON;
import com.sfauto.a5000.rtdb.RtdbValue;
import com.sfauto.cloud.gateway.dao.RealdbDao;
import com.sfauto.cloud.gateway.dao.TimedbDao;
import com.sfauto.cloud.gateway.util.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtilTest {

    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    private RealdbDao realdbDao;

    @Autowired
    private TimedbDao timedbDao;

    @Test
    public void testJson(){
        String strResult = "[{\"result_message\":\"返回扫描结果\",\"taskResultId\":608328,\"result_vulMiddle\":0,\"result_endTime\":\"\",\"result_returnCode\":0,\"result_vulSum\":0,\"domain\":\"gaokao.haedu.gov.cn\",\"result_vulLow\":0,\"info_message\":\"成功创建任务\",\"result_vulHigh\":0,\"info_returnCode\":0,\"result_startTime\":\"2016-07-11 11:28:03\"}]";
        List<Map<String, Object>> resultlist = new ArrayList<Map<String, Object>>();
        resultlist = jsonUtil.parseListMap(strResult);
        System.out.println(resultlist);
        System.out.println(resultlist.get(0).get("taskResultId"));
    }

    @Test
    public void testJsonTostring(){
        String dbName = "";
        String tableName = "";
        String fieldName = "";
        long id = 1;
        RtdbValue rtdbValue = realdbDao.get(dbName, tableName, fieldName, id);
        String str = jsonUtil.toJsonString(rtdbValue);
        System.out.println(str);
    }

}
