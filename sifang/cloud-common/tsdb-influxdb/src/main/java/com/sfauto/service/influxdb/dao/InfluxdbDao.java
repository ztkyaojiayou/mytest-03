package com.sfauto.service.influxdb.dao;

import com.influxdb.client.InfluxDBClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * InfluxDB���ݿ����Ӳ�����
 *
 * @author xuchao
 */
public class InfluxdbDao {

    @Autowired
    private InfluxDBClient influxDBClient;
}
