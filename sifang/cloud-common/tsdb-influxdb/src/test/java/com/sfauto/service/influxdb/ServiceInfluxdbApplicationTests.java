package com.sfauto.service.influxdb;

import com.sfauto.service.influxdb.service.InfluxDBService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceInfluxdbApplicationTests {

    @Autowired
    InfluxDBService influxDBService;

    @Test
    public void testQuery() {
        //TODO
    }

    @Test
    public void testWrite() {
        //TODO
    }
}
