/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.sfauto.cloud.model.controller;

import com.sfauto.cloud.model.entity.ModelDevice;
import com.sfauto.cloud.model.service.IModelDeviceService;
import com.sfauto.cloud.model.service.IThingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

/**
 * <p>
 * 设备 前端控制器
 * </p>
 *
 * @author sifang
 * @since 2022-09-19
 */
@RestController
@RequestMapping("/model/model-device")
public class ModelDeviceController {
    @Autowired
    private IModelDeviceService modelDeviceService;
    @Autowired
    private IThingModel thingModel;

    @GetMapping("")
    public String test() {
        ModelDevice dev = modelDeviceService.getById("1234542343242");

        long t1 = System.currentTimeMillis();
        try {
            Class clazz = Class.forName("com.sfauto.cloud.algorithm.TestCalculate");
            Method method = clazz.getMethod("cal1", double.class, double.class, double.class);
            method.invoke(null, 1,2,3);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        if(thingModel.writeValue("", "", 0.0)) {
            System.out.println("dfdfd");
        }
        else {
            System.out.println("33333");
        }

        return dev.toString();
    }

}
