# 反射

## 定义一个实体类Student

```java
public class Student {
    private Integer stuId;
    private String stuNo;
    private String stuName;
    private String email;
    private int age;

    public Student() {
    }

    public Student(Integer stuId, String stuNo) {
        this.stuId = stuId;
        this.stuNo = stuNo;
    }

    public Student(Integer stuId, String stuNo, String stuName) {
        this.stuId = stuId;
        this.stuNo = stuNo;
        this.stuName = stuName;
    }

    public Student(Integer stuId, String stuNo, String stuName, String email, int age) {
        this.stuId = stuId;
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.email = email;
        this.age = age;
    }

    public void eat() {
        System.out.println("多喝热水，少熬夜");
    }

    public String lean(String courseName) {
        return stuName + "正在学习" + courseName + "课程";
    }

    public void playGame(String gameType, String gameName) {
        System.out.println(stuName + "正在玩" + gameType + "类型的" + gameName + "游戏");
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

## 加载类

获取类全称与类简称

```java
public class GetClassDemo {
    public static void main(String[] args) {
        //1.通过getClass()方法
        Student student = new Student();
        Class cla1 = student.getClass();
        System.out.println("全称：" + cla1.getName());
        System.out.println("简称：" + cla1.getSimpleName());
        //2.通过类名称获取class对象
        Class cla2 = Student.class;
        System.out.println("全称：" + cla2.getName());
        System.out.println("简称：" + cla2.getSimpleName());
        //3.通过Class的forName静态方法获取类
        try {
            Class cla3 = Class.forName("cn.cobight.entity.Student");
            System.out.println("全称：" + cla3.getName());
            System.out.println("简称：" + cla3.getSimpleName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //4.通过类加载器ClassLoader中的loadClass方法获取class对象
        ClassLoader classLoader = GetClassDemo.class.getClassLoader();
        try {
            Class cla4 = classLoader.loadClass("cn.cobight.entity.Student");
            System.out.println("全称：" + cla4.getName());
            System.out.println("简称：" + cla4.getSimpleName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

## 构造方法--反射

获取所有构造方法与实现三个构造方法

```java
public class ClassConstructorDemo {
    public static void main(String[] args) {
        //获取Class对象
        Class cla = Student.class;
        //获取该类中所有的构造方法
        Constructor[] declaredConstructors = cla.getDeclaredConstructors();//获取所有的构造方法
        //cla.getConstructors();//获取公共的构造方法
        
        //遍历所有构造方法与参数类型
        for (Constructor declaredConstructor : declaredConstructors) {
            Class[] parameterTypes = declaredConstructor.getParameterTypes();
            System.out.println("构造方法名称："+declaredConstructor.getName()
                    +"\t构造的参数个数："+declaredConstructor.getParameterTypes().length+",具体的参数类型：");
            for (Class parameterType : parameterTypes) {
                System.out.println("---------:"+parameterType.getName());
            }
        }
        
        
        System.out.println("-------------------------------");
        try {
            Constructor declaredConstructor = cla.getDeclaredConstructor(Integer.class, String.class);
            System.out.println("构造方法名称为："+declaredConstructor.getName());
            System.out.println("参数个数："+declaredConstructor.getParameterTypes().length);
            //使用反射实例化对象，先要获取构造对象
            Student student = (Student)declaredConstructor.newInstance(1001, "stu0001");
            System.out.println("学生编号:"+student.getStuNo ()+",学生ID："+student.getStuId());


            //构造空对象
            Student student2 = (Student)cla.newInstance();
            System.out.println(student2.getClass().getName());
            //构造空对象
            Constructor declaredConstructor1 = cla.getDeclaredConstructor();
            Student student3 = (Student)declaredConstructor1.newInstance();
            System.out.println(student3.getClass().getName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
```

### 实现一个构造方法

```java
//先获取对应的Class对象
Class cla = Student.class;
1.先获取构造方法对象，用它来new 实例
    //获取一个指定参数类型的构造方法对象----抛异常
    Constructor declaredConstructor = cla.getDeclaredConstructor(Integer.class, String.class);//可以空构造方法
    //构造方法对象. new一个实例，相当于new Student（1001，“stu0001”）
    Student student = (Student)declaredConstructor.newInstance(1001, "stu0001");
2.直接使用Class的newinstance方法
    //加载类，通过加载类来新建实例
    Student student2 = (Student)cla.newInstance();//应该可以有参数构造
    System.out.println(student2.getClass().getName());
```

## 成员变量属性--反射

获取所有类成员变量属性

获取一个变量属性  并 读（set）写（get）

```java
public class ClassFieldDemo {
    public static void main(String[] args) {
        //获取Class对象
        Class cla = Student.class;
        //获取所有属性
        //获取指定属性，给指定属性赋值
        Field[] declaredFields = cla.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("属性名称为："+declaredField.getName()+"属性类型："+declaredField.getType());
        }
        System.out.println("-------------------");
        //获取指定属性,给指定属性赋值
        try {
            Field stuNo = cla.getDeclaredField("stuNo");
            System.out.println("属性名称为："+stuNo.getName()+",属性的类型："+stuNo.getType());
            Object obj = cla.newInstance();
            stuNo.setAccessible(true);
            stuNo.set(obj,"stu0005");
            System.out.println(stuNo.get(obj));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
```

## 成员方法--反射

```java
public class ClassMethodDemo {
    public static void main(String[] args) {
        //获取Class对象
        Class cla = Student.class;
        //获取所有方法
        Method[] declaredMethods = cla.getDeclaredMethods();
        //遍历所有方法对象
        for (Method declaredMethod : declaredMethods) {
            //获取本方法对象的所有参数对象数组
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            //输出方法对象的方法名 与 本方法的参数个数
            System.out.println("方法名称："+declaredMethod.getName()+",方法参数个数"+parameterTypes.length);
            //如果有参数就输出参数类型
            if (parameterTypes.length>0){
                System.out.println("具体的参数类型为：");
                for (Class<?> parameterType : parameterTypes) {
                    System.out.println("-------"+parameterType.getName());
                }
            }
        }
        try {
            //通过获取的Class，获取一个指定方法名与参数类型与数量的方法对象
            Method playGame = cla.getDeclaredMethod("playGame", String.class, String.class);
            //输出方法名 + 参数个数
            System.out.println("方法名称:"+playGame.getName()+",方法参数个数："+playGame.getParameterTypes().length);
            //方法需要一个具体的学生对象，那就新建一个实例
            Object stu1 = cla.newInstance();
            //获取一个指定的类成员变量 + 赋值
            Field stuName = cla.getDeclaredField("stuName");
            stuName.setAccessible(true);
            stuName.set(stu1,"张三");
            //运行在stu1类中的方法playgame，并 赋予参数
            Object back = playGame.invoke(stu1,"射击","CF");
            //返回值
            
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}

```