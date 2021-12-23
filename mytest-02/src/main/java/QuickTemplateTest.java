import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @Author: tk.zou
 * @Description:
 * @Date: 2021-08-09 11:05
 * @Version: 1.0.0
 */


public class QuickTemplateTest {
    int a = 1;
    int b = 2;
    String ab = "svv";
    /**
     * psf
     * psfi:int
     * psfs:String
     */
    public static final int i = 10;
    public static final String tkzou = "tkzou";

    public void myMethod() {
        System.out.println("mytestmethod~~");

    }

    public static void main(String[] args) {
        /**
         * sout:
         * sout
         * soutp
         * soutv
         * soutm
         * "abc".sout
         */
        int c = 2;
        System.out.println("c = " + c);
        System.out.println("QuickTemplateTest.main");
        System.out.println("args = " + Arrays.deepToString(args));
        System.out.println(c);
        int[] nums = new int[]{1, 2, 3, 4, 5};
        for (int i = 0; i < nums.length; i++) {
            System.out.println("i = " + i);
        }
        for (int num : nums) {
            System.out.println("nums = " + num);
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            System.out.println("nums = 111" + num);
        }

        /**
         * list：
         * list.for
         * list.fori
         * list.forr
         */
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        for (Integer list1 : list) {
            System.out.println(list1);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        //使用迭代器
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        /**
         * ifn
         */
        String str = "tkzou";
        if (str == null) {
            System.out.println("该字段不存在");
        }


    }
}
