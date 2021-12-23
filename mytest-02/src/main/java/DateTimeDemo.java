import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: tk.zou
 * @Description: DateTime工具类的使用
 * @Date: 2021-07-23 15:46
 * @Version: 1.0.0
 */

public class DateTimeDemo {

    public static void main(String[] args) {
        DateTime dateTime = new DateTime();
        System.out.println(dateTime);

        String time = dateTime.toString("yyyy-MM-dd hh:mm:ss.SSSa");
        System.out.println(time);

        String dateUp = dateTime.toString("YYYY-MM-dd HH:mm:ss");
        System.out.println(dateUp);

//        Date date = new Date();
//        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
//        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
//        System.out.println(sdf.format(date));

        //方式1：直接获取，也可以使用上述的dateTime类
        Date date = new Date();
        System.out.println(date);

        //方式2：按照特定格式输出
        //先确定格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        //再输出
        System.out.println(simpleDateFormat.format(date));
    }


}
