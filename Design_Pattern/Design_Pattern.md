# 具体内容

## 1  设计模式概念：

​       模式是一套被反复使用、多数人知晓的、经过分类编写的、代码设计经验的总结；它不是语法规定，而是一套用来提高代码可复用性、可维护性、可读性、稳健性以及安全性的解决方案。

## 2 设计模式的作用：

1，可以提高程序员的思维能力、编程能力和设计能力。
2，使程序设计更加标准化、代码编制更加工程化，使软件开发效率大大提高，从而缩短软件的开发周期。
3，使设计的代码可重用性高、可读性强、可靠性高、灵活性好、可维护性强。

## 3  设计模式类别：

分为三大类：
创建型模式（5种）：**工厂方法模式，抽象工厂模式，单例模式**，建造者模式，原型模式。
结构型模式（7种）：适配器模式，装饰器模式，**代理模式**，外观模式，桥接模式，组合模式，享元模式。
行为型模式（11种）：策略模式、模板方法模式、观察者模式、迭代子模式、责任链模式、命令模式、备忘录模式、状态模式、访问者模式、中介者模式、解释器模式。

## 4  设计模式遵循的原则有6个：

1、开闭原则（Open Close Principle）
　　对扩展开放，对修改关闭。
2、里氏代换原则（Liskov Substitution Principle）
　　只有当衍生类可以替换掉基类，软件单位的功能不受到影响时，基类才能真正被复用，而衍生类也能够在基类的基础上增加新的行为。**如果对每一个类型为S的对象o1，都有类型为T的对象o2，使得以T定义的所有程序P在所有的对象o1代换o2时，程序P的行为没有变化，那么类型S是类型T的子类型。**在软件中将一个基类对象替换成它的子类对象，程序将不会产生任何错误和异常，反过来则不成立，如果一个软件实体使用的是一个子类对象的话，那么它不一定能够使用基类对象。
3、依赖倒转原则（Dependence Inversion Principle）
　　这个是开闭原则的基础，对接口编程，依赖于抽象而不依赖于具体。
4、接口隔离原则（Interface Segregation Principle）
　　使用多个隔离的接口来降低耦合度。
5、迪米特法则（最少知道原则）（Demeter Principle）
　　一个实体应当尽量少的与其他实体之间发生相互作用，使得系统功能模块相对独立。
6、合成复用原则（Composite Reuse Principle）
　　原则是尽量使用合成/聚合的方式，而不是使用继承。继承实际上破坏了类的封装性，超类的方法可能会被子类修改。

# 工厂模式（Factory）

工厂模式属于创建型设计模式，它提供了一种创建对象的最佳方式。隐藏复杂的逻辑处理过程， 只关心执行结果。**直接用new**可以完成的不需要用工厂模式

需要生成**复杂对象**的地方使用

## 单一工厂

**一个工厂控制所有返回值**

定义一个接口类

```java
public interface SoftwareTechnology {
	void study();
}
```

编写实现类

```java
public class JavaDevelop implements SoftwareTechnology {
	@Override
	public void study() {
		// TODO Auto-generated method stub
        System.out.println("经过了努力，学会了JAVA");
	}
}
public class PythonDevelop implements SoftwareTechnology {
	@Override
	public void study() {
		// TODO Auto-generated method stub
		 System.out.println("经过了努力，学会了 Python");
	}
}
```

创建工厂

```java
public class AAAFactory {
	public SoftwareTechnology studyST(int code){
		if(code==1){
			return new JavaDevelop();
		}else if(code==2){
			return new PythonDevelop();
		}else{
			return null;
		}
	}
}
```

测试

```java
public static void main(String[] args) {
    SoftwareTechnology javaDevelop = new AAAFactory().studyST(1);
    javaDevelop.study();
    SoftwareTechnology PythonDevelop = new AAAFactory().studyST(2);
    PythonDevelop.study();
}
```

## 工厂方法模式

**一个工厂标准，多个分厂**

工厂方法模式优点：不违反开闭原则，加需求只需再开一个分厂就行

工厂方法模式缺点：代码复杂度提高，分厂越写越多，编译会慢



