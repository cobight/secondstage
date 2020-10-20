# AOP

在软件业，AOP为Aspect Oriented Programming的缩写，意为：[面向切面编程](https://baike.baidu.com/item/面向切面编程/6016335)，通过[预编译](https://baike.baidu.com/item/预编译/3191547)方式和运行期间动态代理实现程序功能的统一维护的一种技术。AOP是[OOP](https://baike.baidu.com/item/OOP)的延续，是软件开发中的一个热点，也是[Spring](https://baike.baidu.com/item/Spring)框架中的一个重要内容，是[函数式编程](https://baike.baidu.com/item/函数式编程/4035031)的一种衍生范型。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的[耦合度](https://baike.baidu.com/item/耦合度/2603938)降低，提高程序的可重用性，同时提高了开发的效率。

**配置实现与注解实现的执行流程是不同的**

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
    <!--aop包-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aop</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjweaver</artifactId>
      <version>1.9.4</version>
    </dependency>
```



# 用配置实现函数增强

配置及对应函数顺序影响执行流程

## 配置

bean中

* 服务层
  * id=接口类名开头小写
  * class=实现类名（默认全路径）

* 增强类
  * id=自己类名开头小写
  * class=实现类名（默认全路径）

aop中

* id=自己起的切入点名称

* expression

  * expression=“execution(public * 包名.\*.\*(..)) or .........”

    第一个*是任意返回值（基础数据类型自动装箱）

    第二个*是所有类

    第三个*是任意方法

    (..) 0-多个参数

  * expression=“execution(public * 包名.\*.XX\*(..)) or .........”

或者全了，写的对照上了，通配符也可以不要了


```xml
<!--切入点配置  regexp = regular expression    execution 切入点表达式
 修改符为public 返回值为int  第一个* 所有类  * 类下所有方法  ..0到多个参数-->
<aop:pointcut id="pcA" expression="execution(public * com.aaa.aop.service.*.save*(..)) or execution(public * com.aaa.aop.service.*.insert*(..)) or execution(public * com.aaa.aop.service.*.add*(..)) or execution(public * com.aaa.aop.service.*.update*(..)) or execution(public * com.aaa.aop.service.*.delete*(..))"></aop:pointcut>
```



## spring-config.xml

before、around、after等写的顺序不一样，执行顺序不一样

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop.xsd">
    <!--把服务层bean交给springioc容器管理-->
    <bean id="deptService" class="com.aaa.aop.service.DeptServiceImpl"></bean>
    <bean id="orderService" class="com.aaa.aop.service.OrderServiceImpl"></bean>
    <!--通知或者增强 类 交给spring管理-->
    <bean id="logHandler" class="com.aaa.aop.util.LogHandler"></bean>

    <!--aop配置-->
    <aop:config>
        <!--切入点配置  regexp = regular expression    execution 切入点表达式
         修改符为public 返回值为int  第一个* 所有类  * 类下所有方法  ..0到多个参数-->
        <aop:pointcut id="pcA" expression="execution(public * com.aaa.aop.service.*.save*(..)) or execution(public * com.aaa.aop.service.*.insert*(..)) or execution(public * com.aaa.aop.service.*.add*(..)) or execution(public * com.aaa.aop.service.*.update*(..)) or execution(public * com.aaa.aop.service.*.delete*(..))"></aop:pointcut>
        <!--切面配置   ref="logHandler" 指向切面的具体实现类（通知或者增强类）-->
        <aop:aspect ref="logHandler">
            <!--前置通知-->
            <aop:before method="beforeExecute" pointcut-ref="pcA"></aop:before>
            <!--环绕通知  相当于前置和后置一个整合，不同  1，可以决定调用不调用业务方法  2，有返回值-->
            <aop:around method="aroundExecute" pointcut-ref="pcA"></aop:around>
            <!--异常通知  出现异常时，才会被调用-->
<!--            <aop:after-throwing method="exceptionExecute" pointcut-ref="pcA" throwing="ex"></aop:after-throwing>-->
            <!--后置通知  执行业务之后，再执行的增强功能  执行修改后，保存修改日志-->
            <aop:after-returning method="saveLog" pointcut-ref="pcA"></aop:after-returning>
            <!--最终通知-->
            <aop:after method="afterExecute" pointcut-ref="pcA"></aop:after>
        </aop:aspect>
    </aop:config>
</beans>
```

## 测试的实体类与接口和实现类

dept跟order实体类entity里面啥都没

接口定义增删改查（增里面写了个空指针异常）

实现类全是sout输出

## 增强类

```java
public class LogHandler {

    /**
     * 通用日志记录功能
     */
    public void saveLog(JoinPoint joinPoint){
        //拿到链接点方法名称
        String methodName = joinPoint.getSignature().getName();
        //获取目标对象名称
        String className = joinPoint.getTarget().getClass().getName();
        //执行方法的参数
        Object[] args = joinPoint.getArgs();
        StringBuilder str = new StringBuilder("");
        if(args!=null&&args.length>0){
            for (Object arg : args) {
                str.append(arg.getClass().getName());
            }
        }
        System.out.println("切面：AfterReturning后执行->"+className+"."+methodName+"("+str.toString()+")");
        //System.out.println("-------------模拟日志记录功能2222222222，在操作"+className+"."+methodName+"方法后，记录日志"+str);
    }

    /**
     * 前置通知处理方法。。。
     * @param joinPoint
     */
    public void beforeExecute(JoinPoint joinPoint){
        //拿到链接点方法名称
        String methodName = joinPoint.getSignature().getName();
        //获取目标对象名称
        String className = joinPoint.getTarget().getClass().getName();
        //执行方法的参数
        Object[] args = joinPoint.getArgs();
        StringBuilder str = new StringBuilder("");
        if(args!=null&&args.length>0){
            for (Object arg : args) {
                str.append(arg.getClass().getName());
            }
        }
        System.out.println("切面：Before -->"+className+"."+methodName+"("+str.toString()+")");
    }


    /**
     * 环绕通知
     * @param proceedingJoinPoint
     * @return
     */
    public Object aroundExecute(ProceedingJoinPoint proceedingJoinPoint){
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        StringBuilder str = new StringBuilder("");
        if(args!=null&&args.length>0){
            for (Object arg : args) {
                str.append(arg.getClass().getName());
            }
        }
        Object obj = null;
        System.out.println("--切面：Around ----> 业务执行前->"+className+"."+methodName+"("+str.toString()+")");
        try {
            obj =  proceedingJoinPoint.proceed();  //调用业务方法
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
       // System.out.println("--------返回值："+obj);
        System.out.println("--切面：Around ----> 业务执行后，返回值："+obj);
       return obj;
    }

    /**
     * 当出现异常时，执行该方法
     * @param joinPoint
     * @param ex
     */
    public void exceptionExecute(JoinPoint joinPoint,Exception ex){
        String name = joinPoint.getSignature().getName();
        System.out.println("切面：AfterThrowing --> 在执行"+name+"方法时，出现"+ex.getClass().getName()+
                "的异常，具体描述:"+ex.getMessage());
    }

    /**
     * 无论有没有异常都会执行方法处理收尾业务
     * @param joinPoint
     */
    public void afterExecute(JoinPoint joinPoint){
        //拿到链接点方法名称
        String methodName = joinPoint.getSignature().getName();
        //获取目标对象名称
        String className = joinPoint.getTarget().getClass().getName();
        //执行方法的参数
        Object[] args = joinPoint.getArgs();
        StringBuilder str = new StringBuilder("");
        if(args!=null&&args.length>0){
            for (Object arg : args) {
                str.append(arg.getClass().getName());
            }
        }
        System.out.println("切面：After 最后执行 --> "+className+"."+methodName+"("+str.toString()+")");
        System.out.println("##########################################################");
    }
}
```

## 测试类

```java
@Test
public void testAop(){
    ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("spring-config.xml");
    DeptService deptService  = (DeptService)applicationContext.getBean("deptService");
    Dept dept = new Dept();
    deptService.addDept(dept);
    deptService.update(dept);
    deptService.delete(0);
    OrderService orderService1  = (OrderService)applicationContext.getBean("orderService");
    Order order = new Order();
    orderService1.add(order);
    orderService1.update(order);
    orderService1.delete(0);
}
```



## 输出

```java
配置的执行流程
切面：Before -->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
--切面：Around ----> 业务执行前->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
模拟部门添加
切面：Around --> 异常java.lang.NullPointerException
--切面：Around ----> 业务执行后，返回值：null
切面：AfterReturning后执行->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
切面：After 最后执行 --> com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
##########################################################
切面：Before -->com.aaa.aop.service.DeptServiceImpl.update(com.aaa.aop.entity.Dept)
--切面：Around ----> 业务执行前->com.aaa.aop.service.DeptServiceImpl.update(com.aaa.aop.entity.Dept)
模拟部门更新
--切面：Around ----> 业务执行后，返回值：0
切面：AfterReturning后执行->com.aaa.aop.service.DeptServiceImpl.update(com.aaa.aop.entity.Dept)
切面：After 最后执行 --> com.aaa.aop.service.DeptServiceImpl.update(com.aaa.aop.entity.Dept)
##########################################################
切面：Before -->com.aaa.aop.service.DeptServiceImpl.delete(java.lang.Integer)
--切面：Around ----> 业务执行前->com.aaa.aop.service.DeptServiceImpl.delete(java.lang.Integer)
模拟部门删除
--切面：Around ----> 业务执行后，返回值：0
切面：AfterReturning后执行->com.aaa.aop.service.DeptServiceImpl.delete(java.lang.Integer)
切面：After 最后执行 --> com.aaa.aop.service.DeptServiceImpl.delete(java.lang.Integer)
##########################################################
切面：Before -->com.aaa.aop.service.OrderServiceImpl.add(com.aaa.aop.entity.Order)
--切面：Around ----> 业务执行前->com.aaa.aop.service.OrderServiceImpl.add(com.aaa.aop.entity.Order)
模拟订单添加
--切面：Around ----> 业务执行后，返回值：0
切面：AfterReturning后执行->com.aaa.aop.service.OrderServiceImpl.add(com.aaa.aop.entity.Order)
切面：After 最后执行 --> com.aaa.aop.service.OrderServiceImpl.add(com.aaa.aop.entity.Order)
##########################################################
切面：Before -->com.aaa.aop.service.OrderServiceImpl.update(com.aaa.aop.entity.Order)
--切面：Around ----> 业务执行前->com.aaa.aop.service.OrderServiceImpl.update(com.aaa.aop.entity.Order)
模拟订单更新
--切面：Around ----> 业务执行后，返回值：0
切面：AfterReturning后执行->com.aaa.aop.service.OrderServiceImpl.update(com.aaa.aop.entity.Order)
切面：After 最后执行 --> com.aaa.aop.service.OrderServiceImpl.update(com.aaa.aop.entity.Order)
##########################################################
/*其实dao跟implment中方法里的参数是int，但是spring自动装箱为Integer，估计char啥的也会自动装箱*/
切面：Before -->com.aaa.aop.service.OrderServiceImpl.delete(java.lang.Integer)
--切面：Around ----> 业务执行前->com.aaa.aop.service.OrderServiceImpl.delete(java.lang.Integer)
模拟订单删除
--切面：Around ----> 业务执行后，返回值：0
切面：AfterReturning后执行->com.aaa.aop.service.OrderServiceImpl.delete(java.lang.Integer)
切面：After 最后执行 --> com.aaa.aop.service.OrderServiceImpl.delete(java.lang.Integer)
##########################################################
```

# 用注解实现函数增强

注解及对应函数顺序不影响执行流程

## spring-config.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
<!-- 开启组件扫描   -->
    <context:component-scan base-package="com.aaa.aop"></context:component-scan>
<!-- 开启切面自动代理   -->
    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>

    <bean id="deptService" class="com.aaa.aop.service.DeptServiceImpl"></bean>
    <bean id="orderService" class="com.aaa.aop.service.OrderServiceImpl"></bean>
</beans>
```



## 实现类

```java
@Service
public class DeptServiceImpl implements  DeptService {...}
@Service
public class OrderServiceImpl implements OrderService {...}
```

## 增强类

#### 最麻烦的pointcut都写

```java
@Component
@Aspect
public class LogHandler {

    /**
     * 通用日志记录功能
     */
    @AfterReturning(pointcut = "execution(public * com.aaa.aop.service.*.*(..))")
    public void saveLog(JoinPoint joinPoint) {
        //拿到链接点方法名称
        String methodName = joinPoint.getSignature().getName();
        //获取目标对象名称
        String className = joinPoint.getTarget().getClass().getName();
        //执行方法的参数
        Object[] args = joinPoint.getArgs();
        StringBuilder str = new StringBuilder("");
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                str.append(arg.getClass().getName());
            }
        }
        System.out.println("切面：AfterReturning->" + className + "." + methodName + "(" + str.toString() + ")");
        System.out.println("##########################################################");
        //System.out.println("-------------模拟日志记录功能2222222222，在操作"+className+"."+methodName+"方法后，记录日志"+str);
    }

    /**
     * 前置通知处理方法。。。
     *
     * @param joinPoint
     */
    @Before("execution(public * com.aaa.aop.service.*.*(..))")
    public void beforeExecute(JoinPoint joinPoint) {
        //拿到链接点方法名称
        String methodName = joinPoint.getSignature().getName();
        //获取目标对象名称
        String className = joinPoint.getTarget().getClass().getName();
        //执行方法的参数
        Object[] args = joinPoint.getArgs();
        StringBuilder str = new StringBuilder("");
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                str.append(arg.getClass().getName());
            }
        }
        System.out.println("切面：Before->" + className + "." + methodName + "(" + str.toString() + ")");
    }


    /**
     * 环绕通知
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around("execution(public * com.aaa.aop.service.*.*(..))")
    public Object aroundExecute(ProceedingJoinPoint proceedingJoinPoint) {
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        StringBuilder str = new StringBuilder("");
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                str.append(arg.getClass().getName());
            }
        }
        Object obj = null;
        System.out.println("--切面：Around ----> 业务执行前->" + className + "." + methodName + "(" + str.toString() + ")");
        try {
            obj = proceedingJoinPoint.proceed();  //调用业务方法
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        // System.out.println("--------返回值："+obj);
        System.out.println("--切面：Around ----> 业务执行后，返回值：" + obj);
        return obj;
    }

    /**
     * 当出现异常时，执行该方法
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(pointcut = "execution(public * com.aaa.aop.service.*.*(..))", throwing = "ex")
    public void exceptionExecute(JoinPoint joinPoint, Exception ex) {
        String name = joinPoint.getSignature().getName();
        System.out.println("切面：AfterThrowing --> 在执行" + name + "方法时，出现" + ex.getClass().getName() +
                "的异常，具体描述:" + ex.getMessage());
    }

    /**
     * 无论有没有异常都会执行方法处理收尾业务
     *
     * @param joinPoint
     */
    @After("execution(public * com.aaa.aop.service.*.*(..))")
    public void afterExecute(JoinPoint joinPoint) {
        //拿到链接点方法名称
        String methodName = joinPoint.getSignature().getName();
        //获取目标对象名称
        String className = joinPoint.getTarget().getClass().getName();
        //执行方法的参数
        Object[] args = joinPoint.getArgs();
        StringBuilder str = new StringBuilder("");
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                str.append(arg.getClass().getName());
            }
        }
        System.out.println("切面：After -->" + className + "." + methodName + "(" + str.toString() + ")");

    }
}
```

#### 较简单的配置一个通用切入点

```java
@Component
@Aspect
public class LogHandler {
    @Pointcut("execution(public * com.aaa.aop.service.*.*(..))")
    private void pointA(){

    }
    @AfterReturning(pointcut = "pointA()")
    public void saveLog(JoinPoint joinPoint) {
        ....
    }
    .......
}
```



## 测试类

```java
@Test
public void testAop(){
    ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("spring-config.xml");
    DeptService deptService  = (DeptService)applicationContext.getBean("deptService");
    Dept dept = new Dept();
    deptService.addDept(dept);
    deptService.update(dept);
    deptService.delete(0);
    OrderService orderService1  = (OrderService)applicationContext.getBean("orderService");
    Order order  =new Order();
    orderService1.add(order);
    orderService1.update(order);
    orderService1.delete(0);
}
```

## 输出

```
--切面：around ----> 业务执行前->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
切面：在执行业务之前->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
模拟部门添加
--切面：around ----> 业务执行后，返回值：null
切面：最后执行->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
切面：return后执行->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
##########################################################
--切面：around ----> 业务执行前->com.aaa.aop.service.DeptServiceImpl.update(com.aaa.aop.entity.Dept)
切面：在执行业务之前->com.aaa.aop.service.DeptServiceImpl.update(com.aaa.aop.entity.Dept)
模拟部门更新
--切面：around ----> 业务执行后，返回值：0
切面：最后执行->com.aaa.aop.service.DeptServiceImpl.update(com.aaa.aop.entity.Dept)
切面：return后执行->com.aaa.aop.service.DeptServiceImpl.update(com.aaa.aop.entity.Dept)
##########################################################
--切面：around ----> 业务执行前->com.aaa.aop.service.DeptServiceImpl.delete(java.lang.Integer)
切面：在执行业务之前->com.aaa.aop.service.DeptServiceImpl.delete(java.lang.Integer)
模拟部门删除
--切面：around ----> 业务执行后，返回值：0
切面：最后执行->com.aaa.aop.service.DeptServiceImpl.delete(java.lang.Integer)
切面：return后执行->com.aaa.aop.service.DeptServiceImpl.delete(java.lang.Integer)
##########################################################
--切面：around ----> 业务执行前->com.aaa.aop.service.OrderServiceImpl.add(com.aaa.aop.entity.Order)
切面：在执行业务之前->com.aaa.aop.service.OrderServiceImpl.add(com.aaa.aop.entity.Order)
模拟订单添加
--切面：around ----> 业务执行后，返回值：0
切面：最后执行->com.aaa.aop.service.OrderServiceImpl.add(com.aaa.aop.entity.Order)
切面：return后执行->com.aaa.aop.service.OrderServiceImpl.add(com.aaa.aop.entity.Order)
##########################################################
--切面：around ----> 业务执行前->com.aaa.aop.service.OrderServiceImpl.update(com.aaa.aop.entity.Order)
切面：在执行业务之前->com.aaa.aop.service.OrderServiceImpl.update(com.aaa.aop.entity.Order)
模拟订单更新
--切面：around ----> 业务执行后，返回值：0
切面：最后执行->com.aaa.aop.service.OrderServiceImpl.update(com.aaa.aop.entity.Order)
切面：return后执行->com.aaa.aop.service.OrderServiceImpl.update(com.aaa.aop.entity.Order)
##########################################################
--切面：around ----> 业务执行前->com.aaa.aop.service.OrderServiceImpl.delete(java.lang.Integer)
切面：在执行业务之前->com.aaa.aop.service.OrderServiceImpl.delete(java.lang.Integer)
模拟订单删除
--切面：around ----> 业务执行后，返回值：0
切面：最后执行->com.aaa.aop.service.OrderServiceImpl.delete(java.lang.Integer)
切面：return后执行->com.aaa.aop.service.OrderServiceImpl.delete(java.lang.Integer)
##########################################################
```

# 两种函数增强不同点

## 增强顺序不同

配置增强

受配置xml文件的配置顺序影响

举例子：

Before-->Around(before)-->Around(throw)-->Around(after)-->AfterReturning-->After

```
配置的执行流程
切面：Before -->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
模拟部门添加
切面：AfterThrowing --> 在执行addDept方法时，出现java.lang.NullPointerException的异常，具体描述:null
切面：After 最后执行 --> com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
```

**不用around只用afterthrowing，爆红无法屏蔽，程序无法继续执行，所以用around更加好**

注解增强

```
--切面：around ----> 业务执行前->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
切面：Before->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
模拟部门添加
--切面：around ----> 业务执行后，返回值：null
切面：After->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
切面：return后执行->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)

