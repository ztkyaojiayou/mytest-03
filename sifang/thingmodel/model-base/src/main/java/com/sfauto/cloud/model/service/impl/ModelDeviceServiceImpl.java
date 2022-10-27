/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.sfauto.cloud.model.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.sfauto.cloud.model.entity.ModelDevice;
import com.sfauto.cloud.model.entity.ModelProperty;
import com.sfauto.cloud.model.entity.ModelService;
import com.sfauto.cloud.model.mapper.ModelDeviceMapper;
import com.sfauto.cloud.model.mapper.ModelPropertyMapper;
import com.sfauto.cloud.model.mapper.ModelServiceMapper;
import com.sfauto.cloud.model.service.IModelDeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 设备 服务实现类
 * </p>
 *
 * @author sifang
 * @since 2022-09-19
 */
@Service
public class ModelDeviceServiceImpl extends ServiceImpl<ModelDeviceMapper, ModelDevice> implements IModelDeviceService {
    @Autowired
    ModelDeviceMapper modelDeviceMapper;
    @Autowired
    ModelServiceMapper modelServiceMapper;
    @Autowired
    ModelPropertyMapper modelPropertyMapper;

    public String getParent(String devid) {
        ModelDevice modelDevice = modelDeviceMapper.selectById(devid);
        if(modelDevice != null) {
            return modelDevice.getPid();
        }

        return null;
    }

    public String[] getChildren(String devid) {
        QueryWrapper<ModelDevice> wapper = new QueryWrapper<>();
        wapper.eq("pid", devid);
        List<ModelDevice> list = modelDeviceMapper.selectList(wapper);

        if(list.size() > 0) {
            String[] res = new String[list.size()];
            int i = 0;
            for(ModelDevice device : list) {
                res[i] = device.getUuid();
                i++;
            }

            return res;
        }

        return null;
    }

    public List<ModelService> getModelService(String devid, int type) {
        QueryWrapper<ModelService> wapper = new QueryWrapper<>();
        wapper.eq("TID", devid);
        wapper.eq("CalType", type);

        List<ModelService> list = modelServiceMapper.selectList(wapper);
        return list;
    }

    public List<ModelProperty> getModelProperty(String devid) {
        QueryWrapper<ModelProperty> wapper = new QueryWrapper<>();
        wapper.eq("TID", devid);

        List<ModelProperty> list = modelPropertyMapper.selectList(wapper);

        return list;
    }

    public boolean writeStaticProperty(String devid, String proid, String value) {
        LambdaUpdateWrapper<ModelProperty> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(ModelProperty::getUuid, proid)
                .set(ModelProperty::getPvalue, value);
        modelPropertyMapper.update(null, lambdaUpdateWrapper);

        return true;
    }
}
