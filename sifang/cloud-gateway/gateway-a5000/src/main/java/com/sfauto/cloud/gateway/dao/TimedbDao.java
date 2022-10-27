package com.sfauto.cloud.gateway.dao;

import com.sfauto.a5000.rhdb.CommitDeviceValue;
import com.sfauto.a5000.rhdb.TimeTag;
import com.sfauto.a5000.rhdb.TimeValue;
import com.sfauto.a5000.rhdb.TimeValueEx;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TimedbDao {

    /**
     * 测试连接
     * @return 测试结果
     */
    public static boolean test(){
        return false;
    }

    /**
     * 按时间段查询指定标签点的历史数据
     * @param tagName   标志名称
     * @param start 开始时间，单位ms
     * @param end   结束时间。单位ms
     * @param interval  间隔时间。单位为s，=0代表返回全部数据，大于零的其他值表示间隔采样值
     * @return  时序值集合
     */
    public static List<TimeValue> query(String tagName, long start, long end, int interval){
        return null;
    }

    /**
     * 保存值到时序库
     * @param data  实时值
     * @return  结果标志
     */
    public static boolean save(List<CommitDeviceValue> data){
        return false;
    }

    /**
     * 更新值到时序库
     * @param tagName   标志名称
     * @param data  实时数据
     * @return  结果标志
     */
    public static boolean update(String tagName,List<TimeValueEx> data){
        return false;
    }

    /**
     * 添加标签点到时序库
     * @param tags  标志集合
     * @return  结果标志
     */
    public static boolean addTags(List<TimeTag> tags){
        return false;
    }

    /**
     * 查询统计值
     * @param tagName   标志名称
     * @param start     开始时间，单位ms
     * @param end       结束时间。单位ms
     * @param mode      类型，mode=API.RHDB_STA_MAX,API.RHDB_STA_MIN,API.RHDB_STA_AVG这三个之一
     * @return  统计值
     */
    private static Double queryStatistic(String tagName,long start,long end,int mode){
        return Double.NaN;
    }

    /**
     * 获取最大值
     * @param tagName   标志名称
     * @param start     开始时间，单位ms
     * @param end       结束时间。单位ms
     * @return  最大值
     */
    public static Double getMax(String tagName, long start, long end){
        return Double.NaN;
    }

    /**
     * 获取最小值
     * @param tagName   标志名称
     * @param start     开始时间，单位ms
     * @param end       结束时间。单位ms
     * @return  最小值
     */
    public static Double getMin(String tagName, long start, long end){
        return Double.NaN;
    }

    /**
     * 获取平均值
     * @param tagName   标志名称
     * @param start     开始时间，单位ms
     * @param end       结束时间。单位ms
     * @return  平均值
     */
    public static Double getAvg(String tagName, long start, long end){
        return Double.NaN;
    }
}
