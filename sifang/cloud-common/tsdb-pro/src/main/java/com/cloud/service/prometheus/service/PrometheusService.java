package com.cloud.service.prometheus.service;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;

import java.io.IOException;

public interface PrometheusService {

    /***
     * Counter：计数器 +1
     * @param counter
     */
    void incCouter(Counter counter);

    /***
     * Counter：计数器 +1
     * @param counter
     * @param lableName 标签
     */
    void incCouter(Counter counter, String lableName);

    /***
     * Guage：仪表盘 +1
     * @param gauge
     */
    void incGuage(Gauge gauge);

    /***
     * Guage：仪表盘 -1
     * @param gauge
     */
    void decGuage(Gauge gauge);

    /***
     * Guage：仪表盘 +1
     * @param gauge
     * @param lableName 标签
     */
    void incGuage(Gauge gauge, String lableName);

    /***
     * Guage：仪表盘 -1
     * @param gauge
     * @param lableName 标签
     */
    void decGuage(Gauge gauge, String lableName);

    /***
     * Summary：摘要
     * @param summary
     */
    void timeSummary(Summary summary);

    /***
     * Histogram：直方图
     * @param histogram
     */
    void timeHistogram(Histogram histogram);

    void push(String job) throws IOException;
}
