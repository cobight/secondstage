package cn.cobight.mail;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * fileName:Mail
 * description:
 * author:cobight
 * createTime:2020/8/31 20:41
 * version:1.0.0
 */
public class Mail {
    private String Host;
    private String PortNumber;//默认25
    private String Name;
    private String Subject;
    private String Message;
    private String Username;
    private String Password;
    private List<String> Receiver=new ArrayList<>();
    public static String ENCODEING="utf-8";
    public void addReceiver(String... receivers){
        if (receivers!=null && receivers.length>0){
            Receiver.addAll(Arrays.asList(receivers));
        }
    }
    public List<String> getReceiver(){
        return Receiver;
    }
    public String getHost() {
        return Host;
    }

    public void setHost(String host, String portNumber) {
        Host = host;
        PortNumber = portNumber;
    }

    public String getPortNumber() {
        return PortNumber;
    }

    public String getName() {
        return Name;
    }

    public String getSubject() {
        return Subject;
    }

    public String getMessage() {
        return Message;
    }

    public void setMail(String name, String subject,String message) {
        Name = name;
        Subject = subject;
        Message = message;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username, String password) {
        Username = username;
        Password = password;
    }

    public String getPassword() {
        return Password;
    }
}
