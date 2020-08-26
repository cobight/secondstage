package cn.cobight.MultiThread.demo1;

public class PingIpExtendsThread extends Thread {
    //定义一个类的成员变量 ip
    private String ip;

    public PingIpExtendsThread(String ip) {
        this.ip = ip;
    }

    @Override
    public void run() {
        PingDemo.pingIp(ip);
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            //多线程的启动方式  start() 一次启动20个线程，同时ping  ip
            new PingIpExtendsThread("192.168.1."+i).start();
        }
        System.out.println("main线程是否执行...............");
    }
}
