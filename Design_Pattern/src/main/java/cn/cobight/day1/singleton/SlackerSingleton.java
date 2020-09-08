package cn.cobight.day1.singleton;

/**
 * fileName:SlackerSingleton
 * description: 懒汉模式
 *            懒汉和饿汉的区别：
 *            1，类加载速度    懒汉加载速度会快，类加载时，属性没有被实例化对象，饿汉反之
 *            2，获取对象速度   懒汉比较慢，因为方法调用时，要实例化对象，饿汉反之
 *            3，实例在线时长    懒汉实例在线时长理论上比饿汉在线时长少（8点-20点在线，8点项目启动 饿汉就会实例化单例类，懒汉到12点调用获取对象方法，12被实例化，12-20存在于内存之中）
 *
 * author:zz
 * createTime:2020/9/7 9:25
 * version:1.0.0
 */
public class SlackerSingleton {

    //1,私有的构造
    private SlackerSingleton(){
    }
    //2,私有静态该类的属性
    private static volatile SlackerSingleton slackerSingleton = null;
        //volatile  1,多线程可见  2,防止指令重排
        // int a=1;
        // int b=2;
        // int  c=3;
       //  volatile  int  d=a+b;   18,19,20,21 正常顺序  指令重排，为了提高计算机运行速度  保证结果正确情况下  20,19,18,21 .....
    //实例化SlackerSingleton时，经过3个步骤     A  a = new A()
        // 1),在jvm的内存模型的堆heap开辟空间
        // 2),使用该空间给初始化对象
        // 3),使用对象的引用指向该空间
        // 加上 volatile一定按照  1,2,3顺序执行，不加有可能返回的对象中还没有被初始化
    //3,公共的静态的返回该类对象的方法  双重检查锁
    public static SlackerSingleton  getSlackerSingleton(){
        //不考虑多线程，懒汉就这样写，实际使用过程中，一定要考虑多线程
         if(slackerSingleton==null){//多个线程同时进入该方法 t1 t2 t3
            synchronized (SlackerSingleton.class) {//SlackerSingleton.class 做锁，因为SlackerSingleton是单个的
                //加锁只能保证同一时刻是有一个线程执行，但是不能保证只创建一个对象，因为 t1释放锁后，t2和t3还可以拿锁，再次创建实例
                if(slackerSingleton==null) {//t1释放锁后，t2或者t3再次判断，防止实例化多个对象
                    slackerSingleton = new SlackerSingleton();
                }
            }
        }
        return slackerSingleton;
    }
}
