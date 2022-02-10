package java基础.枚举;

/**
 * @Author: tk.zou
 * @Description:
 * @Date: 2022-02-10 20:12
 * @Version: 1.0.0
 */


public enum Week {
    //本文的枚举类变量，枚举类实例，name属性指的就是MONDAY
    //该类的具体实例变量
    MONDAY(1,"星期一"),
    TUESDAY(2,"星期二"),
    WEDNESDAY(3,"星期三"),
    THURSDAY(4,"星期四"),
    FRIDAY(5,"星期五"),
    SATURDAY(6,"星期六"),
    //最后一个类型必须要用分号结束
    SUNDAY(7,"星期日");

    //该枚举类的成员变量/属性（即构成每一个枚举实例的属性）
    private int num;
    private String desc;

    /**
     * 构造方法必然是private修饰的
     * 就算不写，也是默认的
     *
     * @param num
     * @param desc
     */
    private Week(int num, String desc) {
        this.num = num;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public int getNum() {
        return num;
    }
}
