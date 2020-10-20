package cn.cobight.sbm.controller;

import cn.cobight.sbm.entity.User;
import cn.cobight.sbm.service.UserService;
import cn.cobight.sbm.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * fileName:UserController
 * description:
 * author:cobight
 * createTime:2020/10/13 11:08
 * version:1.0.0
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public Result register(@RequestBody User user) {
        try {
            int rtn = userService.add(user);
            if (rtn == 1) {
                return new Result<>(200, "注册成功");
            } else if (rtn == 0) {
                return new Result<>(400, "注册失败");
            } else {
                return new Result<>(300, "注册失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(500, "注册异常");
        }
    }

    @GetMapping("login")
    public Result login(String userName, String passWord) {
        /*if (userService.userLogin(userName,passWord)){
            return new Result<>(200, "登陆成功");
        }*/
        System.out.println(userName + "\t" + passWord);
        Subject subject = SecurityUtils.getSubject();

        AuthenticationToken token = new UsernamePasswordToken(userName, passWord);

        try {
            subject.login(token);
            Session session = subject.getSession();
            session.setAttribute("userInfo", subject.getPrincipal());

            return new Result<>(200, "登陆成功");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return new Result<>(500, "用户名密码错误");
    }

}
