package com.cloud.service.prometheus.pojo;

import io.micrometer.core.instrument.Metrics;
import io.prometheus.client.Collector;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SifangCollector extends Collector {

    private final Collection<Metrics> colMetrics;

    public SifangCollector(Collection<Metrics> publicMetrics) {
        this.colMetrics = publicMetrics;
    }

    @Override
    public List<MetricFamilySamples> collect() {
        ArrayList<MetricFamilySamples> samples = new ArrayList<MetricFamilySamples>();
//        for (PublicMetrics publicMetrics : this.publicMetrics) {
//            for (Metric<?> metric : publicMetrics.metrics()) {
//                String name = Collector.sanitizeMetricName(metric.getName());
//                double value = metric.getValue().doubleValue();
//                MetricFamilySamples metricFamilySamples = new MetricFamilySamples(
//                        name, Type.GAUGE, name, Collections.singletonList(
//                        new MetricFamilySamples.Sample(name, Collections.<String>emptyList(), Collections.<String>emptyList(), value)));
//                samples.add(metricFamilySamples);
//            }
//        }
        return samples;
    }
}
