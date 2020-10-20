# ioc

IOC(Inversion of Control) 控制反转，即“不用打电话过来，我们会打给你”。

两种实现： 依赖查找（DL）和依赖注入（DI）。

```xml
<spring.version>4.2.4.RELEASE</spring.version>

	<!--ioc包-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
      <version>${spring.version}</version>
    </dependency>
```



# 类

## entity

```java
public class User {
    private Integer userId;
    private String userName;
    private String realName;
    private Integer age;
	//有参无参构造方法
	//toString
}
```

## dao

```java
public interface UserDao {
    User getById(int userId);
}
public class UserDaoImpl implements UserDao {
    @Override
    public User getById(int userId) {
        if (userId==9527)return new User(9527,"唐伯虎","周星驰",20);
        return null;
    }
}
```

## service

```java
public interface UserService {
    User getById(int userId);
}
public class UserServiceImpl implements UserService {
    private UserDao ud;
    getter and 
    @Override
    public User getById(int userId) {
        return ud.getById(userId);
    }
}
```

## controller

```java
public class UserController {
    private UserService us;
    private Integer userId;
    getter and setter...
    public UserController() {
    }

    public UserController(UserService us, Integer userId) {
        this.us = us;
        this.userId = userId;
    }
    /*获取用户*/
    public void getUser(){
        User user = us.getById(userId);
        if (user!=null){
            System.out.println(user);
        }else {
            System.out.println("用户不存在");
        }
    }
}
```

# setting注入

## 配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
<!--xsd : xml schema definition 一定引入了定义，里面的标签就不可以随便写  和dtd-->
<!--  使用IOC（Inversion of Control控制反转）原来需要自己实例化对象，现在springioc容器提供方法，
      不用自己去new对象，把我们要使用的对象通过bean标签配置，让ioc容器帮我们实例化对象，控制权反转给了IOC容器，
      所以叫控制反转
            ICO又叫（Dependency Injection）依赖注入 userController 依赖userService
                userService 又依赖 userDao 我们使用springmvc 容器提供的方式，把依赖的一个类注入到要依赖的类中，这种就叫依赖
        3种注入：
            setter注入  constructor注入  注解注入

 -->
<!--  id不能重复-->
    <!-- scope =  prototype 原型  singleton  单例-->
    <bean id="userController" class="cn.cobight.ioc.controller.UserController" scope="prototype">
        <property name="us" ref="userService"></property>
        <property name="userId" value="9527"></property>
    </bean>
    <!--接口不可以交给spring管理-->
    <!--自动装配 autowire （自动装配引用类型，基本数据类型无法自动装配）
       类型：
               1 根据类型装配  byType  根据接口类型
              2, 根据名称装配 byName   根据属性名称
              3, 不使用自动装配
              4， 根据构造自动装配
    -->
    <bean id="userService" class="cn.cobight.ioc.service.UserServiceImpl">
        <property name="ud" ref="userDao"></property>
    </bean>
    <bean id="userDao" class="cn.cobight.ioc.dao.UserDaoImpl">
    </bean>
</beans>
```



```java
public class IocTest {

    @Test
    public void testUser(){
//        UserController uc=new UserController();
//        uc.getUser();
        //使用spring提供加载配置文件ClassPathXmlApplicationContext 加载配置文件，读配置为资源，解析资源，
        //生成bean定义，注册到BeanFactory ，有BeanFactory管理bean  初始化上下文对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //调用父类接口中beanFactory
        UserController userController =(UserController)applicationContext.getBean("userController");
        UserController userController1 =(UserController)applicationContext.getBean("userController");
        UserController userController2 =(UserController)applicationContext.getBean("userController");
        //所有交给spring管理的对象默认都是单例模式
        System.out.println(userController==userController1);
        System.out.println(userController==userController2);
        System.out.println(userController1==userController2);
        userController.getUser();
        userController1.getUser();
        userController2.getUser();

    }
}
```

## out

配置scope="prototype"时 改变了默认单例情况，所以如果是单例，将会是三个true

```
false
false
false
User{userId=9527, userName='唐伯虎', realName='周星驰', age=20}
User{userId=9527, userName='唐伯虎', realName='周星驰', age=20}
User{userId=9527, userName='唐伯虎', realName='周星驰', age=20}
```

# 构造注入

## 配置一  index控制

index是构造函数的参数位置，value和ref控制着参数

```xml

    <bean id="userController" class="cn.cobight.ioc.controller.UserController" scope="prototype">
<!--    通过设置构造函数的参数位置来注入    -->
        <constructor-arg index="0" ref="userService"></constructor-arg>
        <constructor-arg index="1" value="9527"></constructor-arg>
    </bean>
    
    <bean id="userService" class="cn.cobight.ioc.service.UserServiceImpl">
<!--        <property name="ud" ref="userDao"></property>-->
        <constructor-arg index="0" ref="userDao"></constructor-arg>
    </bean>
