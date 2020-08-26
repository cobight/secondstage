package cn.cobight.MultiThread.demo2;

public class MTExtendsThread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"多线程开始执行！");
    }

    public static void main(String[] args) {
        new MTExtendsThread().start();
        new MTExtendsThread().start();
        new MTExtendsThread().start();
    }
}