```

# 异常拦截



不管配置与注解，用afterthrowing实现的话，就不能用around了，否则优先around



## 不用Around的异常拦截

异常出现时，依然会完整执行完此方法的其他流程，但是不会继续进行下一个方法了，想不停，那就只能用around

### 配置实现的流程

```
切面：Before -->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
模拟部门添加
切面：AfterThrowing --> 在执行addDept方法时，出现java.lang.NullPointerException的异常，具体描述:null
切面：After 最后执行 --> com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
##########################################################

java.lang.NullPointerException
.....
```

### 注解实现的流程

未执行AfterReturning

```
切面：Before->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
模拟部门添加
切面：After -->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
切面：AfterThrowing --> 在执行addDept方法时，出现java.lang.ArithmeticException的异常，具体描述:/ by zero

java.lang.ArithmeticException: / by zero
...
未执行AfterReturning
```

## 用Around，不用afterthrowing拦截

函数将会继续执行，不会停下

### 配置实现函数

```java
public Object aroundExecute(ProceedingJoinPoint proceedingJoinPoint) {
    String methodName = proceedingJoinPoint.getSignature().getName();
    String className = proceedingJoinPoint.getTarget().getClass().getName();
    Object[] args = proceedingJoinPoint.getArgs();
    StringBuilder str = new StringBuilder("");
    if (args != null && args.length > 0) {
        for (Object arg : args) {
            str.append(arg.getClass().getName());
        }
    }
    Object obj = null;
    System.out.println("--切面：Around ----> 业务执行前->" + className + "." + methodName + "(" + str.toString() + ")");
    try {
        obj = proceedingJoinPoint.proceed();  //调用业务方法
    } catch (Throwable throwable) {
        System.out.println("切面：Around --> 异常" + throwable.getClass().getName());
        //throwable.printStackTrace();
    }
    // System.out.println("--------返回值："+obj);
    System.out.println("--切面：Around ----> 业务执行后，返回值：" + obj);
    return obj;
}
输出：
切面：Before -->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
--切面：Around ----> 业务执行前->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
模拟部门添加
切面：Around --> 异常java.lang.NullPointerException
--切面：Around ----> 业务执行后，返回值：null
切面：After 最后执行 --> com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
##########################################################
```

### 注解实现流程

```java
@Around("execution(public * com.aaa.aop.service.*.*(..))")
public Object aroundExecute(ProceedingJoinPoint proceedingJoinPoint) {
    String methodName = proceedingJoinPoint.getSignature().getName();
    String className = proceedingJoinPoint.getTarget().getClass().getName();
    Object[] args = proceedingJoinPoint.getArgs();
    StringBuilder str = new StringBuilder("");
    if (args != null && args.length > 0) {
        for (Object arg : args) {
            str.append(arg.getClass().getName());
        }
    }
    Object obj = null;
    System.out.println("--切面：Around ----> 业务执行前->" + className + "." + methodName + "(" + str.toString() + ")");
    try {
        obj = proceedingJoinPoint.proceed();  //调用业务方法
    } catch (Throwable throwable) {
        System.out.println("切面：Around --> 异常" + throwable.getClass().getName());
        //throwable.printStackTrace();
    }
    // System.out.println("--------返回值："+obj);
    System.out.println("--切面：Around ----> 业务执行后，返回值：" + obj);
    return obj;
}
输出：
--切面：Around ----> 业务执行前->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
切面：Before->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
模拟部门添加
切面：Around --> 异常java.lang.ArithmeticException
--切面：Around ----> 业务执行后，返回值：null
切面：After -->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
切面：AfterReturning->com.aaa.aop.service.DeptServiceImpl.addDept(com.aaa.aop.entity.Dept)
##########################################################
```

