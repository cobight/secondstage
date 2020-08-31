package cn.cobight.entity;

/**
 * @ClassName Teacher
 * @Description TODO
 * @Author cobight
 * @CreateTime 2020/8/26 18:37
 * @Version 1.0
 **/
public class Teacher {
    private int id;
    private String username;
    private String realname;
    private int age;
    private String mobilenum;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobilenum() {
        return mobilenum;
    }

    public void setMobilenum(String mobilenum) {
        this.mobilenum = mobilenum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
