package com.aaa.mybatis.plugins;

import com.aaa.mybatis.util.Page;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * fileName:PagePlugin
 * description:
 * author:zz
 * createTime:2020/9/21 10:32
 * version:1.0.0
 */
//Intercepts 标识当前类为插件类
// Signature 拦截类的签名
//Type = 拦截接口 是四个接口之一
//method= 拦截接口中的某一个方法
//args =  该方法的参数
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PagePlugin implements Interceptor {

    //初始化参数  默认第几页
    private Integer defaultPageNo;
    // 每页显示数量
    private Integer defaultPageSize;

    // intercept  拦截方法，执行自定义业务
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("进入拦截器。。。。。。。。。。。。。。。。");
        //  Executor
        // 在普通的查询sql语句，看是否携带分页参数，如果携带分页参数，把普通的sql语句做封装，封装oracle 的分页查询语句，mybatis让它执行
        //在上面想要的需求中分页，必须在语句准备节点，改变语句结构，不能执行时改变
        // 1,获取执行语句
        // 获取当前拦截到代理对象  多态
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget(); //RoutingStatementHandler
        //调用系统MetaObject接口，可以拿到该statementHandler中的所有属性
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        //调用该类的所有属性   StatementHandler->RoutingStatementHandler(delegate)  ->BaseStatementHandler(boundSql) ->BoundSql->sql
        String executeSql = (String) metaObject.getValue("delegate.boundSql.sql");
        System.out.println("拦截到正在执行的sql语句:" + executeSql);
        // 2,判断当前sql是否为查询语句
        //如果不含有select 关键字，说明不是查询语句 执行放行
        if (executeSql.indexOf("select") == -1) {
            return invocation.proceed(); //继续往下走
        }
        // 3,判断有没有携带分页参数
        //获取BoundSql
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        //再根据BoundSql获取当前执行sql语句的参数
        Object parameterObject = boundSql.getParameterObject();
        // 判断语句执行参数是否为空 , 说明没有携带分页参数，程序继续
        if (parameterObject == null) {
            return invocation.proceed(); //继续往下走
        }
        //实例化空对象
        Page page = null;
        //判断当前对象是否是Page类的实例对象
        if (parameterObject instanceof Page) {
            page = (Page) parameterObject;
        } else {
            return invocation.proceed(); //继续往下走
        }
        // 获取当前第几页
        Integer pageNo = page.getPageNo();
        if (pageNo == null) {
            //如果没有传值 赋默认值1
            page.setPageNo(defaultPageNo);
        }
        //获取每页显示数量
        Integer pageSize = page.getPageSize();
        if (pageSize == null) {
            page.setPageSize(defaultPageSize);
        }
        // 为总数量赋值
        //调用底层jdbc查询总数量
        //获取connection对象
        Connection connection = (Connection) invocation.getArgs()[0];
        //executeSql  正在执行的sql select deptno,dname as deptName,loc from dept
        String totalSql = "select count(*) cn from (" + executeSql + ")";
        //获取preparedStatement对象  执行语句
        PreparedStatement preparedStatement = connection.prepareStatement(totalSql);
        ResultSet resultSet = preparedStatement.executeQuery();
        int total = 0;
        if (resultSet.next()) {
            total = resultSet.getInt("cn");
        }
        // 为page赋值
        page.setTotal(total);
        // 为总页数赋值  总数量对每页显示条数取余  为0 说明整除  不为0说明有余数，总页数+1
        int pageCount = total % page.getPageSize() == 0 ? total / page.getPageSize() : (total / page.getPageSize() + 1);
        page.setPageCount(pageCount);

        //执行分页查询 ，查询真正的数据
        //计算开始值和结束值
        int start = (page.getPageNo() - 1) * page.getPageSize();
        int end = page.getPageNo() * page.getPageSize() + 1;
        //拼装分页sql
        String pageSql = "select  * from ( select rownum rn,a.* from (" + executeSql + ") a where rownum<?) b where b.rn>?";
        //使用MetaObject 赋值给 ps对象执行的语句
        metaObject.setValue("delegate.boundSql.sql", pageSql);
        //让程序继续运行
        PreparedStatement ps = (PreparedStatement) invocation.proceed();
        // 放入执行语句
        // ps.execute(pageSql);
        //设置参数
        ps.setInt(1, end);
        ps.setInt(2, start);

        // Object target = invocation.getTarget();//代理（拦截）对象
    /*    Method method = invocation.getMethod();
        System.out.println("拦截类的名称："+statementHandler);
        System.out.println("拦截方法的名称："+method.getName());
      //  Object[] args = invocation.getArgs();
       // System.out.println(args[0]);
        *//*if(args!=null&&args.length>0) {
            for (Object arg : args) {
                System.out.println("拦截到的执行方法的参数：" + arg.getClass().getName());
            }
        }*//*
        System.out.println("初始化第几页："+defaultPageNo);
        System.out.println("初始化第几页："+defaultPageSize);
       //处理自己业务
        System.out.println("在执行之前执行业务。。。。");
        Object proceed = invocation.proceed();// proceed 继续StatementHandler 的 prepare 方法 ，自己的sql语句需要分页，拼装分页sql
        System.out.println("在执行之后执行业务。。。。");*/
        return ps;
    }

    //返回代理对象
    @Override
    public Object plugin(Object target) {
        //生成代理对象  this  =  PagePlugin的对象  重写
        return Plugin.wrap(target, this);
    }

    //设置代理类的初始化参数
    @Override
    public void setProperties(Properties properties) {
        // 获取插件初始化参数，复制给插件里面的属性
        this.defaultPageNo = Integer.valueOf(properties.getProperty("dtPageNo"));
        this.defaultPageSize = Integer.valueOf(properties.getProperty("dtPageSize"));
    }
}
