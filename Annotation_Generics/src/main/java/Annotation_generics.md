# 注解

## 四个元注解

『元注解』一般用于指定某个注解生命周期以及作用目标等信息。

JAVA 中有以下几个『元注解』：

- @Target：注解的作用目标
- @Retention：注解的生命周期
- @Documented：注解是否应当被包含在 JavaDoc 文档中
- @Inherited：是否允许子类继承该注解

### @Target

@Target 用于指明被修饰的注解最终可以作用的目标是谁，也就是指明，你的注解到底是用来修饰方法的？修饰类的？还是用来修饰字段属性的。



被这个 @Target 注解修饰的注解将只能作用在成员字段上，不能用于修饰方法或者类。其中，ElementType 是一个枚举类型，有以下一些值：

- ElementType.TYPE：允许被修饰的注解作用在类、接口和枚举上
- ElementType.FIELD：允许作用在属性字段上
- ElementType.METHOD：允许作用在方法上
- ElementType.PARAMETER：允许作用在方法参数上
- ElementType.CONSTRUCTOR：允许作用在构造器上
- ElementType.LOCAL_VARIABLE：允许作用在本地局部变量上
- ElementType.ANNOTATION_TYPE：允许作用在注解上
- ElementType.PACKAGE：允许作用在包上

### @Retention

@Retention 用于指明当前注解的生命周期

- RetentionPolicy.SOURCE：当前注解编译期可见，不会写入 class 文件，仅在源文件有效
- RetentionPolicy.CLASS：类加载阶段丢弃，会写入 class 文件，运行Java时，不再保留注解，这是默认值
- RetentionPolicy.RUNTIME：永久保存，可以反射获取



剩下两种类型的注解我们日常用的不多，也比较简单，这里不再详细的进行介绍了，你只需要知道他们各自的作用即可。@Documented 注解修饰的注解，当我们执行 JavaDoc 文档打包时会被保存进 doc 文档，反之将在打包时丢弃。@Inherited 注解修饰的注解是具有可继承性的，也就说我们的注解修饰了一个类，而该类的子类将自动继承父类的该注解。

## **JAVA 的内置三大注解**

除了上述四种元注解外，JDK 还为我们预定义了另外三种注解，它们是：

- @Override
- @Deprecated
- @SuppressWarnings

### @Override 

@Override 注解想必是大家很熟悉的了，它的定义如下：

```text
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {
}
```

它没有任何的属性，所以并不能存储任何其他信息。它只能作用于方法之上，编译结束后将被丢弃。

所以你看，它就是一种典型的『标记式注解』，仅被编译器可知，编译器在对 java 文件进行编译成字节码的过程中，一旦检测到某个方法上被修饰了该注解，就会去匹对父类中是否具有一个同样方法签名的函数，如果不是，自然不能通过编译。



### @Deprecated 

过时的方法，当用户使用时有删除线

