package com.sfauto.service.influxdb.service.impl;

import com.influxdb.client.*;
import com.influxdb.client.domain.Query;
import com.influxdb.client.domain.WriteConsistency;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.client.write.WriteParameters;
import com.influxdb.query.FluxTable;
import com.sfauto.service.influxdb.service.InfluxDBService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class InfluxDBServiceImpl implements InfluxDBService {

    @Autowired
    InfluxDBClient influxDBClient;

    //region write

    /***
     * 写入 measurement
     * @param bucket 仓库
     * @param org 组织
     * @param pojo 实体
     */
    @Override
    public void writeMeasurement(String bucket, String org, Object pojo) {
        // Initialize Blocking API
        WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();

//        // Write Measurement method1
//        writeApi.writeMeasurement(WritePrecision.NS, pojo);

        // Configure precision and consistency
        WriteParameters parameters = new WriteParameters(bucket, org, WritePrecision.NS, WriteConsistency.ALL);
        // Write Measurement method2
        writeApi.writeMeasurement(pojo, parameters);

//        //organization
//        Organization organization = influxDBClient.getOrganizationsApi().findOrganizationByID(InfluxdbConst.INFLUXDB_ORG_ID);
//        // Write Measurement method3
//        writeApi.writeMeasurement(bucket, organization.getId(), WritePrecision.NS, pojo);

    }

    /***
     * 写入 measurement
     * @param bucket 仓库
     * @param org 组织
     * @param pojos 实体
     */
    @Override
    public void writeMeasurements(String bucket, String org, List<Object> pojos) {
        // Initialize Blocking API
        WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();

//        // Write Measurement method1
//        writeApi.writeMeasurements(WritePrecision.NS, pojos);

        // Configure precision and consistency
        WriteParameters parameters = new WriteParameters(bucket, org, WritePrecision.NS, WriteConsistency.ALL);
        // Write Measurement method2
        writeApi.writeMeasurements(pojos, parameters);

//        //organization
//        Organization organization = influxDBClient.getOrganizationsApi().findOrganizationByID(InfluxdbConst.INFLUXDB_ORG_ID);
//        // Write Measurement method3
//        writeApi.writeMeasurements(bucket, organization.getId(), WritePrecision.NS, pojos);
    }

    /***
     * 写入 measurement
     * @param bucket 仓库
     * @param org 组织
     * @param record 记录
     */
    @Override
    public void writeRecord(String bucket, String org, String record) {
        // Initialize Blocking API
        WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();
        // Configure precision and consistency
        WriteParameters parameters = new WriteParameters(bucket, org, WritePrecision.NS, WriteConsistency.ALL);
        // Write record method1
        writeApi.writeRecord(record, parameters);

//        //organization
//        Organization organization = influxDBClient.getOrganizationsApi().findOrganizationByID(InfluxdbConst.INFLUXDB_ORG_ID);
//        // Write record method2
//        writeApi.writeRecord(bucket, organization.getId(), WritePrecision.NS, record);

    }

    /***
     * 写入 measurement
     * @param bucket 仓库
     * @param org 组织
     * @param records 记录
     */
    @Override
    public void writeRecords(String bucket, String org, List<String> records) {
        // Initialize Blocking API
        WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();
        // Configure precision and consistency
        WriteParameters parameters = new WriteParameters(bucket, org, WritePrecision.NS, WriteConsistency.ALL);
        // Write records
        writeApi.writeRecords(records, parameters);

//        //organization
//        Organization organization = influxDBClient.getOrganizationsApi().findOrganizationByID(InfluxdbConst.INFLUXDB_ORG_ID);
//        // Write record method2
//        writeApi.writeRecords(bucket, organization.getId(), WritePrecision.NS, records);
    }

    /***
     * 写入点数据
     * @param bucket 仓库
     * @param org 组织
     * @param point 点数据
     */
    @Override
    public void writePoint(String bucket, String org, Point point) {
        // Initialize Blocking API
        WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();
        // Configure precision and consistency
        WriteParameters parameters = new WriteParameters(bucket, org, WritePrecision.NS, WriteConsistency.ALL);

//        // Write Point method1
//        writeApi.writePoint(point);

//        // Write Point method2
//        writeApi.writePoint(bucket, org, point);

        // Write Point method3
        writeApi.writePoint(point, parameters);
    }

    /***
     * 写入数据
     * @param measurement 测量
     * @param org 组织
     * @param bucket 仓库
     */
    @Override
    public void writePoint(String org, String bucket, String measurement) {
        // Initialize Blocking API
        WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();
        // Configure precision and consistency
        WriteParameters parameters = new WriteParameters(bucket, org, WritePrecision.NS, WriteConsistency.ALL);
        // Initialize Point
        Point point = Point.measurement(measurement)
                .time(Instant.now(), WritePrecision.NS);

//        // Write Point method1
//        writeApi.writePoint(point);

//        // Write Point method2
//        writeApi.writePoint(bucket, org, point);

        // Write Point method3
        writeApi.writePoint(point, parameters);
    }

    /***
     * 写入数据
     * @param measurement 测量
     * @param org 组织
     * @param bucket 仓库
     * @param tags   标志
     */
    @Override
    public void writePoint(String org, String bucket, String measurement, Map<String, String> tags) {
        // Initialize Blocking API
        WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();
        // Configure precision and consistency
        WriteParameters parameters = new WriteParameters(bucket, org, WritePrecision.NS, WriteConsistency.ALL);
        // Initialize Point
        Point point = Point.measurement(measurement)
                .addTags(tags)
                .time(Instant.now(), WritePrecision.NS);

//        // Write Point method1
//        writeApi.writePoint(point);

//        // Write Point method2
//        writeApi.writePoint(bucket, org, point);

        // Write Point method3
        writeApi.writePoint(point, parameters);
    }

    /***
     * 写入数据
     * @param measurement 测量
     * @param org 组织
     * @param bucket 仓库
     * @param tags   标志
     * @param fields 数据
     */
    @Override
    public void writePoint(String org, String bucket, String measurement, Map<String, String> tags, Map<String,Object> fields) {
        // Initialize Blocking API
        WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();
        // Configure precision and consistency
        WriteParameters parameters = new WriteParameters(bucket, org, WritePrecision.NS, WriteConsistency.ALL);
        // Initialize Point
        Point point = Point.measurement(measurement)
                .addTags(tags)
                .addFields(fields)
                .time(Instant.now(), WritePrecision.NS);

//        // Write Point method1
//        writeApi.writePoint(point);

//        // Write Point method2
//        writeApi.writePoint(bucket, org, point);

        // Write Point method3
        writeApi.writePoint(point, parameters);
    }

    /***
     * 写入点数据
     * @param bucket 仓库
     * @param org 组织
     * @param points 点数据
     */
    @Override
    public void writePoints(String bucket, String org, List<Point> points) {
        // Initialize Blocking API
        WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();
        // Configure precision and consistency
        WriteParameters parameters = new WriteParameters(bucket, org, WritePrecision.NS, WriteConsistency.ALL);

//        // Write Point method1
//        writeApi.writePoints(points);
//
//        // Write Point method2
//        writeApi.writePoints(bucket, org, points);

        // Write Point method3
        writeApi.writePoints(points, parameters);
    }

    //endregion write

    //region query

    /***
     * 读取数据
     * @param org 组织
     * @param bucket 仓库
     * @return 数据流
     */
    @Override
    public List<FluxTable> query(String org, String bucket) {
        // Initialize QueryApi
        QueryApi queryApi = influxDBClient.getQueryApi();
        // create query
        String flux = String.format("from(bucket:\"%s\") |> range(start: 0)", bucket);
        // response result
        return queryApi.query(flux, org);
    }

    /***
     * 查询数据
     * @param org 组织
     * @param bucket 仓库
     * @param filters 条件
     * @return 数据流
     */
    @Override
    public List<FluxTable> query(String org, String bucket, Map<String, Object> filters) {
        // Initialize QueryApi
        QueryApi queryApi = influxDBClient.getQueryApi();
        // create query
        String query = String.format("from(bucket:\"%s\")", bucket);
        // response result
        return queryApi.query(query, org, filters);
    }

    /***
     * 查询数据
     * @param org 组织
     * @param bucket 仓库
     * @param start 开始
     * @param stop 结束
     * @param filters 条件
     * @return 数据流
     */
    @Override
    public List<FluxTable> query(String org, String bucket, String start, String stop, Map<String, Object> filters) {
        // Initialize QueryApi
        QueryApi queryApi = influxDBClient.getQueryApi();
        // create query
        String query = String.format("from(bucket:\"%s\") |> range(start: %s, stop: %s) %s", bucket, start, stop);
        // response result
        return queryApi.query(query, org, filters);
    }

    /***
     * 查询数据
     * @param org 组织
     * @param bucket 仓库
     * @param query 查询
     * @return 数据流
     */
    @Override
    public List<FluxTable> query(String org, String bucket, Query query) {
        // Initialize QueryApi
        QueryApi queryApi = influxDBClient.getQueryApi();
        // response result
        return queryApi.query(query, org);
    }

    /***
     * 查询数据
     * @param org 组织
     * @param bucket 仓库
     * @param query 查询
     * @return 数据流
     */
    @Override
    public String queryRaw(String org, String bucket, Query query) {
        // Initialize QueryApi
        QueryApi queryApi = influxDBClient.getQueryApi();

        // response query
        return queryApi.queryRaw(query, org);
    }

    //endregion query

}
