package cn.cobight.ioc.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * fileName:Person
 * description:
 * author:cobight
 * createTime:2020/9/22 20:21
 * version:1.0.0
 */
@Controller("userC")  //相当于前两个项目中中的bean
@Scope("prototype")
public class Person {
    @Value("关羽")
    private String name;
    @Autowired
    private ChiTu chiTu;
    @Autowired
    private QingLong qingLong;

    public void fight(){
        System.out.println(name+"出征了");
        chiTu.run();
        qingLong.attack();
    }
}
