package cn.cobight.ssm.service;
import cn.cobight.ssm.entity.Users;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * fileName:UserService
 * description:
 * author:cobight
 * createTime:2020/9/29 9:32
 * version:1.0.0
 */
public interface UserService {
    List<Users> list(Map map);
    boolean userLogin(String userName, String passWord, HttpSession session);
}
