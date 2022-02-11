package java基础.日期类;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
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
         * 3.1）介绍
         * Java 8新增了LocalDate和LocalTime接口，为什么要搞一套全新的处理日期和时间的API？因为旧的java.util.Date实在是太难用了。
         * java.util.Date月份从0开始，一月是0，十二月是11，变态吧！java.time.LocalDate月份和星期都改成了enum，就不可能再用错了。
         * java.util.Date和SimpleDateFormatter都不是线程安全的，而LocalDate和LocalTime和最基本的String一样，是不变类型，不但线程安全，而且不能修改。
         * 3.2）小结
         * 在新的Java 8中，日期和时间被明确划分为LocalDate和LocalTime，LocalDate无法包含时间，LocalTime无法包含日期。当然，LocalDateTime才能同时包含日期和时间。
         * 新接口更好用的原因是考虑到了日期时间的操作，经常发生往前推或往后推几天的情况。用java.util.Date配合Calendar要写好多代码，而且一般的开发人员还不一定能写对。
         */
        //获取当前时间的LocalDate
        LocalDate localDate = LocalDate.now();
        //获取指定年、月、日 的 LocalDate
        LocalDate ofDate = LocalDate.of(2016, 12, 31);
        //通过解析字符串获取 LocalDate，如果格式不对会抛出 DateTimeParseException
        LocalDate parseDate = LocalDate.parse("2016-12-31-");

        // 取本月第1天：
        LocalDate firstDayOfThisMonth = localDate.with(TemporalAdjusters.firstDayOfMonth()); // 2014-12-01
        // 取本月第2天：
        LocalDate secondDayOfThisMonth = localDate.withDayOfMonth(2); // 2014-12-02
        // 取本月最后一天，再也不用计算是28，29，30还是31：
        LocalDate lastDayOfThisMonth = localDate.with(TemporalAdjusters.lastDayOfMonth()); // 2014-12-31
        // 取下一天：
        LocalDate firstDayOf2015 = lastDayOfThisMonth.plusDays(1); // 变成了2015-01-01
        // 取2015年1月第一个周一，这个计算用Calendar要死掉很多脑细胞：
        LocalDate firstMondayOf2015 = LocalDate.parse("2015-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); // 2015-01-05
    }

}