![img](https://pic4.zhimg.com/v2-d5d7b6af86178dac9e076379337d6c70_b.jpg)

### @SuppressWarning() 

![img](https://pic4.zhimg.com/v2-c8a6d065d01207b94067cbad8a96b268_b.jpg)

它有一个 value 属性需要你主动的传值，这个 value 代表一个什么意思呢，这个 value 代表的就是需要被压制的警告类型。例如：

@SuppressWarning() 压制代码异常提示

* @SuppressWarning("all")  --  所有
* @SuppressWarning("unused")  --  未使用的
* @SuppressWarning("deprecation ")  --  压制过时的



## 获取类上的注解

### 反射拿到所有注解

先反射类，然后拿到注解:@注解名（k,v   ....）

```java
//获取UserController的class对象
Class<UserController> userControllerClass = UserController.class;
//获取该类的所有注解
Annotation[] declaredAnnotations = userControllerClass.getDeclaredAnnotations();
for (Annotation declaredAnnotation : declaredAnnotations) {
    System.out.println(declaredAnnotation);
}
```

### 反射拿到一个注解

先判断类上有没这个注解

有了再去拿

```java
//获取UserController的class对象
Class<UserController> userControllerClass = UserController.class;
//获取该类的指定注解
//判断该类上有没有某一个（Controller）注解
boolean annotationPresent = userControllerClass.isAnnotationPresent(Controller.class);
if(annotationPresent) {
    Controller declaredAnnotation = userControllerClass.getAnnotation(Controller.class);
    System.out.println(declaredAnnotation);
    System.out.println(declaredAnnotation.value());
}
```

## 获取方法上的注解

先反射拿到这个方法对象，在获取指定反射类

然后就获取到反射类与内容

```java
//获取class对象
Class<UserDao> userDaoClass = UserDao.class;

try {
    //获取方法
    Method getUserByParam = userDaoClass.getDeclaredMethod("getUserByParam", Map.class);
    //获取指定反射类
    Select annotation = getUserByParam.getAnnotation(Select.class);
    if(annotation!=null){//获取不到就是null
        System.out.println(annotation);
        System.out.println("值："+annotation.value());
    }

} catch (NoSuchMethodException e) {
    e.printStackTrace();
}
out：
    
@cn.cobight.annotation.custom2.Select(value=select * from user where 1=1 and userName =#{userName} and passWord=#{passWord})
值：select * from user where 1=1 and userName =#{userName} and passWord=#{passWord}
```

### 获取变量属性上的注解

先用类反射出变量属性，然后获取指定注解

然后调用注解里的定义方法获取值

```java
//获取class对象
Class<UserServiceImpl> userServiceClassClass = UserServiceImpl.class;
try {
    //反射获取field
    Field userDao = userServiceClassClass.getDeclaredField("userDao");
    //获取注解
    Autowired annotation = userDao.getAnnotation(Autowired.class);
    if (annotation!=null){
        System.out.println(annotation);
        System.out.println(annotation.name());
        System.out.println(annotation.name1());
        System.out.println(annotation.name2());
    }
} catch (NoSuchFieldException e) {
    e.printStackTrace();
}
```

## 注解的继承

@Inherited

定义的子类继承父类时，父类的注解，有带着这个注解

那么子类也会带着，但是不会让你看到

可以通过反射，拿到定义的子类的父类的注解

【也就是说父类的注解类--加了此注解，那这个注解就能继承下去】

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@Documented  //生成文档时，是否把注解也生成到文档中
@Inherited  //可以被子孙类继承
public @interface RestController {

    //属性
    String  value()  default "baseController";
}
```

## sql拼装案例

注解

```java
@Target(ElementType.TYPE) //指定该注解用于类或者接口
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    //属性  指定数据库的名称
    String schema() default "mysql";
    //属性   指定表名称
    String tableName() default "user";
}
```

entity

```java
@Table(schema = "qy119",tableName = "tb_teacher1")
public class Teacher {

    @Column(name = "id")
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "mobile_num")
    private String mobileNum;

    @Column(name = "email")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

执行

* 通过实体类头上的注解获取数据库名
* 获取所有属性，拼出来   get（属）性名方法，获取变量内容
  * 变量不为空，通过变量属性头上的注解拼装查询语句





```java
public List<Map> listTeacherByParams(Teacher teacher) {
    try {
        //获取teacher的class对象
        Class teacherClass = teacher.getClass();
        System.out.println("11111111111111");
        //判断是否存在Table
        boolean annotationPresentTable = teacherClass.isAnnotationPresent(Table.class);
        System.out.println(annotationPresentTable);
        //如果不存在table注解，直接返回null
        if(!annotationPresentTable){
            return null;
        }
        System.out.println("2222");
        //获取库名和表名称
        Table annotationTable = (Table)teacherClass.getAnnotation(Table.class);
        //库名称
        String schema = annotationTable.schema();
        //表名
        String tableName = annotationTable.tableName();
        //拼装SQL
        StringBuffer sql = new StringBuffer();
        //使用StringBuffer效率高
        sql.append("select * from "+schema+"."+tableName+" where 1=1 ");
        // select * from qy119.tb_teacher1 where 1=1
        //获取所有属性类
        Field[] declaredFields = teacherClass.getDeclaredFields();
        //循环
        for (Field declaredField : declaredFields) {
            //根据属性类获取注解  拿到列名称 拼装  and ..=..  条件语句
            Column annotationColumn = declaredField.getAnnotation(Column.class);
            //拿到列名称  第一次循环  id   第2次  user_name    ....最后 email
            String columnName = annotationColumn.name();
            //获取属性名称 第一次   id   第2次  userName
            String fieldName = declaredField.getName();
            //执行get方法拿到属性值， 拼装get方法
            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);


            //通过反射和方法名称，拿到当前方法对象
            Method declaredMethod = teacherClass.getDeclaredMethod(getMethodName);
            //通过get方法获取当前属性的值
            Object returnValue = declaredMethod.invoke(teacher);
            //判断返回值是否为空，不为空，拼装sql语句
            if (returnValue != null) {
                if (returnValue instanceof Integer) {
                    sql.append(" and " + columnName + "=" + returnValue);
                } else if (returnValue instanceof String) {
                    String returnStrValue = (String) returnValue;
                    if ((returnStrValue).contains(",")) { // ['zhangsanfeng','mayun']
                        String[] strArray = returnStrValue.split(",");
                        sql.append(" and " + columnName + " in (");
                        for (String str : strArray) {
                            sql.append("'" + str + "',");
                        }
                        sql.substring(0, sql.length() - 1);
                        sql.append(")");
                    } else {
                        sql.append(" and " + columnName + "='" + returnValue + "'");
                    }
                }
            }
        }
        System.out.println("执行的sql为："+sql);
    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
```

# 泛型

## 类上加泛型

```java
public class Persion<T,E,A> {
    private T name;
    private E age;
    private A email;
    //打印名称
    public void printName(){
        System.out.println(this.name+"\t"+this.age+"\t"+this.email);
    }

    public Persion(T name, E age, A email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public E getAge() {
        return age;
    }

    public void setAge(E age) {
        this.age = age;
    }

    public A getEmail() {
        return email;
    }

    public void setEmail(A email) {
        this.email = email;
    }

    public static void main(String[] args) {
        Persion<String,Integer,Character> persion = new Persion<>("牛皮",1,'q');
        persion.printName();
    }
}
```

## 方法上加泛型

```java
public class ArrayToList {
    public static  <T> List<T> aToL(T[] tArray){
        List<T> tList = new ArrayList<>();
        for (T t : tArray) {
            tList.add(t);
        }
        return tList;
    }

    public static void main(String[] args) {
        List<String> strings = aToL(new String[]{"a", "b", "c"});
        System.out.println(strings);
        List<Integer> integers = aToL(new Integer[]{1,2,3});
        System.out.println(integers);
    }

}
```

## ？泛型

```
? extends Cat//包括自己的所有子孙延伸类
? supper Cat//包含自己在内的所有父类，最顶能object
```

