package com.sfauto.cloud.gateway.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Json工具类
 */
@Slf4j
@UtilityClass
public class JsonUtil {

    /**
     * 用fastjson 将json字符串解析为一个 JavaBean
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> T parseBean(String jsonString, Class<T> cls)
    {
        T t = null;
        try
        {
            t = JSON.parseObject(jsonString, cls);
        }
        catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return t;
    }

    /**
     * 用fastjson 将json字符串 解析成为一个 List<JavaBean> 及 List<String>
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> List<T> parseBeanArray(String jsonString, Class<T> cls)
    {
        List<T> list = new ArrayList<T>();
        try
        {
            list = JSON.parseArray(jsonString, cls);
        }
        catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 用fastjson 将jsonString 解析成 List<Map<String,Object>>
     *
     * @param jsonString
     * @return
     */
    public static List<Map<String, Object>> parseListMap(String jsonString)
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try
        {
            // 两种写法
            // list = JSON.parseObject(jsonString, new
            // TypeReference<List<Map<String, Object>>>(){}.getType());

            list = JSON.parseObject(jsonString, new TypeReference<List<Map<String, Object>>>()
            {
            });
        }
        catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 用fastjson 将Object 转化为jsonString
     *
     * @param object
     * @return
     */
    public static String toJsonString(Object object)
    {
        String jsonString = "";
        try
        {
            jsonString = JSON.toJSONString(object);
        }
        catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return jsonString;
    }

}