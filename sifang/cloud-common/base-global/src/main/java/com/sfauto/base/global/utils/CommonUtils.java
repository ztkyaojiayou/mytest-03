package com.sfauto.base.global.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class CommonUtils {

    public static Map<String, String> paramToMap(String paramStr, boolean bUrlDecoder) throws UnsupportedEncodingException {
        String[] params = paramStr.split("&");
        Map<String, String> resMap = new HashMap<String, String>();
        for (int i = 0; i < params.length; i++) {
            String[] param = params[i].split("=");
            if (param != null && param.length >= 2) {
                String key = param[0];
                String value = param[1];
                if(bUrlDecoder) {
                    value = URLDecoder.decode(param[1], "UTF-8");
                }
                for (int j = 2; j < param.length; j++) {
                    value += "=" + param[j];
                }
                resMap.put(key, value);
            }
        }
        return resMap;
    }

    public static String mapTpParam(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : map.entrySet()) {
            String t_key = (String) entry.getKey();
            Object t_oValue = entry.getValue();
            String t_strValue = "null";
            if(t_oValue == null){
                continue;
            }
            if (t_oValue instanceof String)
            {
                t_strValue = (String) t_oValue;
            }
            else if (t_oValue instanceof String[])
            {
                t_strValue = StringUtils.join((String[]) entry.getValue());
            }
            else
            {//默认
                t_strValue = t_oValue.toString();
            }

            sb.append(entry.getKey())
                    .append("=")
                    .append(t_strValue)
                    .append("&");
            //System.out.println("--"+sb.toString());
        }
        //System.out.println("----"+sb.toString());
        return sb.toString();
    }

    public static boolean validateJson(String jsonStr) {
        JsonElement jsonElement;
        try {
            jsonElement = new JsonParser().parse(jsonStr);
        } catch (Exception e) {
            return false;
        }
        if (jsonElement == null) {
            return false;
        }
        if (!jsonElement.isJsonObject()) {
            return false;
        }
        return true;
    }


}
