框架种类

```
jsp +servlet+jdbc(BaseDao) +filter ....                
ssm = spring  springmvc   mybatis（dao层）
sm  = springboot  mybatis
ssh  = spring  struts2    hibernate（dao层）
```

# maven

## maven工作原理

​            pom.xml为核心  管理jar   使用自带maven core  本地仓库获取jar,本地如果没有jar 使用配置的远程（阿里云的仓库）配置仓库，下载jar到本地仓库，再使用jar

![image-20200915193720404](MyBatis.assets/image-20200915193720404.png)

## maven常用命令

```cmd
mvn install:install-file -Dfile=F:\app\Administrator\product\11.1.0\db_1\jdbc\lib\ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.4.0  -Dpackaging=jar
```



# mybatis

## 本章重点

1, ORM 思想

2, mybatis 原理

3, mybatis 配置详解

## 具体内容

### ORM思想

对象关系映射（Object Relational Mapping，简称ORM） ORM是通过使用描述对象和数据库之间映射的元数据，元数据一般采用XML格式，并且存放在专门的对象一映射文件中。将程序中的对象自动持久化到关系数据库中。

### mybatis简介

#### mybatis官网：

https://mybatis.org/mybatis-3/zh/getting-started.html

   MyBatis 是一款优秀的持久层框架，它支持定制化 SQL、存储过程以及高级映射。MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。MyBatis 可以使用简单的 XML 或注解来配置和映射原生类型、接口和 Java 的 POJO（Plain Old/Ordinary Java Objects，普通老式 Java 对象）为数据库中的记录。

## mapper文件详解：

可配置标签：

cache – 对给定命名空间的缓存配置。

cache-ref – 对其他命名空间缓存配置的引用（已经不用了）。

resultMap – 是最复杂也是最强大的元素，用来描述如何从数据库结果集中来加载对象。

parameterMap – 已被废弃！老式风格的参数映射。更好的办法是使用内联参数，此元素可能在将来被移除。文档中不会介绍此元素。

sql – 可被其他语句引用的可重用语句块。

insert – 映射插入语句

update – 映射更新语句

delete – 映射删除语句

select – 映射查询语句

标签的属性描述： 

1）id：命名空间中唯一的标识符，可以来引用该条语句 也是接口的方法名称

2）parameterType：这里设置传入该条语句的参数类的完全限定名或者普通java类型 

3）resultType：返回结果的类的完全限定名或者普通java类型 

4）resultMap：外部resultMap的命名引用，结果集的映射是Mybatis最强大的特性 

5）flushCache：如果设置为true,则任何时候该语句被调用，都会导致本地缓存和二级缓存都被清空。默认是false 

6）useCache：如果设置为true，将会导致本条语句的结果被二级缓存。默认是true 

7）timeout：抛出异常之前，驱动程序等待数据库返回请求结果的秒数 

## 结构图

![image-20200915194034404](MyBatis.assets/image-20200915194034404.png)

架构分为三层：

接口层：给应用程序提供一系列的数据接口。（CRUD) ;SqlSession数据处理层：

接口层传递参数，sql命令，在数据处理层进行处理，返回对应的结果映射。

基础支撑层：提供最基础的底层的操作：连接管理（连接池），事务管理（增、删、改），配置加载

（读取配置信息），缓存（一级缓存，二级缓存）。

## mybatis 工作原理

![image-20200915194127174](MyBatis.assets/image-20200915194127174.png)

## ***\*关键类介绍:\****

***\*SqlSessionFactoryBuilder\****

这个类可以被实例化、使用和丢弃，一旦创建了 SqlSessionFactory，就不再需要它了。 因此 SqlSessionFactoryBuilder 实例的最佳作用域是方法作用域（也就是局部方法变量）。 你可以重用 SqlSessionFactoryBuilder 来创建多个 SqlSessionFactory 实例，但是最好还是不要让其一直存在，以保证所有的 XML 解析资源可以被释放给更重要的事情。

