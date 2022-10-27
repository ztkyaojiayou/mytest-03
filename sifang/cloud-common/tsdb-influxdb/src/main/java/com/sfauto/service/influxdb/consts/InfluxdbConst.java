package com.sfauto.service.influxdb.consts;

import org.springframework.beans.factory.annotation.Value;

import java.security.PublicKey;

public class InfluxdbConst {

    @Value("${influxdb.maxCountPerBucket}")
    public Integer maxCountPerBucket;

    public static final char[] INFLUXDB_TOKEN = "ItXo5mJLdi45dBEaJapeLi7eK5W1rDZK3VKdKrqHJlyTS5rBWPlP9lY2KrrVCpm1oC9NXX-SJBHLLAiSLuDhzw==".toCharArray();

    public static final String INFLUXDB_HOST = "192.188.1.245";
    public static final String INFLUXDB_PORT = "8086";
    public static final String INFLUXDB_ORG = "sifang";
    public static final String INFLUXDB_ORG_ID = "fbf99f3805dad264";
    public static final String INFLUXDB_BUCKET = "sf_bucket";

    public static final String GUID_FORMULA_ID = "guidformulaid";

    public static final String FT_ENERGY_VALUE = "ftenergyvalue";

    public static final String ENERGYBASEDATA = "energybasedata";

    public static final String ENERGYCYCLE = "energycycle";

    public static final String ENERGYDAY = "energyday";

    public static final String ENERGYUNITCYCLE = "energyunitcycle";

    public static final String ENERGYUNITDAY = "energyunitday";

    public static final String ENERGYUNITHOUR = "energyunithour";

    public static final String ENERGYUNITMONTH = "energyunitmonth";
}
