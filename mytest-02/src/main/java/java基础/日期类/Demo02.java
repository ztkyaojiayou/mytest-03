package java基础.日期类;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
         * Date的总结
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
         *LocalDateTime与String日期互相转换
         */
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time111 = LocalDateTime.now();
        String localTime = df.format(time111);
        LocalDateTime ldt = LocalDateTime.parse("2016-08-08 18:08:08", df);
        System.out.println("LocalDateTime转成String类型的时间：" + localTime);
        System.out.println("String类型的时间转成LocalDateTime：" + ldt);

    }

}