工厂（方法）模式：工厂方法模式定义一个用于创建对象的接口，让子类决定实例化哪一个类。工厂方法使一个类的实例化延迟到其子类。
   概念解释： 工厂本身不再创建产品，而是规定了工厂规范，即工厂接口，而将产品创建都交给子工厂创建。
     优点：  遵循了开闭原则（不需要修改工厂类，就可以增加产品）
                   解耦，职责单一（每个工厂只负责创建对应的产品）
     缺点：  增加系统复杂度（每新加一个产品需要新加一个工厂）

 1,定义工厂接口

```java
public interface GBFactory {
	SoftwareTechnology studySt();
}
```

2，编写工厂实现类

```java
public class ZhiYouFactory implements GBFactory {
    @Override
    public SoftwareTechnology studySt() {
        // TODO Auto-generated method stub
        return new JavaDevelop();
    }
}
public class ChuanZhiFactory implements GBFactory {
    @Override
    public SoftwareTechnology studySt() {
        // TODO Auto-generated method stub
        return new BigDataDevelop();
    }
}
```

3，测试

```java
public static void main(String[] args) {
    // TODO Auto-generated method stub
    GBFactory factory=new ChuanZhiFactory();
    factory.studySt().study();
}
```

## 抽象工厂

​    **抽象工厂是工厂方法的升级版，为相关或者相互依赖的对象提供一个接口，而且无须指定他们的具体实现类。**

​      **概念解释：抽象工厂模式相对于工厂方法模式来说，就是工厂方法模式是针对一个产品系列的，而抽象工厂模式是针对多个产品系列的，即工厂方法模式是一个产品系列一个工厂类，而抽象工厂模式是多个产品系列一个工厂类**

**优点：**当一个产品族中的多个对象被设计成一起工作时，它能保证客户端始终只使用同一个产品族中的对象。

**缺点：**难以支持新种类的产品。因为抽象工厂接口确定了可以被创建的产品集合，所以难以扩展抽象工厂以生产新种类的产品。



一个工厂返回各种抽象实例，一个抽象实例对应各种具体实例

1，定义抽象接口（包括简单和工厂方法）

```java
public interface AAAAbstractFactory {//工厂类的接口定义
	DiggerTechnolog teachDT();//定义一个抽象返回
}
public interface DiggerTechnolog {//实体类定义
	void study();
}
public class SmallDigger implements  DiggerTechnolog{//实体类实现
    @Override
    public void study() {
        System.out.println("头悬梁锥刺股，废寝忘食，努力学习小型挖掘机技术，升职加薪，出任CTO，赢取白富美，走向人生巅峰...");
    }
}
```

  2，编写实现工厂

```java
public class ShenZhenAAAFactory implements AAAAbstractFactory {//实现各种的抽象返回实例
   @Override
   public SoftwareTechnolog teachST() {
       return new BigDataDevelop();
   }
   @Override
   public DiggerTechnolog teachDT() {
       return new SmallDigger();
   }
}
```

3，测试两种方式 

```java
public class AAAAbstractFactoryTest {
    public static void main(String[] args) {
        AAAAbstractFactory aaaAbstrcatFactory = new ShenZhenAAAFactory();
        DiggerTechnolog diggerTechnolog = aaaAbstrcatFactory.studyDT();
        SoftwareTechnolog softwareTechnolog = aaaAbstrcatFactory.studyST();
        diggerTechnolog.study();
        softwareTechnolog.study();
    }
}
```

总结：
       无论是简单工厂模式，工厂方法模式，还是抽象工厂模式，他们都属于工厂模式，在形式和特点上也是极为相似的，他们的最终目的都是为了解耦，让类的创建和使用过程实现松耦合。







# 单例（态）模式（Singleton）

* 一种常用的软件设计模式。所谓单例，就是让一个类在项目中只存在一个对象，即使用到这个类的地方很多，也只存在一个对象。

​      好处：

​		1、节省内存

​		2、有些情况下不用单例模式可能会引起代码逻辑错误(例如：网站访问量统计功能)

* 核心作用∶ 保证一个类只有一个实例，并且提供一个访问该实例的全局访问点。



