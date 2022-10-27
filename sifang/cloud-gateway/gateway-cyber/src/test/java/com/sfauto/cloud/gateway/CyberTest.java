package com.sfauto.cloud.gateway;

import com.sfauto.cloud.gateway.dao.RealdbDao;
import com.sfauto.realdb.DBTable;
import com.sfauto.realdb.RealDBDefine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CyberTest {

    @Autowired
    private RealdbDao realdbDao;

    @Test
    public void testEnv(){
        try{
            String tomcatHome = System.getenv("CATALINA_HOME");
            System.out.println("CATALINA_HOME: "+tomcatHome);
            String nuHome = System.getenv("NU_HOME");
            System.out.println("NU_HOME: "+nuHome);
        }catch (Exception exc){
            exc.printStackTrace();
        }
    }

    @Test
    public void testDB(){
        try{
            boolean flag =  realdbDao.initDB();
            System.out.println(flag);
        }catch (Exception exc){
            exc.printStackTrace();
        }
    }

    @Test
    public void getDB01(){
        try{
            //表ID
            int db_id = RealDBDefine.dbID_SYSTEM;
            //库ID
            int table_id = RealDBDefine.tableID_scadapublic_Formula;

            //获取数据
            DBTable dbTable = realdbDao.getTable( db_id, table_id);

            System.out.println("getDB01");
        }catch (Exception exc){
            exc.printStackTrace();
        }
    }

    @Test
    public void getDB02(){
        try{
            //表名称
            String db_name = "";
            //库名称
            String table_name = "";
            //获取数据
            DBTable dbTable = realdbDao.getTable(db_name, table_name);
        }catch (Exception exc){
            exc.printStackTrace();
        }
    }

    @Test
    public void getDB03(){
        try{
            //表名称
            String db_cname = "";
            //库名称
            String table_cname = "";
            //获取数据
            DBTable dbTable = realdbDao.getTableCn( db_cname, table_cname);
        }catch (Exception exc){
            exc.printStackTrace();
        }
    }
}
