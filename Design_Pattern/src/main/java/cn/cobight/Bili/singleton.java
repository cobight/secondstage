package cn.cobight.Bili;

/**
 * fileName:singleton
 * description:
 * author:cobight
 * createTime:2020/9/7 17:14
 * version:1.0.0
 */
public class singleton {
    private static singleton s = new singleton();
    private singleton(){};//私有化构造器，这样外部就不能用构造方法new对象了，这样能保证通过getInstance方法获取唯一的单例的对象
    //想要获取本对象，就只能调用getInstance，不管谁调用，我们用的都是同一个对象
    public static singleton getInstance(){return s;}

    public static void main(String[] args) {
        singleton instance = singleton.getInstance();
        singleton instance1 = singleton.getInstance();
        System.out.println(instance==instance1);
    }
}
