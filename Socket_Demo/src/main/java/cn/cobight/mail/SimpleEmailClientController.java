package cn.cobight.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * fileName:SimpleEmailClientController
 * description:
 * author:cobight
 * createTime:2020/8/31 20:38
 * version:1.0.0
 */
public class SimpleEmailClientController {

    public static void main(String[] args) throws InterruptedException {
        Mail mail = new Mail();
        mail.setHost("smtp.qq.com","465"); // 设置邮件服务器,如果不用QQ邮箱的,自己找找看相关的
        mail.setUsername("1415470614@qq.com","wysmqzqxzwzggeaa"); // 登录账号,一般都是和邮箱名一样
        mail.setMail("发送人昵称","主题","内容");
        mail.addReceiver("1040818610@qq.com","1415470614@qq.com");
//        List list = new ArrayList();
//        list.add("1040818610@qq.com");
        long a = System.currentTimeMillis();
        if (new MailUtil().send(mail)) {
            System.out.println("发送成功");
        } else {
            System.out.println("发送失败");
        }
        long b = System.currentTimeMillis();
        System.out.println(b-a);


    }
}
