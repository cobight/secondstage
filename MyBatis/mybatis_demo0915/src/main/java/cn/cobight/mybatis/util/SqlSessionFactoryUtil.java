package cn.cobight.mybatis.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * fileName:SqlSessionFactoryUtil
 * description: 工具类，提供工厂的创建方法及生产SqlSession方法
 * author:cobight
 * createTime:2020/9/15 20:37
 * version:1.0.0
 */
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