* 常见应用场景∶

  * Windows的Task Manager(任务管理器）就是很典型的单例模式

  * windows的Recycle Bin(回收站）也是典型的单例应用。在整个系统运行过程中，回收站一直维护着仅有的一个实例。

  * 项目中，读取配置文件的类，一般也只有一个对象。没有必要每次使用配置文件数据，每次new一个对象去读取。

  * 网站的计数器，一般也是采用单例模式实现，否则难以同步。

  * 应用程序的日志应用，一般都何用单例模式实现，这一般是由于共享的日志文件一直处于打开状态，因为只能有一个实例去操作，否则内容下好追加。

  * 数据库连接池的设计一般也是采用单例模式，因为数据库连接是一种数据库资源。

  * 操作系统的文件系统，也是大的单例模式实现的具体例子，一个操作系统只能有一个文件系统。

  * Application也是单例的典型应用( Servlet编程中会涉及到)

  * 在Spring中，每个Bean默认就是单例的，这样做的优点是Spring容器可以管理

  * 在servlet编程中，每个Servlet也是单例

  * 在spring MVC框架/struts1框架中，控制器对象也是单例

## 单例模式的优点∶

* 由于单例模式只生成一个实例，减少了系统性能开销，当一个对象的产生需要比较多的资源时，如读取配置、产生其他依赖对象时，则可以通过在应用启动时直接产生一个单例对象，然后永久驻留内存的方式来解决
* 单例模式可以在系统设置全局的访问点，优化环共享资源访问，例如可以设计一个单例类，负责所有数据表的映射处理

## 常见的五种单例模式实现方式∶

主要∶

* 饿汉式（线程安全，调用效率高。但是，不能延时加载。)
* 懒汉式（线程安全，调用效率不高。但是，可以延时加载。)

其他∶

* 双重检测锁式（由于JVM底层内部模型原因，偶尔会出问题。不建议使用)

* 静态内部类式(线程安全，调用效率高。但是，可以延时加载)
* 枚举单例(线程安全，调用效率高，不能领正时加载)

## 饿汉式实现（单例对象立即加载）

```java
public class singleton {
    //1, 静态(伴随着类的加载而产生，只产生一次)私有（不允许在其他地方进行调用）该类的对象属性
    private static singleton s = new singleton();//创建一个实例化对象
    //2，只提供私有构造  保证在其他地方通过new不能实例化对象
    private singleton(){};//私有化构造器，这样外部就不能用构造方法new对象了，这样能保证通过getInstance方法获取唯一的单例的对象
    //想要获取本对象，就只能调用getInstance，不管谁调用，我们用的都是同一个对象
    public static singleton getInstance(){return s;}
	//3，提供静态的(一定要加，否则无法调用，因为类不能实例化对象)公共的方法 ，创建或者返回该类的对象
    public static void main(String[] args) {
        singleton instance = singleton.getInstance();
        singleton instance1 = singleton.getInstance();
        System.out.println(instance == instance1);
    }
}
```

## 懒汉式实现（单例对象实例化时加载）

