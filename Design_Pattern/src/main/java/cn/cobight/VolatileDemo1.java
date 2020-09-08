package cn.cobight;

/**
 * fileName:VolatileDemo1
 * description:
 * author:cobight
 * createTime:2020/9/7 20:54
 * version:1.0.0
 */
public class VolatileDemo1 extends Thread {
//    private volatile boolean flag = false;
    private  boolean flag = false;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "---开始运行---");
        while (!flag) ;
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "---开始运行main线程---");
        VolatileDemo1 volatileDemo1 = new VolatileDemo1();
        volatileDemo1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "---两秒后让flag改变---");
        volatileDemo1.flag = true;
        System.out.println(volatileDemo1.flag);
    }
}
