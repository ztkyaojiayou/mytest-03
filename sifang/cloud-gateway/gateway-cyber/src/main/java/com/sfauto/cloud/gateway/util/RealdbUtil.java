package com.sfauto.cloud.gateway.util;

//import com.sfauto.device.DeviceFactory;
import com.sfauto.cloud.gateway.entity.TurbineInfo;
import com.sfauto.realdb.JRDBSet;
import com.sfauto.web.device.Device;
import com.sfauto.web.device.DeviceApp;
import com.sfauto.web.device.DeviceFactory;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class RealdbUtil {
    public boolean initialize() {

        JRDBSet.getInstance().initialize(true);
        JRDBSet.getInstance().setRuntime(true);
        DeviceFactory.getInstance().initialize(DeviceFactory.INIT_MODE_RUN);

        return true;
    }

    public Object getWindFarms(){
        List<TurbineInfo> formalist = new ArrayList<>();
        DeviceApp[] farmDevices = DeviceFactory.getInstance().getDevices("WindFarm"); //获取设备
        if(farmDevices != null){
            for(DeviceApp app: farmDevices){
                Device dv = app.getDevice();
                formalist.add(new TurbineInfo(dv.getID(),dv.getName()));
            }
        }
        return formalist;
    }
}