或者
<bean id="userService" class="cn.cobight.ioc.service.UserServiceImpl" autowire="constructor">
<!-- byType按类型装配，byName就需要下面的userDao与实体类entity成员变量对应 -->    
<!--        <property name="ud" ref="userDao"></property>-->
<!--        <constructor-arg index="0" ref="userDao"></constructor-arg>-->
    </bean>

    <bean id="userDao" class="cn.cobight.ioc.dao.UserDaoImpl">
    </bean>

```

## 配置二  name控制

name是参数的变量名，value和ref控制着参数

```xml
<bean id="userController" class="cn.cobight.ioc.controller.UserController" scope="prototype">
    <constructor-arg name="us" ref="userService"></constructor-arg>
    <constructor-arg name="userId" value="9527"></constructor-arg>
</bean>
```



# 注解注入

```xml
 <!--开启组件扫描 component-scan 组件扫描 扫描配置包下的所有类，看谁的类头上有spring注解，交给IOC容器管理-->
    <context:component-scan base-package="com.aaa.ioc"></context:component-scan>
    <!--注解注入-->
    <!-- @Controller  控制层 注解 专门负责注解控制层的类，交给springioc容器管理
         @Service  服务层注解  专门负责注解服务层的类，交给springioc容器管理
         @Repository  Dao层注解  专门负责注解DAO层的类，交给springioc容器管理
         @Component   不属于三层注解  专门负责注解不在三层之内的类，交给springioc容器管理

         @Autowire  spring提供 依赖注入注解  默认按照byType 方式注入
         @Resource  javax提供的注解   (type=xxx.class) 按类型注入  (name="") 按名称注入
     -->
```

## 关羽出战demo

### 类

```java
public interface Horse {
    void run();
}
@Component
public class ChiTu implements Horse {
    @Override
    public void run() {
        System.out.println("兔马快如闪电，日行千里，夜行八百");
    }
}
public interface Weapon {
    void attack();
}
@Component
public class QingLong implements Weapon {
    @Override
    public void attack() {
        System.out.println("青龙刀，砍敌如切菜");
    }
}

@Controller("userC")  //相当于前两个项目中中的bean
@Scope("prototype")
public class Person {
    @Value("关羽")
    private String name;
    @Autowired
    private ChiTu chiTu;
    @Autowired
    private QingLong qingLong;

    public void fight(){
        System.out.println(name+"出征了");
        chiTu.run();
        qingLong.attack();
    }
}
```

### xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">
    <!--开启组件扫描 component-scan 组件扫描 扫描配置包下的所有类，看谁的类头上有spring注解，交给IOC容器管理-->
    <context:component-scan base-package="cn.cobight.ioc.demo1"></context:component-scan>

</beans>
```

### test


```java
ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//调用父类接口中beanFactory
Person userController =(Person)applicationContext.getBean("userC");
userController.fight();
```

## # 打印机

@Autowired注解对两个实现类的抉择

若是变量名为对应实现类的开头小写名字（a5Paper、a4Paper）则不需要@qualifier
若不是，那就需要@Quolifier（实现类开头小写的变量名）

```java
@Autowired//默认byType
//@Qualifier("a4Paper")//@Autowired + Qualifier = byName
//private Paper paper;
private Paper a4Paper;//@Autowired + 变量名对应实现类（开头小写） = byName
```

或者用更高级的Resource注解，若是有两个及以上的实现类，则通过name属性实现Autowired + Qualifier的功能

```java
@Resource(name = "a4Paper")
private Paper paper;
```



### 类

```java
public interface Lnk {
    void setColor();
}
@Component
public class ColorLnk implements  Lnk {
    @Override
    public void setColor() {
        System.out.println("使用彩色打印...");
    }
}
public interface Paper {
    void  setPaper();
}

@Component
public class A4Paper implements Paper {
    @Override
    public void setPaper() {
        System.out.println("使用A4纸打印...");
    }
}

@Component
public class A5Paper implements Paper {
    @Override
    public void setPaper() {
        System.out.println("使用A5纸打印...");
    }
}

@Controller("printer")  //相当于前两个项目中中的bean
@Scope("prototype")
public class Printer {
    @Value("打印机1号")
    private String name;
<!--若是变量名为对应实现类的开头小写名字（a5Paper、a4Paper）则不需要@qualifier
    若不是，那就需要@Quolifier（实现类开头小写的变量名）-->    
    @Autowired
    //@Qualifier("a4Paper")
    //private Paper paper;
    private Paper a4Paper;
    @Autowired
    private Lnk colorLnk;
    public void print(){
        System.out.println(name+"开始工作");
        a4Paper.setPaper();
        colorLnk.setColor();
    }
}

<context:component-scan base-package="cn.cobight.ioc.demo2"></context:component-scan>
```

### 测试类

```java
@Test
public void testPrinter(){
    //        UserController uc=new UserController();
    //        uc.getUser();
    //使用spring提供加载配置文件ClassPathXmlApplicationContext 加载配置文件，读配置为资源，解析资源，
    //生成bean定义，注册到BeanFactory ，有BeanFactory管理bean  初始化上下文对象
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    Printer printer = (Printer)applicationContext.getBean("printer");
    printer.print();
}
```