***\*SqlSessionFactory\****

SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例。 使用 SqlSessionFactory 的最佳实践是在应用运行期间不要重复创建多次，多次重建 SqlSessionFactory 被视为一种代码“坏味道（bad smell）”。因此 SqlSessionFactory 的最佳作用域是应用作用域。 有很多方法可以做到，最简单的就是使用单例模式或者静态单例模式。

***\*SqlSession\****

每个线程都应该有它自己的 SqlSession 实例。SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域。 绝对不能将 SqlSession 实例的引用放在一个类的静态域，甚至一个类的实例变量也不行。 也绝不能将 SqlSession 实例的引用放在任何类型的托管作用域中，比如 Servlet 框架中的 HttpSession。 如果你现在正在使用一种 Web 框架，要考虑 SqlSession 放在一个和 HTTP 请求对象相似的作用域中。 换句话说，每次收到的 HTTP 请求，就可以打开一个 SqlSession，返回一个响应，就关闭它。 这个关闭操作是很重要的，你应该把这个关闭操作放到 finally 块中以确保每次都能执行关闭。 

## mybatis 快速入门（完成增删改查操作）

### 创建项目引入jar

```xml
<!--  mybatis包  -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.5</version>
</dependency>
<!--oracle驱动包
    mvn install:install-file -Dfile=F:\app\Administrator\product\11\dbhome\jdbc\lib\ojdbc6.jar -DgroupId=oracle -DartifactId=oracle-jdbc -Dversion=12.1.0.2  -Dpackaging=jar

    -->
<dependency>
    <groupId>oracle</groupId>
    <artifactId>oracle-jdbc</artifactId>
    <version>12.1.0.2</version>
</dependency>
```

创建mybatis主配置文件，创建数据源配置文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!--dtd =document type definition 规定了当前文档类型定义格式-->
<configuration>
<!--  环境配置，可以配置多个环境  -->
    <environments default="development">
<!--    具体环境配置    -->
        <environment id="development">
<!--    事务管理配置 type="jdbc" 自行处理事务 type="managed"把事务交给容器管理        -->
            <transactionManager type="JDBC"/>
            <!--数据源：连接池技术-->
            <dataSource type="POOLED">
                <property name="driver" value="oracle.jdbc.driver.OracleDriver"/>
                <property name="url" value="jdbc:oracle:thin:@localhost:1521:orcl"/>
                <property name="username" value="scott"/>
                <property name="password" value="coby"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="mappers/DeptMapper.xml"/>
    </mappers>
</configuration>
```

### 创建mybatis Mapper文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--1.隔离语句；2.绑定接口-->
<mapper namespace="cn.cobight.mybatis.dao.DeptDao">
    <select id="listDept" resultType="cn.cobight.mybatis.entity.Dept">
        select deptno,dname deptName,loc from dept
    </select>
<!--  parameter可以省略   -->
    <select id="getDeptById" parameterType="int" resultType="cn.cobight.mybatis.entity.Dept">
        select deptno,dname deptName,loc from dept where deptno=#{deptNo}
    </select>
</mapper>
```

### 实体类Dept

```java
public class Dept {

    private Integer deptNo;
    private String deptName;
    private String loc;
    getter and setter
}    
```

### 代理的接口

```java
public interface DeptDao {
    List<Dept> listDept();
    Dept getDeptById(int deptNo);
}
```

### 代理的工厂类

```java
public class SqlSessionFactoryUtil {
    //1.私有构造
    private SqlSessionFactoryUtil(){}
    //2.静态私有属性
    private static SqlSessionFactory sqlSessionFactory;

    //静态单例模式
    static {
        //mybatis提供Resource  中提供getResourceAsStream根据资源得到流
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //mybatis提供SqlSessionFactoryBuilder 中提供build方法用来创建工厂
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream!=null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    //3.提供对外创建SqlSession方法
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
```

### 测试类

