/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.sfauto.cloud.model.service;

import com.sfauto.cloud.model.entity.ModelDevice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sfauto.cloud.model.entity.ModelProperty;
import com.sfauto.cloud.model.entity.ModelService;

import java.util.List;

/**
 * <p>
 * 设备 服务类
 * </p>
 *
 * @author sifang
 * @since 2022-09-19
 */
public interface IModelDeviceService extends IService<ModelDevice> {
    public String getParent(String devid);
    public String[] getChildren(String devid);
    public List<ModelService> getModelService(String devid, int type);
    public List<ModelProperty> getModelProperty(String devid);

    public boolean writeStaticProperty(String devid, String proid, String value);
}
