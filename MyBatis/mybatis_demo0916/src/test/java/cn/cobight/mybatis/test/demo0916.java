package cn.cobight.mybatis.test;

import cn.cobight.mybatis.dao.DeptDao;
import cn.cobight.mybatis.dao.NewTypeDao;
import cn.cobight.mybatis.entity.Dept;
import cn.cobight.mybatis.entity.NewsType;
import cn.cobight.mybatis.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * fileName:demo0916
 * description:
 * author:cobight
 * createTime:2020/9/16 8:30
 * version:1.0.0
 */
/*在已经搭建好mybatis的项目中使用mybatis对新闻分类表tb_news_type 进行查询
  要求：1，dao层接口为  NewTypeDao   方法名称为 listNewsType  实体名称NewsType
        2，mapper文件为  NewTypeMapper.xml
	    3，测试类名称为  NewTypeDaoTest  方法为 testListNewsType
        4，打印新闻分类名称和添加时间*/
public class demo0916 {
    @Test
    public void testListNewsType(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用JDK代理（代理接口）生成DeptDao代理对象
            NewTypeDao deptDao = sqlSession.getMapper(NewTypeDao.class);//多态
            //获取列表
            List<NewsType> deptList = deptDao.listNewsType();
            //判断循环 输出
            if (deptList!=null&&deptList.size()>0){
                for (NewsType dept : deptList) {
                    System.out.println("名称："+dept.gettName()+",\t时间：");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }
}
