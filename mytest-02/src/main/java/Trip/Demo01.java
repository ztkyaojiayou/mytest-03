package Trip;

import org.apache.commons.lang.ArrayUtils;

/**
 * @Author: tk.zou
 * @Description:
 * @Date: 2021-12-23 18:15
 * @Version: 1.0.0
 */


public class Demo01 {
    public static void main(String[] args) {
        int[] array = {1, 2, 3};
        boolean res = ArrayUtils.contains(array, 1);
        System.out.println(res);
    }
}
