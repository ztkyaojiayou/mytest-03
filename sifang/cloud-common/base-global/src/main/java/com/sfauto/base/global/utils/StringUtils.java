package com.sfauto.base.global.utils;

import java.util.UUID;

public final class StringUtils {

    public static String getSalt() {
        String model = "0123456789abcdef";
        StringBuilder sb = new StringBuilder(16);
        char[] m = model.toCharArray();
        for (int i = 0; i < 16; i++) {
            char c = m[(int) (Math.random() * 16)];
            sb.append(c);
        }
        return sb.toString();
    }

    public static String getRandomPasswd() {
        String model = "?!@-0123456789aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";

        StringBuilder sb = new StringBuilder(18);
        char[] m = model.toCharArray();
        for (int i = 0; i < 18; i++) {
            char c = m[(int) (Math.random() * 62)];
            sb.append(c);
        }

        return sb.toString();
    }

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(StringUtils.getRandomPasswd());
    }

}
