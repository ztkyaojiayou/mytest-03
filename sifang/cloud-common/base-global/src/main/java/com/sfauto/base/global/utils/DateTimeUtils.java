package com.sfauto.base.global.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTimeUtils
{

    static private Logger log = LogManager.getLogger(DateTimeUtils.class.getName());

    static String regex = "(\\*|today|t|yesterday|y|monday|mon|tuesday|tue|wednesday|wed|thursday|thu|friday|fri|saturday|sat|sunday|sun)([\\-+][0-9]+(\\b((.){1}[0-9]+))?(second|minute|hour|day|week|s|m|h|d|w))?|(\\b(19|20)[0-9]{2}[- /.](0[1-9]|1[012]|[1-9]|january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sep|october|oct|november|nov|december|dec)[- /.](0[1-9]|[12][0-9]|3[01]|[1-9])|\\b((0[1-9])|[12][0-9]|3[01]|[1-9])[- /.]((0[1-9]|[1-9]|1[012])|january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sep|october|oct|november|nov|december|dec)[- /.]((19|20)?)[0-9]{2})([ t](2[0-3]|[0-1][0-9]|[0-9]):[0-5][0-9](:[0-5][0-9][z]?)?)?|(19|20)[0-9]{2}|(0[1-9]|1[012]|[1-9]|january|jan|february|feb|march|mar|april|apr|may|june|jun|july|jul|august|aug|september|sep|october|oct|november|nov|december|dec)[- /.](0[1-9]|[12][0-9]|3[01]|[1-9])|\\b(0[1-9]|[12][0-9]|3[01]|[1-9])\\b";
    static String operateRegx	= "[+-]";
    static String complexRegx = "(\\*|today|t|yesterday|y|monday|mon|tuesday|tue|wednesday|wed|thursday|thu|friday|fri|saturday|sat|sunday|sun)([\\-+][0-9]+(\\b((.){1}[0-9]+))?(second|minute|hour|day|week|s|m|h|d|w))?";
    static String digitRegx = "[0-9]+((.){1}[0-9]+)?";
    static String splitRegx="[- /.tz:]";

    final public static int COMPLEX_TYPE_CURRENT_TIME = 10;
    final public static int COMPLEX_TYPE_TODAY = 11;
    final public static int COMPLEX_TYPE_YESTERDAY = 12;

    static Map<String, Integer> dict = new HashMap() {
        {
            put("s", 1); put("m", 60); put("h", 3600); put("d", 86400); put("w", 604800);
            put("second", 1); put("minute", 60); put("hour", 3600); put("day", 86400); put("week", 604800);
            put("jan", 1); put("feb", 2); put("mar", 3); put("apr", 4); put("may", 5); put("jun", 6);
            put("jul", 7); put("aug", 8); put("sep", 9); put("oct", 10); put("nov", 11); put("dec", 12);
            put("january", 1); put("february", 2); put("march", 3); put("april", 4);
            put("june", 6); put("july", 7); put("august", 8); put("september", 9); put("october", 10);
            put("november", 11); put("december", 12);


            put("sun", 1); put("mon", 2); put("tue", 3); put("wed", 4); put("thu", 5); put("fri", 6); put("sat", 7);
            put("sunday", 1); put("monday", 2); put("tuesday", 3); put("wednesday", 4); put("thursday", 5); put("friday", 6); put("saturday", 7);
            put("*", COMPLEX_TYPE_CURRENT_TIME); put("t", COMPLEX_TYPE_TODAY); put("today", COMPLEX_TYPE_TODAY); put("y", COMPLEX_TYPE_YESTERDAY); put("yesterday", COMPLEX_TYPE_YESTERDAY);
        }
    };


    public static long getlTime(String expressionStr){
        String expression = expressionStr.trim().toLowerCase();
        if(expression.matches(regex)){
            Pattern pattern = Pattern.compile(complexRegx, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find())
            {
                // Get the matching string
                String exp = matcher.group();
                log.debug(exp);
                return getComplexDate(exp);
            }else{
                pattern = Pattern.compile(splitRegx, Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(expression);
                if (matcher.find()){
                    String[] ts = pattern.split(expressionStr.trim().toLowerCase());
                    return getSimpleTime(ts);
                }else{
                    String ts = expression;
                    return getSimpleTime(ts);
                }
            }
        }else{
            log.warn("the expressionStr[{}] is nonstandard!", expression);
            return -1;
        }

    }

    private static long getSimpleTime(String... tsplit) {
        int length = tsplit.length;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        log.debug("tsplit length is {}!", length);
        switch (length){
            case 1:
                String sp0 = tsplit[0];
                if(sp0.length() == 4){
                    cal.set(Calendar.YEAR , Integer.valueOf(sp0));
                    cal.set(Calendar.MONTH, 0);
                    cal.set(Calendar.DAY_OF_MONTH, 1);
                }else{
                    cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(sp0));
                }
                return cal.getTimeInMillis() / 1000;
            case 2:
                String spMonth = tsplit[0];
                int iMonth = StringUtils.isNumeric(spMonth)?Integer.valueOf(spMonth):dict.get(spMonth);
                int iDay = Integer.valueOf(tsplit[1]);

                cal.set(Calendar.MONTH, iMonth-1);
                cal.set(Calendar.DAY_OF_MONTH, iDay);
                return cal.getTimeInMillis() / 1000;
            case 3:
                setCalByContent(cal, tsplit);
                return cal.getTimeInMillis() / 1000;
            case 5:
                setCalByContent(cal, tsplit);
                cal.set(Calendar.HOUR_OF_DAY , Integer.valueOf(tsplit[3]));
                cal.set(Calendar.MINUTE , Integer.valueOf(tsplit[4]));
                return cal.getTimeInMillis() / 1000;
            case 6:
                setCalByContent(cal, tsplit);
                cal.set(Calendar.HOUR_OF_DAY , Integer.valueOf(tsplit[3]));
                cal.set(Calendar.MINUTE , Integer.valueOf(tsplit[4]));
                cal.set(Calendar.SECOND, Integer.valueOf(tsplit[5]));
                return cal.getTimeInMillis() / 1000;
            default:
                return -1;
        }
    }

    private static void setCalByContent(Calendar cal, String[] tsplit) {
        String sp0 = tsplit[0];
        String spMonth = tsplit[1];
        String sp2 = tsplit[2];
        int iMonth = StringUtils.isNumeric(spMonth)?Integer.valueOf(spMonth):dict.get(spMonth);
        cal.set(Calendar.MONTH, iMonth-1);
        if(sp0.length() == 4){
            cal.set(Calendar.YEAR , Integer.valueOf(sp0));
            cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(sp2));
        }else{
            if(sp2.length() == 2){
                try {
                    SimpleDateFormat sdf= new SimpleDateFormat("yy-MM-dd");
                    Date date =  sdf.parse(String.format("%s-%d-%s", sp2, iMonth, sp0));
                    cal.setTime(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else{
                cal.set(Calendar.DAY_OF_MONTH , Integer.valueOf(sp0));
                cal.set(Calendar.YEAR , Integer.valueOf(sp2));
            }
        }
    }

    private static long getComplexDate(String expressionStr) {
        Pattern pattern = Pattern.compile(operateRegx);
        Matcher matcher = pattern.matcher(expressionStr.trim());
        char oper;
        if (matcher.find()) {
            oper = matcher.group().charAt(0);
        }else{
            //System.out.println(expressionStr);
            Integer type = dict.get(expressionStr);
            if(type == null){
                log.warn("undefined keywords[{}] in dictionary!", expressionStr);
                return -1;
            }

            return getComplexTime(type);
        }

        String[] a_split = pattern.split(expressionStr.trim());
        Integer type = dict.get(a_split[0].trim().toLowerCase());
        if(type == null){
            log.warn("undefined keywords[{}] in dictionary!", a_split[0]);
            return -1;
        }

        long aOperand = getComplexTime(type);

        pattern = Pattern.compile(digitRegx);
        matcher = pattern.matcher(a_split[1].trim().toLowerCase());
        if (matcher.find()){
            String f = matcher.group();
            String[] s = pattern.split(a_split[1].trim().toLowerCase());
            Integer b = dict.get(s[1]);
            if(b != null){
                long bOperand = Math.round(b * Float.valueOf(f));
                long lTime = DateTimeUtils.operate(aOperand, oper,  bOperand);
                return Math.round(Math.floor(lTime));
            }else{
                log.warn("undefined keywords[{}] in dictionary!", s[1]);
            }
        }
        return -1;
    }

    private static long getComplexTime(int type){
        Calendar cal = Calendar.getInstance();
        switch (type){
            case COMPLEX_TYPE_CURRENT_TIME:
                return cal.getTimeInMillis() / 1000;
            case COMPLEX_TYPE_TODAY:
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                return cal.getTimeInMillis() / 1000;
            case COMPLEX_TYPE_YESTERDAY:
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                cal.add(Calendar.DAY_OF_MONTH, -1);
                return cal.getTimeInMillis() / 1000;
            default:
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                cal.add(Calendar.WEEK_OF_YEAR, -1);
                cal.set(Calendar.DAY_OF_WEEK, type);
                return cal.getTimeInMillis() / 1000;
        }
    }

    private static long operate(long a, char oper, long b) throws ArithmeticException
    {
        switch (oper)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            default:
                throw new ArithmeticException("error, The operators '" + oper + "' not define!");
        }
    }

    public static void main(String[] args) {
        //String str = "9-3-98 10:00";
        //String str = "*-2d";
        String str = "*";
        long lTime = DateTimeUtils.getlTime(str);

        System.out.println(lTime);

    }

}
