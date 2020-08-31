package cn.cobight.annotation.suppresswarning;

import java.util.Calendar;
import java.util.Date;

/**
 * fileName:Person
 * description:
 * author:zz
 * createTime:2020/8/27 9:15
 * version:1.0.0
 */
@SuppressWarnings("all")
public class Person {

   // @SuppressWarnings("all")
    private  String email;
    /**
     * 自我介绍
     * @param name
     * @param age
     * @return
     */
    @Deprecated
    public String introduce(@SuppressWarnings("all") String name, int age) {
        int a = 0;
       // @SuppressWarnings("all")
        int b = 0;
        return "我叫"+name+"今年"+age+","+a;
    };

    public Person() {
    }

    //@SuppressWarnings("all")
    public Person(String email) {
        this.email = email;
    }

    //@SuppressWarnings("all")
    public static void main(String[] args) {
        Person person =new Person();
        String returnStr = person.introduce("马云", 18);
        System.out.println(returnStr);
        //过时的
        Date date =new Date();
        int year = date.getYear();
        int month = date.getMonth();
        //获取日历类
        Calendar calendar = Calendar.getInstance();
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);
    }
}
