package cn.cobight.MultiThread.beforeclass;

/**
 * @ClassName demo1
 * @Description TODO
 * @Author cobight
 * @CreateTime 2020/8/25 8:39
 * @Version 1.0
 **/
public class demo1 extends Thread {
    /*使用多线程知识完成，对静态变量 a=1000 进行加和减，要求一共四个线程，两个线程线程
对a进行加100次的操作，两个线程对a进行减100次的操作，最终实现a的值不发生变化（提示：
要使用同步锁）*/
    static Object lock = new Object();
    static int a = 1000;
    private int dowhat;

    public demo1(int dowhat) {
        this.dowhat = dowhat;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            synchronized (lock) {
                a = dowhat == 1 ? ++a : --a;
            }
        }
        synchronized (lock) {
            System.out.println(a + "\t" + dowhat);
        }
    }

    public static void main(String[] args) {
        new demo1(1).start();
        new demo1(1).start();
        new demo1(-1).start();
        new demo1(-1).start();
    }
}
