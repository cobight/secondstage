package com.aaa.sbm.iocdemo;

/**
 * fileName:Person
 * description:
 * author:zz
 * createTime:2020/10/13 9:52
 * version:1.0.0
 */
public class Person {


    private String name;

    private Horse horse;

    public void fighting(){
        System.out.println(name+"出征了");
        horse.run();
    }

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
}
