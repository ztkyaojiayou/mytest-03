package com.sfauto.service.influxdb.service;

import com.influxdb.client.domain.Query;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxTable;
import com.sfauto.service.influxdb.pojo.InfluxdbEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface InfluxDBService {

    //region write

    /***
     * 写入 measurement
     * @param bucket 仓库
     * @param org 组织
     * @param pojo 实体
     */
    void writeMeasurement(String bucket, String org, Object pojo);

    /***
     * 写入 measurement
     * @param bucket 仓库
     * @param org 组织
     * @param pojos 实体
     */
    void writeMeasurements(String bucket, String org, List<Object> pojos);

    /***
     * 写入 measurement
     * @param bucket 仓库
     * @param org 组织
     * @param record 记录
     */
    void writeRecord(String bucket, String org, String record);

    /***
     * 写入 measurement
     * @param bucket 仓库
     * @param org 组织
     * @param records 记录
     */
    void writeRecords(String bucket, String org, List<String> records);

    /***
     * 写入点数据
     * @param bucket 仓库
     * @param org 组织
     * @param point 点数据
     */
    void writePoint(String bucket, String org, Point point);

    /***
     * 写入数据
     * @param measurement 测量
     * @param org 组织
     * @param bucket 仓库
     */
    void writePoint(String org, String bucket, String measurement);

    /***
     * 写入数据
     * @param measurement 测量
     * @param org 组织
     * @param bucket 仓库
     * @param tags   标志
     */
    void writePoint(String org, String bucket, String measurement, Map<String, String> tags);

    /***
     * 保存数据
     * @param measurement 测量
     * @param org 组织
     * @param bucket 仓库
     * @param tags   标志
     * @param fields 数据
     */
    void writePoint(String org, String bucket, String measurement, Map<String, String> tags, Map<String,Object> fields);

    /***
     * 写入点数据
     * @param bucket 仓库
     * @param org 组织
     * @param points 点数据
     */
    void writePoints(String bucket, String org, List<Point> points);

    //endregion write

    //region query

    /***
     * 查询数据
     * @param org 组织
     * @param bucket 仓库
     * @return 数据流
     */
    List<FluxTable> query(String org, String bucket);

    /***
     * 查询数据
     * @param org 组织
     * @param bucket 仓库
     * @param filters 条件
     * @return 数据流
     */
    List<FluxTable> query(String org, String bucket, Map<String, Object> filters);

    /***
     * 查询数据
     * @param org 组织
     * @param bucket 仓库
     * @param start 开始
     * @param stop 结束
     * @param filters 条件
     * @return 数据流
     */
    List<FluxTable> query(String org, String bucket, String start, String stop, Map<String, Object> filters);

    /***
     * 查询数据
     * @param org 组织
     * @param bucket 仓库
     * @param query 查询
     * @return 数据流
     */
    List<FluxTable> query(String org, String bucket, Query query);

    /***
     * 查询数据
     * @param org 组织
     * @param bucket 仓库
     * @param query 查询
     * @return 数据流
     */
    String queryRaw(String org, String bucket, Query query);

    //endregion query

}