```java
public class DeptDaoTest {
    @Test
    public void testListDept(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用JDK代理（代理接口）生成DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态
            //获取列表
            List<Dept> deptList = deptDao.listDept();
            //判断循环 输出
            if (deptList!=null&&deptList.size()>0){
                for (Dept dept : deptList) {
                    System.out.println("部门名称"+dept.getDeptName()+",位置："+dept.getLoc());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }

    @Test
    public void testGetDeptById(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用JDK代理（代理接口）生成DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态
            //获取列表
            Dept dept = deptDao.getDeptById(10);
            //判断循环 输出
            if (dept!=null){
                System.out.println("部门名称"+dept.getDeptName()+",位置："+dept.getLoc());

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }
}
```

## mybatis 基础语句集合

### 实体类Dept

```java
public class Dept {

    private Integer deptNo;
    private String deptName;
    private String loc;

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
```

### 测试基础语句的接口

```java
public interface DeptDao {
    //select案例入门
    List<Dept> listDept();
    //带参查找
    Dept getDeptById(int deptNo);
    //if语句
    List<Dept> listDeptByParamIf(Dept dept);
    //if + where
    List<Dept> listDeptByParamIfWhere(Dept dept);
    //choose when otherwise选择，类似switch
    List<Dept> listDeptByParamChoose(Dept dept);
    //set用法
    int updateDeptParam(Dept dept);
    //加头与去头
    List<Dept> listDeptByParamTrim(Dept dept);
    //遍历
    List<Dept> listDeptByParamForeach(List list);
}
```

### mapper配置

```java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--1.隔离语句；2.绑定接口-->
<mapper namespace="cn.cobight.mybatis.dao.DeptDao">
<!--  部门列表查询  -->
    <select id="listDept" resultType="cn.cobight.mybatis.entity.Dept">
        select deptno,dname deptName,loc from dept
    </select>
<!--  部门列表查询  -->
    <select id="listDeptByParamIf" parameterType="dept" resultType="cn.cobight.mybatis.entity.Dept">
        select deptno,dname deptName,loc from dept where 1=1
        <if test="deptNo != null">
            and deptno = #{deptNo}
        </if>
        <if test="deptName != null and deptName != '' ">
            and dname like '%'||#{deptName}||'%'
<!--             and deptno like '%${deptName}%' -->
        </if>
    </select>
<!--  parameter可以省略   -->
    <select id="getDeptById" parameterType="int" resultType="cn.cobight.mybatis.entity.Dept">
        select deptno,dname deptName,loc from dept where deptno=#{deptNo}
    </select>
<!--  if + where 用法 -->
    <select id="listDeptByParamIfWhere" parameterType="dept" resultType="cn.cobight.mybatis.entity.Dept">
        select deptno,dname deptName,loc from dept
        <where>
            <if test="deptNo != null">
                and deptno = #{deptNo}
            </if>
            <if test="deptName != null and deptName != '' ">
                and dname like '%'||#{deptName}||'%'
                <!--             and deptno like '%${deptName}%' -->
            </if>
        </where>
    </select>
<!-- Choose 用法 -->
    <select id="listDeptByParamChoose" parameterType="dept" resultType="cn.cobight.mybatis.entity.Dept">
        select deptno,dname deptName,loc from dept
        <where>
            <choose>
                <when test="deptNo != null">
                    and deptno = #{deptNo}
                </when>
                <when test="deptName != null and deptName != '' ">
                    and dname like '%'||#{deptName}||'%'
                    <!--             and deptno like '%${deptName}%' -->
                </when>
                <when test="loc != null and loc != '' ">
                    and loc = #{loc}
                </when>
                <otherwise>
                    and 1=1
                </otherwise>
            </choose>
        </where>
    </select>
    <sql id="publicSql">
        select deptno,dname deptName,loc
    </sql>
<!-- 查询列表foreach -->
    <select id="listDeptByParamForeach" parameterType="list" resultType="dept">
         <include refid="publicSql"></include> from dept where deptno in
--           集合(10,20,30,40)   separator分隔器
        <foreach collection="list" open="(" close=")" separator="," item="dno">
            #{dno}
        </foreach>
    </select>
<!-- 部门更新 （set + if）根据参数动态拼接更新语句
    更操作（insert update delete）不能有resultType resultMap
-->
    <update id="updateDeptParam" parameterType="dept">
        update dept
        <trim prefix="set" suffixOverrides=",">
            <if test="deptName != null and deptName != ''">-->
                dname=#{deptName},
            </if>
            <if test="loc != null and loc != ''">
                dname=#{loc},
            </if>
        </trim>
<!--        <set>-->
<!--&#45;&#45;         多个条件至少满足一个，若都不满足，会报错-->
<!--&#45;&#45;         这个例子中，set 元素会动态地在行首插入 SET 关键字，并会删掉额外的逗号（这些逗号是在使用条件语句给列赋值时引入的）。-->
<!--            <if test="deptName != null and deptName != ''">-->
<!--                dname=#{deptName},-->
<!--            </if>-->
<!--            <if test="loc != null and loc != ''">-->
<!--                dname=#{loc},-->
<!--            </if>-->
<!--        </set>-->
        where deptno=#{deptNo}
    </update>
<!--  if + trim 用法 -->
    <select id="listDeptByParamTrim" parameterType="dept" resultType="cn.cobight.mybatis.entity.Dept">
        select deptno,dname deptName,loc from dept
--         prefix整体加前缀，suffix整体加后缀
--         prefixOverrides子句删前缀，suffixOverrides子句删后缀
--         <trim suffix="" suffixOverrides=","></trim>
        <trim prefix="where" prefixOverrides="and|or">
            <if test="deptNo != null">
                and deptno = #{deptNo}
            </if>
            <if test="deptName != null and deptName != '' ">
                and dname like '%'||#{deptName}||'%'
                <!--             and deptno like '%${deptName}%' -->
            </if>
        </trim>
    </select>
</mapper>
```

