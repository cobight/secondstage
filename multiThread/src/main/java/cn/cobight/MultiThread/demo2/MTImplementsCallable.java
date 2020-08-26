package cn.cobight.MultiThread.demo2;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MTImplementsCallable implements Callable {
    /*
    * 实现runnable和实现callable的区别
    * 1.一个使用run方法，一个使用call方法来执行多线程的业务
    * 2.run方法没有返回值，call方法有返回值
    * 3.run方法没有异常，call方法有异常
    * 4.run启动借助thread类，而call方法启动借助thread 借助于Future
    * */
    @Override
    public Object call() throws Exception {
        //UUID java提供随机字符串一个类可以静态方法randomUUID返回一个字符串对象
        String randomStr = UUID.randomUUID().toString();
        System.out.println(Thread.currentThread().getName()+"多线程正在执行....随机字符串为："+randomStr);
        return randomStr;
    }

    public static void main(String[] args) {
        MTImplementsCallable mtImplementsCallable = new MTImplementsCallable();
        //futureTask
        FutureTask<String> futureTask = new FutureTask<String>(mtImplementsCallable);
        //因为FutureTask  间接实现了Runnable接口， 可以借助于Thread启动
        new Thread(futureTask).start();

        try {
            String returnVal = futureTask.get();
            System.out.println("返回结果是："+returnVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
