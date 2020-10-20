package cn.cobight.sbm.service;

import cn.cobight.sbm.dao.UserDao;
import cn.cobight.sbm.entity.User;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * fileName:UserServiceImpl
 * description:
 * author:zz
 * createTime:2020/9/29 9:34
 * version:1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired //byType
    private UserDao userDao;

   /* @Autowired
    private  HttpSession session;*/

    @Override
    public List<User> list(Map map) {
        return userDao.list(map);
    }

    @Override
    public boolean userLogin(String userName, String passWord) {
        try {
       // if(userName==null||"".equals(userName))  过去写法  = StringUtils.isEmpty(userName)
        //判断用户名和密码是否为空
            if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(passWord)){
                return false;
            }
            //拼装参数，查询
            Map paramMap = new HashMap();
            paramMap.put("userName",userName);
            paramMap.put("passWord",passWord);
            List<User> userList = list(paramMap);
            //判断是否有查询结果
            /*if(userList!=null&&userList.size()>0){
                 //把用户信息放入session
                session.setAttribute("userInfo",userList.get(0));
                return true;
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int add(User user) {
        //获取原始密码
        String passWord = user.getPassWord();
        //随机盐值
        String passWordSalt = UUID.randomUUID().toString().substring(0,10);
        //生成加密密码
        Sha512Hash sha512Hash = new Sha512Hash(passWord,passWordSalt,1024);
        //覆盖原密码
        user.setPassWordSalt(passWordSalt);
        user.setPassWord(sha512Hash.toString());
        return userDao.add(user);
    }

    @Override
    public List<Map> listRoleByUserId(int userId) {
        return userDao.listRoleByUserId(userId);
    }
}
