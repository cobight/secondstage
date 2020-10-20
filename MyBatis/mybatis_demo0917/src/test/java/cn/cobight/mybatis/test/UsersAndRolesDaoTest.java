package cn.cobight.mybatis.test;

import cn.cobight.mybatis.dao.UsersAndRolesDao;
import cn.cobight.mybatis.entity.Roles;
import cn.cobight.mybatis.entity.Users;
import cn.cobight.mybatis.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import java.util.List;

/**
 * fileName:UsersAndRolesDaoTest
 * description:
 * author:cobight
 * createTime:2020/9/17 20:13
 * version:1.0.0
 */
public class UsersAndRolesDaoTest {
    @Test
    public void usersAndRoles(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            UsersAndRolesDao usersAndRolesDao = sqlSession.getMapper(UsersAndRolesDao.class);
            List<Users> listGetUsersAndRoles = usersAndRolesDao.listGetUsersAndRoles();
            if (listGetUsersAndRoles!=null&& listGetUsersAndRoles.size()>0){
                for (Users users : listGetUsersAndRoles) {
                    System.out.println(users.getUserId()+"\t"+users.getUserName()+"\t"+users.getRealName()+"\t"+users.getAge());
                    List<Roles> roles = users.getRoles();
                    if (roles!=null&&roles.size()>0){
                        for (Roles role : roles) {
                            System.out.println("-------"+role.getRoleId()+"\t"+role.getRoleName());
                        }
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
