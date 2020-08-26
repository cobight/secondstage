package cn.cobight.MultiThread.DoubleLockDead;

/**
 * @ClassName DeadLockDemo
 * @Description TODO
 * @Author cobight
 * @CreateTime 2020/8/25 15:00
 * @Version 1.0
 **/
public class DeadLockDemo implements Runnable {
    private Father father = new Father();
    private Son son = new Son();
    private boolean isFather;

    private static Object lockA= new Object();
    private static Object lockB= new Object();
    @Override
    public void run() {
        if (isFather){
            synchronized (lockA){
                //先说后拿
                father.say();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB){
                    father.get();
                }

            }

        }else {
            synchronized (lockB){
                //先说后拿
                son.say();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockA){
                    son.get();
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLockDemo deadLockDemo = new DeadLockDemo();
        deadLockDemo.isFather=true;
        new Thread(deadLockDemo).start();
        DeadLockDemo deadLockDemo1= new DeadLockDemo();
        deadLockDemo1.isFather=false;
        new Thread(deadLockDemo1).start();

    }
}
