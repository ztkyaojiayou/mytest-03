package java基础.日期类;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: tk.zou
 * @Description:
 * @Date: 2022-02-11 15:42
 * @Version: 1.0.0
 */


public class Demo02 {
    public static void main(String[] args) {
        /**
         * （1）Date的使用与总结
         * 经过分析，我们发现，Date 类如今很多方法已经没有什么用处了，确实，如今时间的处理并不直接在Date 类中进行，
         * 而是通过Calendar，或者LocalDate 来进行，我们再日常使用中，更多的是要来做一个记录日期的实例。
         */
        Date date = new Date();
        int dates = date.getDate();
//我们可以看到，这里的getYear 并不是获取当前年份，而是获取到和1900年的差值，
// 这里主要是因为JDK老版本遗留下来的问题，
// 对于Date 中获取年月日的方法，现在已经不建议使用了。
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        System.out.println(year);
        int hours = date.getHours();
        System.out.println(dates);
        int min = date.getMinutes();
        int second = date.getSeconds();

        long time = date.getTime();
        int times = date.getTimezoneOffset();
        System.out.println(date);


        /**
         * （2）Calendar 的常用方法
         */

//我们最常用的方法有：
        Calendar calendar = Calendar.getInstance();
        Date date1111 = calendar.getTime();
//通过Calendar 方法获取一个Date 的实例。
//Calendar 方法获取年月日的方法：
        int year111 = calendar.get(Calendar.YEAR);
        int month111 = calendar.get(Calendar.MONTH) + 1;
        int day111 = calendar.get(Calendar.DAY_OF_MONTH);
        int hour111 = calendar.get(Calendar.HOUR_OF_DAY);
        int minute111 = calendar.get(Calendar.MINUTE);
        int seconds111 = calendar.get(Calendar.SECOND);

        /**
         *LocalDateTime与String日期互相转换
         */
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time111 = LocalDateTime.now();
        String localTime = df.format(time111);
        LocalDateTime ldt = LocalDateTime.parse("2016-08-08 18:08:08", df);
        System.out.println("LocalDateTime转成String类型的时间：" + localTime);
        System.out.println("String类型的时间转成LocalDateTime：" + ldt);


        /**
         * （3）LocalDate 提供了三种创建实例的方法：
         */
        //获取当前时间的LocalDate
        LocalDate localDate = LocalDate.now();
        //获取指定年、月、日 的 LocalDate
        LocalDate ofDate = LocalDate.of(2016, 12, 31);
        //通过解析字符串获取 LocalDate，如果格式不对会抛出 DateTimeParseException
        LocalDate parseDate = LocalDate.parse("2016-12-31-");
    }

}