### 测试类

```java
package cn.cobight.mybatis.test;

import cn.cobight.mybatis.dao.DeptDao;
import cn.cobight.mybatis.entity.Dept;
import cn.cobight.mybatis.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * fileName:DeptDaoTest
 * description:
 * author:cobight
 * createTime:2020/9/15 20:53
 * version:1.0.0
 */
public class DeptDaoTest {
    @Test
    public void testListDept(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用JDK代理（代理接口）生成DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态
            //获取列表
            List<Dept> deptList = deptDao.listDept();
            //判断循环 输出
            if (deptList!=null&&deptList.size()>0){
                for (Dept dept : deptList) {
                    System.out.println("部门名称"+dept.getDeptName()+",位置："+dept.getLoc());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }

    @Test
    public void testGetDeptById(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用JDK代理（代理接口）生成DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态
            //获取列表
            Dept dept = deptDao.getDeptById(10);
            //判断循环 输出
            if (dept!=null){
                System.out.println("部门名称"+dept.getDeptName()+",位置："+dept.getLoc());

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }

    @Test
    public void listDeptByParamIf(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用JDK代理（代理接口）生成DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态

            Dept dept1 = new Dept();
           dept1.setDeptNo(20);
            dept1.setDeptName("A");
            List<Dept> deptList = deptDao.listDeptByParamIf(dept1);

            //判断循环 输出
            if (deptList!=null&&deptList.size()>0){
                for (Dept dept : deptList) {
                    System.out.println("部门名称"+dept.getDeptName()+",位置："+dept.getLoc());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }

    @Test
    public void listDeptByParamIfWhere(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用JDK代理（代理接口）生成DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态

            Dept dept1 = new Dept();
            dept1.setDeptNo(20);
            dept1.setDeptName("A");
            List<Dept> deptList = deptDao.listDeptByParamIfWhere(dept1);

            //判断循环 输出
            if (deptList!=null&&deptList.size()>0){
                for (Dept dept : deptList) {
                    System.out.println("部门名称"+dept.getDeptName()+",位置："+dept.getLoc());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }
    @Test
    public void listDeptByParamChoose(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用JDK代理（代理接口）生成DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态

            Dept dept1 = new Dept();
            dept1.setDeptNo(20);
            dept1.setDeptName("A");
            List<Dept> deptList = deptDao.listDeptByParamChoose(dept1);

            //判断循环 输出
            if (deptList!=null&&deptList.size()>0){
                for (Dept dept : deptList) {
                    System.out.println("部门名称"+dept.getDeptName()+",位置："+dept.getLoc());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }

    @Test
    public void updateDeptParam(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用JDK代理（代理接口）生成DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态

            Dept dept1 = new Dept();
            dept1.setDeptNo(20);
            dept1.setDeptName("RESEARCH1");
            int result = deptDao.updateDeptParam(dept1);
//          事务提交
            sqlSession.commit();
            //判断循环 输出
            if (result>0){
                System.out.println("执行成功");
            }else {
                System.out.println("执行失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }

    @Test
    public void listDeptByParamTrim(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用JDK代理（代理接口）生成DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态

            Dept dept1 = new Dept();
            //dept1.setDeptNo(20);
            dept1.setDeptName("A");
            List<Dept> deptList = deptDao.listDeptByParamTrim(dept1);

            //判断循环 输出
            if (deptList!=null&&deptList.size()>0){
                for (Dept dept : deptList) {
                    System.out.println("部门名称"+dept.getDeptName()+",位置："+dept.getLoc());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }

    @Test
    public void listDeptByParamForeach(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用JDK代理（代理接口）生成DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态

            List paramList = new ArrayList();
            paramList.add(10);
            paramList.add(11);
            paramList.add(20);
            paramList.add(40);
            List<Dept> deptList = deptDao.listDeptByParamForeach(paramList);

            //判断循环 输出
            if (deptList!=null&&deptList.size()>0){
                for (Dept dept : deptList) {
                    System.out.println("部门名称"+dept.getDeptName()+",位置："+dept.getLoc());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }
}
```

