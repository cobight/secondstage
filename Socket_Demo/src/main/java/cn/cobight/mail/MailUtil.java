package cn.cobight.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.util.List;

/**
 * fileName:MailUtil
 * description:
 * author:cobight
 * createTime:2020/8/31 20:36
 * version:1.0.0
 */

public class MailUtil {

    public boolean send(Mail mail) {

        try {
            List<String> receivers = mail.getReceiver();
            for (String receiver : receivers) {
                //发送email对象
                HtmlEmail email = new HtmlEmail();
                //这里是SMTP发送服务器的名字
                email.setHostName(mail.getHost());
                //端口号不为空时,用户自定义的端口号为SMTP发送服务器端口号
                if (!"".equals(mail.getPortNumber())) {
                    email.setSSLOnConnect(true);
                    email.setSslSmtpPort(mail.getPortNumber());
                }
                //字符编码集的设置
                email.setCharset(Mail.ENCODEING);
                //发送人的邮箱
                email.setFrom(mail.getUsername(), mail.getName());
                // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
                email.setAuthentication(mail.getUsername(), mail.getPassword());
                // 要发送的邮件主题
                email.setSubject(mail.getSubject());
                // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
                email.setMsg(mail.getMessage());
                //收件人的邮箱
                email.addTo(receiver);
                // 发送
                email.send();
            }
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            return false;
        }
    }
}
