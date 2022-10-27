package com.sfauto.cloud.gateway.dao;

import com.sfauto.a5000.rtdb.RtdbValue;
import com.sfauto.a5000.rtdb.TableInfo;
import com.sfauto.cloud.gateway.entity.Model;
import com.sfauto.cloud.gateway.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class RealdbDao {

    /**
     * 测试服务是否正常
     * @return 测试结果
     */
    public static boolean test(){
        return true;
    }

    /**
     * 获得库、表、字段、ID的值
     * @param dbName    库名称
     * @param tableName 表名称
     * @param fieldName 属性名称
     * @param id        属性id
     * @return  实时值
     */
    public static RtdbValue get(String dbName, String tableName, String fieldName, long id){
        RtdbValue rtdbValue = new RtdbValue();
        rtdbValue.type = 1;
        rtdbValue.setDouble(Double.NaN);
        rtdbValue.setInt(1);
        rtdbValue.setFloat(Float.NaN);
        rtdbValue.setLong(1);
        rtdbValue.setString("test");
        return rtdbValue;
    }

    /**
     * 获得库、表、ID的所有字段信息
     * @param dbName    库名称
     * @param tableName 表名称
     * @param id        id
     * @return  实时集合
     */
    public static Map<String,RtdbValue> getAll(String dbName, String tableName, long id){
        return null;
    }

    /**
     * 获得库、表、ID的指定字段的信息
     * @param dbName        库名称
     * @param tableName     表名称
     * @param fieldsName    属性名称
     * @param id    id
     * @return  实时集合
     */
    public static Map<String,RtdbValue> getFields(String dbName, String tableName, String[] fieldsName, long id){
        return null;
    }

    /**
     * 获得表结构信息
     * @param dbName    库名称
     * @param tableName 表名称
     * @return  表信息
     */
    public static TableInfo getTableInfo(String dbName, String tableName){
        TableInfo tableInfo = new TableInfo();
        tableInfo.setFieldInfo(tableName, 1, 1, 1, 1, 1, 1);
        return tableInfo;
    }

    public static Model getModel(){
        Model model = new Model();
        List<String> datas = new ArrayList<>();
        TableInfo tableInfo = getTableInfo("test", "con_1_description");//容器描述表
        datas.add(JsonUtil.toJsonString(tableInfo));
        tableInfo = getTableInfo("test", "con_1_relation");//容器关系表
        datas.add(JsonUtil.toJsonString(tableInfo));
        tableInfo = getTableInfo("test","con_acline");//交流线路容器表
        datas.add(JsonUtil.toJsonString(tableInfo));
        model.setDatas(datas);
        return model;
    }
}
