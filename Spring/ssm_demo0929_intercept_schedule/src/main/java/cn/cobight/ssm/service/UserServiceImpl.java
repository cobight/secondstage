package cn.cobight.ssm.service;

import cn.cobight.ssm.dao.UserDao;
import cn.cobight.ssm.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * fileName:UserServiceImpl
 * description:
 * author:cobight
 * createTime:2020/9/29 9:34
 * version:1.0.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

//    @Autowired
//    private HttpSession session;

    @Override
    public List<Users> list(Map map) {
        return userDao.list(map);
    }

    @Override
    public boolean userLogin(String userName, String passWord, HttpSession session) {
        if (StringUtils.isEmpty(userName)||StringUtils.isEmpty(passWord))return false;
        Map paramMap =new HashMap();
        paramMap.put("userName",userName);
        paramMap.put("passWord",passWord);
        List<Users> usersList = list(paramMap);
        if (usersList!=null&&usersList.size()>0){
            session.setAttribute("userInfo",usersList.get(0));
            return true;
        }
        return false;
    }


}
