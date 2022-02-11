package java基础.枚举;

/**
 * @Author: tk.zou
 * @Description:
 * @Date: 2022-02-10 20:16
 * @Version: 1.0.0
 */


public class NumTest {
    public static void main(String[] args) {
        //可直接调用枚举实例并获取其属性
        Week friday = Week.FRIDAY;
        String desc = friday.getDesc();
        int num = friday.getNum();
        System.out.println("num:" +desc + "num:"+ num);
        //也可获取所有的枚举实例
        Week[] values = Week.values();
        //遍历每一个实例，并获取其值
        for (Week value : values) {
            //可以对应get方法获取
            System.out.println("num：" + value.getNum() + "," + value.getDesc());
            //这里的toString方法也是重写过的（使用了Switch）
            System.out.println(value.toString());
            //可以使用set方法修改枚举实例的值
            value.setDesc("修改后的值");
            //该方法是针对该枚举类本身的，即通过num的值来获取对应的枚举实例的Desc值
            String name = Week.getName(1);
            System.out.println(name);
            System.out.println("------------------");
        }
    }
}
