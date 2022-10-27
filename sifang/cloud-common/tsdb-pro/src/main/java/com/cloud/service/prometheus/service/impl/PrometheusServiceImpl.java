package com.cloud.service.prometheus.service.impl;

import com.cloud.service.prometheus.service.PrometheusService;
import io.prometheus.client.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@EnableScheduling
public class PrometheusServiceImpl implements PrometheusService {

//    @Autowired
//    private PushGateway pushGateway;

    /***
     * Counter：计数器 +1
     * @param counter
     */
    @Override
    public void incCouter(Counter counter) {
        counter.inc();
    }

    /***
     * Counter：计数器 +1
     * @param counter
     * @param lableName 标签
     */
    @Override
    public void incCouter(Counter counter, String lableName) {
        counter.labels("get").inc();
    }

    /***
     * Guage：仪表盘 +1
     * @param gauge
     */
    @Override
    public void incGuage(Gauge gauge) {
        gauge.inc();
    }

    /***
     * Guage：仪表盘 -1
     * @param gauge
     */
    @Override
    public void decGuage(Gauge gauge) {
        gauge.dec();
    }

    /***
     * Guage：仪表盘 +1
     * @param gauge
     * @param lableName 标签
     */
    @Override
    public void incGuage(Gauge gauge, String lableName) {
        gauge.labels("lableName").inc();
    }

    /***
     * Guage：仪表盘 -1
     * @param gauge
     * @param lableName 标签
     */
    @Override
    public void decGuage(Gauge gauge, String lableName) {
        gauge.labels("lableName").dec();
    }

    /***
     * Summary：摘要
     * @param summary
     */
    @Override
    public void timeSummary(Summary summary) {

        Summary.Timer requestTimer = summary.startTimer();

        summary.time(() -> {
            // Your code here.
        });
    }

    /***
     * Histogram：直方图
     * @param histogram
     */
    @Override
    public void timeHistogram(Histogram histogram) {

        histogram.time(() -> {
            // Your code here.
        });
    }

    /***
     * 推送数据
     */
    @Override
    public void push(String job) throws IOException {
//        CollectorRegistry registry = CollectorRegistry.defaultRegistry;
//        pushGateway.pushAdd(registry, job);
    }
}
