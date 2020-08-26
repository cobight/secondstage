package cn.cobight.MultiThread.demo2;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MTPool implements Runnable{
    /**
    * @ClassName MTPool
    * @Author cobight
    * @Date 2020/8/24
    * @Description //TUDO
    * @Version 1.0
    **/
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"多线程正在执行业务");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MTPool mtPool = new MTPool();
        //通过 Executors获取一个定长的线程池  定长为3 超过线程池长度时就会等待
        ExecutorService executorService = Executors.newFixedThreadPool(3);//返回一个执行器的服务类
        //使用线程池启动线程
        executorService.execute(mtPool);//----无返回值，只支持runnable
        //使用线程池的submit和execute有什么区别
        executorService.submit(mtPool);//----有返回值，支持callable和runnable

        //线程池使用完毕，需要关闭
        //executorService.shutdown();//执行完关闭
        executorService.shutdownNow();//立即关闭，杀死！
        System.out.println("giao");
    }
}
