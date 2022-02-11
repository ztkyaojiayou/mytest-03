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

    //新增
    SSS(11,"SAWSF啊"),
    //若未新增该构造方法，则无法如此定义枚举类
    WWW(1111),

    SATURDAY(6,"星期六"),
    //最后一个类型必须要用分号结束
    SUNDAY(7,"星期日");



    //该枚举类的成员变量/属性（即构成每一个枚举实例的属性）,属于各个实例
    private int num;
    private String desc;

    /**
     * 构造方法必然是private修饰的
     * 就算不写，也是默认的
     * 属于各个具体实例
     * @param num
     * @param desc
     */
    private Week(int num, String desc) {
        this.num = num;
        this.desc = desc;
    }

    /**
     * 也可以定义其他构造方法
     * @param num
     */
    private Week(int num) {
        this.num = num;
    }

    /**
     * get、set方法，,属于各个具体实例
     * @return
     */
    public String getDesc() {
        return desc;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 根据num获取Desc，属于枚举类本身，即只能使用Week.getName(1)，而不能是具体某个实例类去调用，如MONDAY.getName(1)
     * 当然，还可以根据num来获取到其对应的枚举类本身，常用！！！
     * @param index
     * @return
     */
    public static String getName(int index) {
                 for (Week c : Week.values()) {
                    if (c.getNum() == index) {
                              return c.getDesc();
                           }
                    }
               return null;
    }

    /**
     * 用switch重写toString方法，提高代码健壮性，属于各个具体实例
     * @return
     */
    @Override
    public String toString() {
        //switch支持Enum类型
        switch (this) {
            case MONDAY:
                return "今天星期一";
            case TUESDAY:
                return "今天星期二";
            case WEDNESDAY:
                return "今天星期三";
            case THURSDAY:
                return "今天星期四";
            case FRIDAY:
                return "今天星期五";
            case SATURDAY:
                return "今天星期六";
            case SUNDAY:
                return "今天星期日";
            default:
                return "Unknow Day";
        }
    }
}
