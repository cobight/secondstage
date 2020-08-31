package cn.cobight.annotation.deprecated;

import java.util.Calendar;
import java.util.Date;

/**
 * fileName:Person
 * description:
 * author:zz
 * createTime:2020/8/27 9:07
 * version:1.0.0
 */
public class Person {

    /**
     * 自我介绍
     * @param name
     * @param age
     * @return
     */
    @Deprecated
    public String introduce(String name, int age) {
        return "我叫"+name+"今年"+age;
    };


    public static void main(String[] args) {
        Person person = new Person();
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
