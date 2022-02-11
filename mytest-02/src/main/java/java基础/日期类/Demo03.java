package java基础.日期类;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: tk.zou
 * @Description:
 * @Date: 2022-02-11 16:42
 * @Version: 1.0.0
 */


public class Demo03 {
    public static void main(String[] args) {
        //方式1
        Calendar calendar = Calendar.getInstance();
        Date date1 = calendar.getTime();
        System.out.println(date1);
        //方式2
        Calendar.Builder builder =new Calendar.Builder();
        Calendar calendar1 = builder.build();
        Date date2 = calendar.getTime();
        // 简单写法
        // Date time1 = new Calendar.Builder().build().getTime();

        //格式转换--是对Date类型进行曹操作
        //先指定格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //再转换--最终是一个String类型
        String format = sdf.format(date1);
        System.out.println(format);
    }
}
