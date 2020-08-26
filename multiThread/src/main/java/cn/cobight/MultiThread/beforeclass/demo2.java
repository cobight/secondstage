package cn.cobight.MultiThread.beforeclass;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @ClassName demo2
 * @Description TODO
 * @Author cobight
 * @CreateTime 2020/8/25 8:45
 * @Version 1.0
 **/
public class demo2 implements Callable {
    static Object lock = new Object();
    static int a=1000;
    private int dowhat;


    public void setDowhat(int dowhat) {
        this.dowhat = dowhat;
    }

    public  void dowhat(int k){
        a++;
    }
    @Override
    public Object call() throws Exception {
        synchronized (lock){
            for (int i = 0; i < 100; i++) {

            }
            return a;
        }
    }

    /*使用多线程知识完成，对静态变量 a=1000 进行加和减，要求一共四个线程，两个线程线程
对a进行加100次的操作，两个线程对a进行减100次的操作，最终实现a的值不发生变化（提示：
要使用同步锁）*/

    public static void main(String[] args) {
        demo2 d= new demo2();
        d.setDowhat(1);
        FutureTask<Integer> futureTask = new FutureTask<>(d);
        new Thread(futureTask).start();

    }

}
