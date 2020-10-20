package com.aaa.mybatis.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * fileName:SqlSessionFactoryUtil
 * description: SqlSessionFactory工具类，提供工厂的创建方法及生产SqlSession方法
 * author:zz
 * createTime:2020/9/15 11:31
 * version:1.0.0
 */
public class SqlSessionFactoryUtil {

    //1 私有构造
    private SqlSessionFactoryUtil(){};
    //2 静态私有属性
    private static SqlSessionFactory sqlSessionFactory;

    // 静态单例模式
    static{

        InputStream inputStream = null;
        try {
            //mybatis提供Resources  中提供getResourceAsStream 根据资源得到流
             inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //mybatis提供SqlSessionFactoryBuilder 中提供build方法用来创建工厂
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(inputStream!=null){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    //3 提供对外创建SqlSession方法
    public static SqlSession getSqlSession(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
       /* Connection connection = sqlSession.getConnection();
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return sqlSession;
     //   return  sqlSessionFactory.openSession(ExecutorType.SIMPLE);
     //  return  sqlSessionFactory.openSession();
    }
}
