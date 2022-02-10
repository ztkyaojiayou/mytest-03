package java基础.枚举;

/**
 * @Author: tk.zou
 * @Description:
 * @Date: 2022-02-10 20:16
 * @Version: 1.0.0
 */


public class NumTest {
    public static void main(String[] args) {
        Week[] values = Week.values();
        for (Week value : values) {
            System.out.println("num：" + value.getNum()+","+ value.getDesc());
        }
    }
}