```java
/**
 * fileName:SlackerSingleton
 * description: 懒汉模式
 *            懒汉和饿汉的区别：
 *            1，类加载速度    懒汉加载速度会快，类加载时，属性没有被实例化对象，饿汉反之
 *            2，获取对象速度   懒汉比较慢，因为方法调用时，要实例化对象，饿汉反之
 *            3，实例在线时长    懒汉实例在线时长理论上比饿汉在线时长少（8点-20点在线，8点项目启动 饿汉就会实例化单例类，懒汉到12点调用获取对象方法，12被实例化，12-20存在于内存之中）
 *
 * author:zz
 * createTime:2020/9/7 9:25
 * version:1.0.0
 */
public class SlackerSingleton {

    //1,私有的构造
    private SlackerSingleton(){
        if (slackerSingleton!=null){//防止通过反射创建实例
            throw new RuntimeException("SlackerSingleton已经构造了实例");
        }
    }
    //2,私有静态该类的属性
    private static volatile SlackerSingleton slackerSingleton = null;
        //volatile  1,多线程可见  2,防止指令重排
        // int a=1;
        // int b=2;
        // int c=3;
       //  volatile  int  d=a+b;   18,19,20,21 正常顺序  指令重排，为了提高计算机运行速度  保证结果正确情况下  20,19,18,21 .....
    //实例化SlackerSingleton时，经过3个步骤     A  a = new A()
        // 1),在jvm的内存模型的堆heap开辟空间
        // 2),使用该空间给初始化对象
        // 3),使用对象的引用指向该空间
        // 加上 volatile一定按照  1,2,3顺序执行，不加有可能返回的对象中还没有被初始化
    //3,公共的静态的返回该类对象的方法  双重检查锁
    public static SlackerSingleton  getSlackerSingleton(){
        //不考虑多线程，懒汉就这样写，实际使用过程中，一定要考虑多线程
         if(slackerSingleton==null){//多个线程同时进入该方法 t1 t2 t3
            synchronized (SlackerSingleton.class) {//SlackerSingleton.class 做锁，因为SlackerSingleton是单个的
                //加锁只能保证同一时刻是有一个线程执行，但是不能保证只创建一个对象，因为 t1释放锁后，t2和t3还可以拿锁，再次创建实例
                if(slackerSingleton==null) {//t1释放锁后，t2或者t3再次判断，防止实例化多个对象
                    slackerSingleton = new SlackerSingleton();
                }
            }
        }
        return slackerSingleton;
    }
    public static synchronized SlackerSingleton  getSlackerSingleton1(){
        if(slackerSingleton == null) {//多线程的噩梦，当多个线程同时调用函数，绝对会阻塞
            slackerSingleton = new SlackerSingleton();
        }
        return slackerSingleton;
    }
    public static void main(String[] args) throws Exception{
        Class<SlackerSingleton> slackerSingletonClass = SlackerSingleton.class;
        Constructor<SlackerSingleton> c = slackerSingletonClass.getDeclaredConstructor(null);
        c.setAccessible(true);
        SlackerSingleton slackerSingleton = c.newInstance();
        SlackerSingleton slackerSingleton1 = c.newInstance();
        System.out.println(slackerSingleton);
        System.out.println(slackerSingleton1);
    }
}
```

# Volatile 关键字

​	Java语言提供了一种稍弱的同步机制，即volatile变量，用来确保将变量的更新操作通知到其他线程。当把变量声明为volatile类型后，编译器与运行时都会注意到这个变量是共享的，因此不会将该变量上的操作与其他内存操作一起重排序。volatile变量不会被缓存在寄存器或者对其他处理器不可见的地方，因此在读取volatile类型的变量时总会返回最新写入的值。

​	在访问volatile变量时不会执行加锁操作，因此也就不会使执行线程阻塞，因此volatile变量是一种比sychronized关键字更轻量级的同步机制。

​	当对非 volatile 变量进行读写的时候，每个线程先从内存拷贝变量到CPU缓存中。如果计算机有多个CPU，每个线程可能在不同的CPU上被处理，这意味着每个线程可以拷贝到不同的 CPU cache 中。

　　而声明变量是 volatile 的，JVM 保证了每次读变量都从内存中读，跳过 CPU cache 这一步。

### 当一个变量定义为 volatile 之后，将具备两种特性：

