package cn.cobight.entity;

/**
 * @ClassName Student
 * @Description TODO
 * @Author cobight
 * @CreateTime 2020/8/25 9:58
 * @Version 1.0
 **/
public class Student {
    private Integer stuId;
    private String stuNo;
    private String stuName;
    private String email;
    private int age;

    public Student() {
    }

    public Student(Integer stuId, String stuNo) {
        this.stuId = stuId;
        this.stuNo = stuNo;
    }

    public Student(Integer stuId, String stuNo, String stuName) {
        this.stuId = stuId;
        this.stuNo = stuNo;
        this.stuName = stuName;
    }

    public Student(Integer stuId, String stuNo, String stuName, String email, int age) {
        this.stuId = stuId;
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.email = email;
        this.age = age;
    }

    public void eat() {
        System.out.println("多喝热水，少熬夜");
    }

    public String lean(String courseName) {
        return stuName + "正在学习" + courseName + "课程";
    }

    public void playGame(String gameType, String gameName) {
        System.out.println(stuName + "正在玩" + gameType + "类型的" + gameName + "游戏");
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
