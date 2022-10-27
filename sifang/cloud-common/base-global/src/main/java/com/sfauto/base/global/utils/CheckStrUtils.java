package com.sfauto.base.global.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckStrUtils {

    /**
     * 正则表达式校验
     *
     * @param reg 正则表达式
     * @param str 字符串
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean validate(String reg, String str) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * Email正则表达式校验
     *
     * @param str Email字符串：xuzhene@yonyou.com
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean isEmail(String str) {
        String reg = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        return validate(reg, str);
    }

    /**
     * IP地址正则表达式校验
     *
     * @param str IP地址字符串：127.0.0.1
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean isIP(String str) {
        String reg = "((25[0-5]|2[0-4]\\d|[1]?\\d?\\d)\\.){3}((25[0-5]|2[0-4]\\d|[1]?\\d?\\d))";
        return validate(reg, str);
    }

    /**
     * URL正则表达式校验
     *
     * @param str URL字符串：http://www.yonyougov.com/
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean isUrl(String str) {
        String reg = "((http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?)|((((ht|f)tp(s?))://)?(www.|[a-zA-Z].)[a-zA-Z0-9-.]+.(com|edu|gov|mil|net|org|biz|info|name|museum|us|ca|uk)(:[0-9]+)*(/($|[a-zA-Z0-9\\.\\,\\;\\?\\'\\\\+&amp;%\\$#\\=~_\\-]+))*)|(\\b(([\\w-]+://?|www[.])[^\\s()<>]+(?:\\([\\w\\d]+\\)|([^[:punct:]\\s]|/))))";
        return validate(reg, str);
    }

    /**
     * 电话号码正则表达式校验
     *
     * @param str 电话号码字符串：010-88888888
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean isTelephone(String str) {
        String reg = "^(0([3-9]\\d\\d|10|2[1-9])-?[2-8]\\d{6,7})$";
        return validate(reg, str);
    }

    /**
     * 密码长度正则表达式校验
     *
     * @param password 密码字符串：12345678
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean psdLength(String password) {
        String reg = "^\\w{6,18}$";
        return validate(reg, password);
    }


    /**
     * 密码复杂度
     *
     * @param password
     * @return
     */
    public static boolean psdValid(String password){
        String reg = "\\A(?=[\\x20-\\x7E]*?[A-Z])(?=[\\x20-\\x7E]*?[a-z])(?=[\\x20-\\x7E]*?[0-9])[\\x20-\\x7E]{6,18}\\z";
        return validate(reg, password);
    }

    /**
     * 邮编正则表达式校验
     *
     * @param str 邮编字符串：100016
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean isPostalcode(String str) {
        String reg = "^[1-9]\\d{5}$";
        return validate(reg, str);
    }

    /**
     * 手机号码正则表达式校验
     *
     * @param str 手机号码字符串：18688888888
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean isMobile(String str) {
        String reg = "^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$";
        return validate(reg, str);
    }

    /**
     * 身份证正则表达式校验
     *
     * @param str 身份证码字符串：430888899999099876
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean isIDcard(String str) {
        String reg = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})[1|2][0-9]{3}((0[1-9])|(1[0-2]))((0[1-9])|([1|2][0-9])|(3[0-1]))[0-9]{3}[Xx0-9]";
        return validate(reg, str);
    }

    /**
     * 大写字符串正则表达式校验
     *
     * @param str 大写字符串：ABC
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean isUpChar(String str) {
        String reg = "^[A-Z]+$";
        return validate(reg, str);
    }

    /**
     * 小写字符串正则表达式校验
     *
     * @param str 小写字符串：abc
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean isLowChar(String str) {
        String reg = "^[a-z]+$";
        return validate(reg, str);
    }

    /**
     * 中文字符正则表达式校验
     *
     * @param str 中文字符串：中文
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean isChinese(String str) {
        String reg = "^[Α-￥]+$";
        return validate(reg, str);
    }

    /**
     * 正整数正则表达式校验，可校验以0开头的正整数如：01、02、1、2
     *
     * @param str 正整数字符串：01、02、1、2
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean isNumber(String str) {
        String reg = "^[0-9]+$";
        return validate(reg, str);
    }

    /**
     * 正整数正则表达式校验，不可校验0开头的正整数
     *
     * @param str 正整数字符串：1、2
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean isIntNumber(String str) {
        String reg = "^[1-9]\\d*$";
        return validate(reg, str);
    }

    /**
     * 浮点数正则表达式校验
     *
     * @param str 浮点数字符串：1.0、0.2
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean isFloat(String str) {
        String reg = "^(-?\\d*)(\\.\\d*)?";
        return validate(reg, str);
    }

    /**
     * 整数或小数正则表达式校验
     *
     * @param str 整数或小数字符串：-01、01、1、1.2
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean isEFloat(String str) {
        String reg = "[\\+\\-]?[\\d]+([\\.][\\d]*)?([Ee][\\+\\-]?[\\d]+)?";
        return validate(reg, str);
    }

    /**
     * 有效月正则表达式校验
     *
     * @param str 有效月字符串：1、2、3...12
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean isMonth(String str) {
        String reg = "^(0?[1-9]|1[0-2])$";
        return validate(reg, str);
    }

    /**
     * 有效日正则表达式校验
     *
     * @param str 有效日字符串：0、1、2、3...30
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean isDay(String str) {
        String reg = "^((0?[1-9])|((1|2)[0-9])|30|31)$";
        return validate(reg, str);
    }

    /**
     * 有效小时数正则表达式校验
     *
     * @param hourStr 有效小时数字符串：0、1、2、3...23
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean isValidHour(String hourStr) {
        if ((hourStr == null) || (hourStr.length() != 2))
            return false;
        if (!isNumber(hourStr)) {
            return false;
        }
        int hour = Integer.parseInt(hourStr);

        return (hour >= 0) && (hour <= 23);
    }

    /**
     * 有效分钟或秒钟正则表达式校验
     *
     * @param str 有效分钟或秒钟字符串：0、1、2、3...59
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean isValidMinuteOrSecond(String str) {
        if ((str == null) || (str.length() != 2))
            return false;
        if (!isNumber(str)) {
            return false;
        }
        int s = Integer.parseInt(str);

        return (s >= 0) && (s <= 59);
    }

    /**
     * 有效时间正则表达式校验
     *
     * @param str 有效时间字符串
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean isTime(String str) {
        String reg = "^(([0-1][0-9])|(2[0-3])):([0-5][0-9]):([0-5][0-9])$";
        return validate(reg, str);
    }

    /**
     * 特殊字符正则表达式校验
     *
     * @param str 特殊字符串
     * @return boolean 成功返回：true， 失敗返回：false
     */
    public static boolean isQuoteIn(String str) {
        String reg = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]+";
        return validate(reg, str);
    }


}
