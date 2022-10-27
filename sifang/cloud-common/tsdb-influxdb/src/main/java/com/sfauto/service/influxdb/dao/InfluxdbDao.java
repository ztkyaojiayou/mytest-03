package com.sfauto.service.influxdb.dao;

import com.influxdb.client.InfluxDBClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * InfluxDB数据库连接操作类
 *
 * @author xuchao
 */
public class InfluxdbDao {

    @Autowired
    private InfluxDBClient influxDBClient;
}