　　1.保证此变量对所有的线程的可见性，这里的“可见性”，如本文开头所述，当一个线程修改了这个变量的值，volatile 保证了新值能立即同步到主内存，以及每次使用前立即从主内存刷新。但普通变量做不到这点，普通变量的值在线程间传递均需要通过主内存（详见：[Java内存模型](http://www.cnblogs.com/zhengbin/p/6407137.html)）来完成。

　　2.禁止指令重排序优化。有volatile修饰的变量，赋值后多执行了一个“load addl $0x0, (%esp)”操作，这个操作相当于一个**内存屏障**（指令重排序时不能把后面的指令重排序到内存屏障之前的位置），只有一个CPU访问内存时，并不需要内存屏障；（什么是指令重排序：是指CPU采用了允许将多条指令不按程序规定的顺序分开发送给各相应电路单元处理）。

### volatile 性能：

　　volatile 的读性能消耗与普通变量几乎相同，但是写操作稍慢，因为它需要在本地代码中插入许多内存屏障指令来保证处理器不会发生乱序执行。

```java
/**
 * fileName:VolatileDemo1
 * description:
 * author:cobight
 * createTime:2020/9/7 20:54
 * version:1.0.0
 */
public class VolatileDemo1 extends Thread {
    private volatile boolean flag = false;
//    private  boolean flag = false;//

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "---开始运行---");
        while (!flag) ;
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "---开始运行main线程---");
        VolatileDemo1 volatileDemo1 = new VolatileDemo1();
        volatileDemo1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "---两秒后让flag改变---");
        volatileDemo1.flag = true;
    }
}

```

加不加volatile，flag都会改为true

不加volatile线程里会一直死循环，加了volatile线程会终止死循环

所以volatile

# 代理模式

## 静态代理

用代理类进行  行为操作

```java
public interface Account {
    void  queryAccountMoney();//查钱
    void  updateMoney(double num);//存取钱
}
public class Employee  implements  Account{//银行员工
    private Client client;
    public Employee(Client client) {this.client = client;}
    @Override
    public void queryAccountMoney() {
        System.out.println("代理类：准备ATM机。。。。。");
        client.queryAccountMoney();
        System.out.println("代理类：修改ATM机。。。。。");
    }
    @Override
    public void updateMoney(double num) {
        System.out.println("代理类：大额存取准备工作。。。。");
        client.updateMoney(num);
        System.out.println("代理类：善后工作。。。");
    }
}
public class Client implements Account {//
    @Override
    public void queryAccountMoney() {
        System.out.println("----------查询余额。。。。");
    }
    @Override
    public void updateMoney(double num) {
        System.out.println("----------存/取" + num + "元钱");
    }
}
public class AccountTest {
    public static void main(String[] args) {
        //实例化委托类
        Client client = new Client();
        //实例化代理类
        Employee employee = new Employee(client);//用代理类进行  行为操作
        //执行 查询余额
        employee.queryAccountMoney();
        //执行 存取前
        employee.updateMoney(200000);
    }
}
```

## JDK代理

Proxy.newProxyInstance

获取代理对象，然后执行操作

```java
public interface Account {
    void  queryAccountMoney();//查钱
    void  updateMoney(double num);//存取钱
}
public class Client implements Account {//
    @Override
    public void queryAccountMoney() {
        System.out.println("----------查询余额。。。。");
    }
    @Override
    public void updateMoney(double num) {
        System.out.println("----------存/取" + num + "元钱");
    }
}
public class JDKEmpProxy implements InvocationHandler{//代理句柄
    //委托类属性
    private Object obj;
    public JDKEmpProxy(Object obj) {//使用构造给委托类赋值
        this.obj = obj;
    }
    public Object getJdkProxy(){//获取代理对象
        //参数1  获取类加载器对象
        //参数2  委托类实现的所有接口
        //参数3  InvocationHandler 接口的实现类
        Class<?> aClass = obj.getClass();
        return Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(),this);
    }
    /**
     * @param proxy 代理类
     * @param method  委托类实现接口中的单个方法
     * @param args=argements  委托类实现接口中的单个方法的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //执行委托类实现所有接口中的所有方法，按照顺序一个一个执行
        String methodName = method.getName(); //queryAccountMoney，updateMoney
         Object obj = null;
         //判断是否是查询余额方法
        if("queryAccountMoney".equals(methodName)){
            System.out.println("代理类：准备ATM机。。。。。");
            //执行委托类方法
            obj = method.invoke(this.obj, args);
            System.out.println("代理类：修改ATM机。。。。。");
        }else {
            System.out.println("代理类：大额存取准备工作。。。。");
            //执行委托类方法
            obj = method.invoke(this.obj, args);
            System.out.println("代理类：善后工作。。。");
        }
        return obj;
    }
}
public class JDKProxyTest {
    public static void main(String[] args) {
        Client client = new Client();
        JDKEmpProxy jdkEmpProxy =new JDKEmpProxy(client);
        //多态
        Account account = (Account)jdkEmpProxy.getJdkProxy();
        account.queryAccountMoney();
        account.updateMoney(100000);
    }
}
```

