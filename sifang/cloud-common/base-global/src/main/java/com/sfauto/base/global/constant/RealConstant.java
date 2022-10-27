package com.sfauto.base.global.constant;

public class RealConstant {

    public final static int TYPE_ANA = 1;
    public final static int TYPE_DIG = 2;

    public final static int REAL_ADD_TYPE_BATCH = 1;
    public final static int REAL_ADD_TYPE_SINGLE = 2;


    public final static int INT_TYPE = 0;
    public final static int LONG_TYPE = 1;
    public final static int FLOAT_TYPE = 2;
    public final static int DOUBLE_TYPE = 3;
    public final static int OTHER_TYPE = 4;

    /** 测点相关质量位定义 **/
    //虚点标志
    public final static int Q_RTDB_VIRTUAL = 0;
    //数据有效标志
    public final static int Q_RTDB_DATAVALID = 1;
    //人工置数
    public final static int Q_RTDB_MANUAL_SET = 2;
    //计算点标志
    public final static int Q_RTDB_CALCULATE_POINT = 3;
    //未投运
    public final static int Q_RTDB_NOT_RUNNING = 15;


    //越上限标志
    public final static int Q_RTDB_ANA_OVERHIGH =7;
    //越下限标志
    public final static int Q_RTDB_ANA_BELOW = 8;
    //越上上限标志
    public final static int Q_RTDB_ANA_OVERHIGH_HIGH = 9;
    //越下下限标志
    public final static int Q_RTDB_ANA_BELOWLOW_LOW = 10;

    //越上上上限标志
    public static final int Q_RTDB_ANA_OVERHIGH_HIGH_HIGH = 11;
    //越下下下限标志
    public static final int Q_RTDB_ANA_BELOWLOW_LOW_LOW = 12;

    //合状态报警中标志
    public final static int Q_RTDB_DIG_CLOSE_ALARMING =7;
    //分状态报警中标志
    public final static int Q_RTDB_DIG_OPEN_ALARMING = 8;

    /** 设备相关质量位定义 **/
    //虚设备标志
    public final static int Q_DEV_VIRTUAL = 0;
    //设备通信中断标志
    public final static int Q_DEV_COMMU_FAIL  = 1;

}