## mybatis结果映射、关联查询

### 实体类

```java
public class Emp {
    private Integer empNo;
    private String empName;
    private Double salary;
    private String job;
    //多对一，一对一
    private Dept dept;
    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }
}
```

### 测试接口

```java
public interface EmpDao {

    List<Emp> listEmpsAndDept();
}
```

### mapper配置

```java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--1.隔离语句；2.绑定接口-->
<mapper namespace="cn.cobight.mybatis.dao.EmpDao">

    <select id="listEmpsAndDept" resultMap="empsAndDept">
        select e.empno,e.ename,e.sal,e.job,d.deptno,d.dname,d.loc
        from emp e join dept d on e.deptno = d.deptno
    </select>
<!--  高级结果映射  -->
    <resultMap id="empsAndDept" type="emp">
        <id column="en" property="empNo" javaType="Integer" jdbcType="INTEGER"></id>
        <result column="ename" property="empName"></result>
        <result column="sal" property="salary"></result>
        <result column="job" property="job"></result>
<!--   关联  一对一或者多对一   -->
        <association property="dept" javaType="cn.cobight.mybatis.entity.Dept">
            <id column="deptno" property="deptNo"></id>
            <result column="dname" property="deptName"></result>
            <result column="loc" property="loc"></result>
        </association>
    </resultMap>
</mapper>
```

### 测试类

```java
public class EmpDaoTest {
    @Test
    public void testListEmpsAndDept(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            EmpDao empDao = sqlSession.getMapper(EmpDao.class);
            List<Emp> emps = empDao.listEmpsAndDept();
            if (emps!=null && emps.size()>0){
                for (Emp emp : emps) {
                    System.out.println("员工名称："+emp.getEmpName()+"该员工的部门为：");
                    Dept dept = emp.getDept();
                    if (dept!=null){
                        System.out.println("--------------"+dept.getDeptName());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }
}
```

