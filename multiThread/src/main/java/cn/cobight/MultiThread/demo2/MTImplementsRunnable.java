package cn.cobight.MultiThread.demo2;

public class MTImplementsRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"多线程正在执行。");
    }

    public static void main(String[] args) {
        //实现Runnable接口后，需要借助于Thread类启动
        MTImplementsRunnable mtImplementsRunnable = new MTImplementsRunnable();
        //父类引用指向子类对象  多态
        new Thread(mtImplementsRunnable).start();
        new Thread(mtImplementsRunnable).start();
        new Thread(mtImplementsRunnable).start();
    }
}
