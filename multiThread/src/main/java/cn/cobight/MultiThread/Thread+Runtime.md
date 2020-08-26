# Runtime类

## exec

模拟cmd的ping

```java
Runtime runtime = Runtime.getRuntime();
InputStream inputStream = null;
BufferedReader bufferedReader = null;
try {
    //exec = execute 执行windows下cmd中的命令，获取当前命令运行进程对象

    Process process = runtime.exec("ping " + ip);
    //获取到该对象执行命令的结果，放入输入流
    inputStream = process.getInputStream();
    //InputStreamReader 把字节流转换字符流
    //字符输入流 readLine  按行读取
    bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
    String str = null;
    while ((str = bufferedReader.readLine()) != null) {
        //System.out.println("执行结果："+str);
        if (str.contains("TTL")) {
            System.out.println(ip + "ping 通了");
            return;
        }
    }
    catch (IOException e)    
    {    
        e.printStackTrace();    
    }    
}   
```

## shutdownhook

```

public class ShutdownHookTest {
 
    public static void main(String[] args) {
        System.out.println("==============application start================");
 
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("--------------hook 1----------------");
        }));
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("--------------hook 2----------------");
        }));
 
        System.out.println("==============application end================");
    }
}
```

## 总结

Runtime用处非常多，偏底层

比如gc调用

![img](https://img-blog.csdnimg.cn/20190425211429631.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2ZlbmdsbGxsZQ==,size_16,color_FFFFFF,t_70)

加载jar文件

![img](https://img-blog.csdnimg.cn/20190425211532398.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2ZlbmdsbGxsZQ==,size_16,color_FFFFFF,t_70)

Runtime功能强大，但需要合理利用，很多攻击是通过Runtime执行的漏洞

但是使用shutdownhook还是很方便的，用来做停止任务的后续处理。

# Thread类

## 先继承后调用

用类继承Thread然后new对象然后调用，或者new 类名().start直接调用

```java
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
```

## 实现Runnable

准备运行内容，用thread执行

```java
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
```

## 实现callable

有返回值的callable

get方法有阻塞作用，获取返回值并抛异常用的

```java
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
```

## 直接写内容跑的



```java
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

//jdk7以上可以这么写
new Thread (() -> System.out.println("子线程开启！")).start();
 
```



## 线程池

```java
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
        executorService.shutdown();//执行完关闭,非阻塞，只是提交了个结束线程池的任务
        //executorService.shutdownNow();//立即关闭，杀死所有线程！会抛异常
    }
}
```



## synchronized锁与加减法

一个静态变量 a =1000，两个线程+1，两个线程-1，最后返回1000

```java
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
        synchronized (lock) {//输出的时候不加锁老是出错
            System.out.println(a+"\t"+dowhat);
        }
    }
    public static void main(String[] args) {
        new demo1(1).start();
        new demo1(1).start();
        new demo1(-1).start();
        new demo1(-1).start();
    }
}
```

## 死锁

连锁的死锁

```java
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
```

三个及以上的环锁构成死锁