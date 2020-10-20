package cn.cobight.sbm.icodemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * fileName:Person
 * description:
 * author:cobight
 * createTime:2020/10/13 9:57
 * version:1.0.0
 */

@Component
public class Person {
    @Value("guanyu")
    private String name;
    @Autowired
    private Horse horse;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Horse getHorse() {
        return horse;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }
    public void fighting(){
        System.out.println(name+"出征了...........");
        horse.run();
    }
}
