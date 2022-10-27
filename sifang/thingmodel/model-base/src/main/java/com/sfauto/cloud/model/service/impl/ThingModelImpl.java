package com.sfauto.cloud.model.service.impl;

import com.alibaba.fastjson2.JSON;
import com.sfauto.cloud.model.consts.ModelConst;
import com.sfauto.cloud.model.dto.ModelDeviceDto;
import com.sfauto.cloud.model.dto.ModelPointDto;
import com.sfauto.cloud.model.entity.ModelDevice;
import com.sfauto.cloud.model.entity.ModelProperty;
import com.sfauto.cloud.model.entity.ModelPropertyPoint;
import com.sfauto.cloud.model.entity.ModelService;
import com.sfauto.cloud.model.mapper.ModelDeviceMapper;
import com.sfauto.cloud.model.mapper.ModelPropertyPointMapper;
import com.sfauto.cloud.model.service.IModelDeviceService;
import com.sfauto.cloud.model.service.IThingModel;
import com.sfauto.service.influxdb.service.InfluxDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
@ComponentScan(value = "com.sfauto.service.influxdb.config")
public class ThingModelImpl implements IThingModel {

    @Autowired
    private IModelDeviceService modelDeviceService;
    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    InfluxDBService influxDBService;

    @Autowired
    private ModelDeviceMapper modelDeviceMapper;
    @Autowired
    private ModelPropertyPointMapper modelPropertyPointMapper;

    //private Map<String, ModelDeviceDto> mapDevice;// 设备
    private Map<String, ModelPointDto> mapPoint; //点对应表 key:sysid+pointid

    @Override
    public boolean load() {
        List<ModelPointDto> list = modelPropertyPointMapper.getAllPropertyPoint();
        mapPoint.clear();

        for(ModelPointDto dto : list) {
            mapPoint.put(dto.getSysid()+"."+dto.getPointid(), dto);
        }

        return true;
    }

    @Override
    public boolean refresh() {
        return false;
    }

    @Override
    public boolean refresh(String devid) {
        redisTemplate.delete(devid);
        redisTemplate.delete(ModelConst.REALDATA_PREFIX+devid);
        cacheDevice(devid);

        return true;
    }

    @Override
    public ModelDeviceDto getDeviceDetail(String devid) {
        return null;
    }

    @Override
    public String getParentDeviceId(String devid) {
        HashOperations hashOperations = redisTemplate.opsForHash();

        if(!redisTemplate.hasKey(devid)) {
            cacheDevice(devid);
        }

        String pid = (String)hashOperations.get(devid, ModelConst.PARENT);

        return pid;
    }

    @Override
    public String[] getChildDeviceId(String devid) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        if(!redisTemplate.hasKey(devid)) {
            cacheDevice(devid);
        }

        String ids = (String)hashOperations.get(devid, ModelConst.CHILDREN);
        String[] idss = ids.split(",");

        return idss;
    }

    @Override
    public boolean writeValue(String devid, String proid, double value) {
        if(!redisTemplate.hasKey(devid)) {
            cacheDevice(devid);
        }

        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put(ModelConst.REALDATA_PREFIX+devid, proid, value);

        return true;
    }

    @Override
    public boolean writeStaticValue(String devid, String proid, String value) {
        return modelDeviceService.writeStaticProperty(devid, proid, value);
    }

    @Override
    public Double readRealValue(String devid, String proid) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        Double res = (Double)hashOperations.get(devid, proid);
        return res;
    }

    @Override
    public List<Double> readSerialValue(String devid, String proid, Timestamp st, Timestamp et) {
        //influxDBService.getList("");
        return null;
    }

    @Override
    public Map<String, Double> readRealValue(String devid) {
        HashOperations<String, String, Double> hashOperations = redisTemplate.opsForHash();
        Map<String, Double> map = hashOperations.entries(ModelConst.REALDATA_PREFIX+devid);

        return map;
    }

    @Override
    public ModelPointDto getProId(String sysid, String pointid) {
        ModelPointDto modelPointDto = mapPoint.get(sysid+"."+pointid);
        return modelPointDto;
    }

    @Override
    public boolean writeValueByPType(String devid, int pType, double value) {
        if(!redisTemplate.hasKey(devid)) {
            cacheDevice(devid);
        }

        HashOperations hashOperations = redisTemplate.opsForHash();
        String proid = (String)hashOperations.get(devid, pType);

        //hashOperations.put(devid, pType, proid+":"+String.valueOf(value));


        // todo: 写入实时库，写入时序库
        hashOperations.put(ModelConst.REALDATA_PREFIX+devid, proid, value);

        return true;
    }

    @Override
    public Double readRealValueByPType(String devid, int pType) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        String proid = (String)hashOperations.get(devid, pType);

        if(proid != null) {
            Double res = (Double)hashOperations.get(ModelConst.REALDATA_PREFIX+devid, proid);
            return res;
        }

        return null;
    }

    @Override
    public List<Double> readSerialValueByPType(String devid, int pType, Timestamp st, Timestamp et) {
        return null;
    }

    private void cacheDevice(String devid) {
        HashOperations hashOperations = redisTemplate.opsForHash();

        // 父设备
        String pid = modelDeviceService.getParent(devid);
        if(pid != null) {
            hashOperations.put(devid, "parent", pid);
        }

        // 子设备
        String[] list = modelDeviceService.getChildren(devid);
        if(list != null) {
            String children = "";
            for (int i = 0; i < list.length; i++) {
                children += list[i];
                if (i != list.length - 1) {
                    children += ",";
                }
            }

            hashOperations.put(devid, "children", children);
        }

        // 设备属性 以及 属性类型对应的属性及属性值
        List<ModelProperty> listPro = modelDeviceService.getModelProperty(devid);
        if(listPro != null) {
            for(int i=0; i<listPro.size(); i++) {
                ModelProperty pro = listPro.get(i);
                hashOperations.put(ModelConst.REALDATA_PREFIX+devid, pro.getUuid(), pro.getPvalue());

                if(pro.getCtype() != null) {
                    hashOperations.put(devid, pro.getCtype(), pro.getUuid());
                }
            }
        }

        // 计算服务
        List<ModelService> listSer = modelDeviceService.getModelService(devid, 1);
        if(listSer != null) {
            hashOperations.put(devid, ModelConst.SERVICE_REAL, JSON.toJSONString(listSer));
        }
        List<ModelService> listSer2 = modelDeviceService.getModelService(devid, 2);
        if(listSer2 != null) {
            hashOperations.put(devid, ModelConst.SERVICE_STAT, JSON.toJSONString(listSer2));
        }

        // todo: 事件
        
    }
}
