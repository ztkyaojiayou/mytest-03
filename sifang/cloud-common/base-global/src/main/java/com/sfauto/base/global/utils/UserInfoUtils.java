package com.sfauto.base.global.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class UserInfoUtils {


    public static String userInfoDecoder(String text) throws UnsupportedEncodingException {
        if(text != null){
            String[] userInfo = text.split("\\.");
            if(userInfo.length > 1){
                byte[] asBytes = Base64.getUrlDecoder().decode(userInfo[1]);
                return new String(asBytes, "utf-8");
            }
        }

        return null;
    }

}
