package cn.cobight.MultiThread.demo4;

/**
* @ClassName AnonymousInnerClass
* @Author cobight
* @Date 2020/8/24 18:30
* @Description //TUDO
* @Version 1.0
**/

public class AnonymousInnerClass {
    public static void main(String[] args) {
        new Thread(){//重写run方法
            @Override
            public void run() {
                System.out.println("线程开始执行.....");
            }
        }.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程开始执行...");
            }
        }).start();
        //new Thread (() -> System.out.println("子线程开启！")).start();jdk太低
    }

}











