import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: tk.zou
 * @Description:
 * @Date: 2021-07-26 15:31
 * @Version: 1.0.0
 */


public class BigDecimalDemo {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        BigDecimal c = new BigDecimal("0.8");
        BigDecimal x = a.subtract(b);
        BigDecimal y = b.subtract(c);
        if (x.compareTo(y) == 0){
            System.out.println(true);
        }
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        System.out.println(date);
    }
}
