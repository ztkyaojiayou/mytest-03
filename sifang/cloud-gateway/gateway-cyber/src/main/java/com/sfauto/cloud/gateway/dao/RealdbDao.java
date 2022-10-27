package com.sfauto.cloud.gateway.dao;

import com.sfauto.realdb.DBTable;
import com.sfauto.realdb.JRDBSet;
import org.springframework.stereotype.Repository;

@Repository
public class RealdbDao {

    private JRDBSet mdb;

    /**
     * @description 初始化
     * @return 标志
     * */
    public boolean initDB() throws Exception{
        mdb = JRDBSet.getInstance();
        if (mdb.initialize(true)) {
            return true;
        }
        return false;
    }

    /**
     * @description 方式一
     * @param db_id 库名称
     * @param table_id 表名称
     * @return 标志
     * */
    public DBTable getTable(int db_id, int table_id) throws Exception{

        //初始化数据库
        if (!initDB()){
            return null;
        }

        return mdb.getTable(db_id, table_id);
    }

    /**
     * @description 方式二
     * @param db_name 库名称
     * @param table_name 表名称
     * @return 标志
     * */
    public DBTable getTable(String db_name, String table_name) throws Exception{

        //初始化数据库
        if (!initDB()){
            return null;
        }

        return mdb.getTable(db_name, table_name);
    }

    /**
     * @description 方式三
     * @param db_cname 库名称
     * @param table_cname 表名称
     * @return 标志
     * */
    public DBTable getTableCn(String db_cname, String table_cname) throws Exception{

        //初始化数据库
        if (!initDB()){
            return null;
        }

        return mdb.getTable(db_cname, table_cname);
    }
}
