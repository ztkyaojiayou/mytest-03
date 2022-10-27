package com.sfauto.cloud.model.service;

import com.sfauto.cloud.model.dto.ModelDeviceDto;
import com.sfauto.cloud.model.dto.ModelPointDto;
import com.sfauto.cloud.model.entity.ModelProperty;
import org.apache.commons.collections.DoubleOrderedMap;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface IThingModel {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 模型载入
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean load();
    public boolean refresh();
    public boolean refresh(String devid);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 模型树访问
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ModelDeviceDto getDeviceDetail(String devid);

    public String getParentDeviceId(String devid);
    //public ModelDeviceDto getParentDevice(String devid);
    public String[] getChildDeviceId(String devid);
    //public List<ModelDeviceDto> getChildDevice(String devid);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 数据写入
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 根据scada系统上报点表数据更新实时库、时序库
    //public boolean writeValue(int sysid, int pointType, String pid, double value);
    // 根据设备id和属性id更新实时库、时序库
    public boolean writeValue(String devid, String proid, double value);
    // 根据设备id和属性id更新实时库、时序库
    public boolean writeValueByPType(String devid, int pType, double value);
    // 更新静态属性值，写关系库
    public boolean writeStaticValue(String devid, String proid, String value);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 数据读取
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 获取设备指定属性的实时值
    public Double readRealValue(String devid, String proid);
    // 获取设备指定属性类型的属性值
    public Double readRealValueByPType(String devid, int pType);
    // 获取设备指定属性的时序值
    public List<Double> readSerialValue(String devid, String proid, Timestamp st, Timestamp et);
    // 获取设备指定属性类型的时序值
    public List<Double> readSerialValueByPType(String devid, int pType, Timestamp st, Timestamp et);
    // 获取设备的所有实时属性值
    public Map<String, Double> readRealValue(String devid);

    // 根据scada点获取设备属性
    public ModelPointDto getProId(String sysid, String pointid);

}
