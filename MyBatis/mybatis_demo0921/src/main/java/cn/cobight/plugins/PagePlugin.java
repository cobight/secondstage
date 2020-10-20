package cn.cobight.plugins;


import cn.cobight.util.Page;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
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

        Object target = invocation.getTarget();//代理（拦截）对象
        Method method = invocation.getMethod();
        System.out.println("拦截类的名称："+target.getClass().getName());
        System.out.println("拦截方法的名称："+method.getName());
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
        System.out.println("拦截sql语句:" + executeSql);

        // 3,判断有没有携带分页参数
        //获取BoundSql
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        //再根据BoundSql获取当前执行sql语句的参数
        Object parameterObject = boundSql.getParameterObject();
        //实例化空对象
        Page page = null;
        //判断当前对象是否是Page类的实例对象
        if (parameterObject instanceof Page) {
            page = (Page) parameterObject;
            System.out.println("拦截参数:"+page);
        }
        return invocation.proceed();
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
