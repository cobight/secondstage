package cn.cobight.MultiThread.demo4;

/**
 * @ClassName WaitAndSleep
 * @Description sleep与wai区别：
 * 1    wait是Object类中的方法    sleep 是Thread类静态方法
 * 2    sleep可以不在同步块中运行，阻塞当前线程，把CPU让给其他线程，wait方法必须在同步块中智行，否则会报异常IllegalMonitorStateException
 *      （先拿到锁才可以释放锁，如果没有拿到锁，直接释放，就会出现该异常）
 * 3    sleep如果在同步块中智行，不释放锁 ， wait释放锁，并且没有其他线程唤醒时一直阻塞
 *      其他线程在唤醒等待线程时，并不是被唤醒的线程立马执行，只是有了获取锁的资格，必须等当前拿锁的线程执行完成，被唤醒的才有可能执行
 * @Author cobight
 * @CreateTime 2020/8/24 18:37
 * @Version 1.0
 **/
public class WaitAndSleep {
    static Object lock=new Object();
    static Runnable runA = new Runnable() {//定义有名称内部类，实现runnable接口，重写run方法
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("世间三千，我爱有三");
                //lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("日月与卿");
            }
        }
    };
    static Runnable runB = new Runnable() {//定义有名称内部类，实现runnable接口，重写run方法
        @Override
        public void run() {
            synchronized (lock) {

                try {
                    System.out.println("日为朝，月为暮");
                    //lock.wait();
                    //Thread.sleep(2000);
                    lock.notify();
                    System.out.println("卿为朝朝暮暮");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    };


    public static void main(String[] args) {
//        new Thread(runA).start();//有可能sout穿插
//        new Thread(runB).start();
        Thread threadA = new Thread(runA);
        threadA.setPriority(10);
        Thread threadB = new Thread(runB);
        threadB.setPriority(1);

        threadA.start();
        threadB.start();
        /*
         * 线程A优先级最高，执行一半wait阻塞
         * 线程B开始执行，释放A的锁，继续执行
         * 线程A阻塞解除，继续执行
         * wait阻塞，notify释放
         **/
    }
}
