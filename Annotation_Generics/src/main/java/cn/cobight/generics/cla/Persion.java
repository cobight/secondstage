package cn.cobight.generics.cla;

import java.util.Date;

/**
 * @ClassName Persion
 * @Description 类泛型,成员变量就能用了，构造方法也就能用了
 * @Author cobight
 * @CreateTime 2020/8/28 15:29
 * @Version 1.0
 **/
public class Persion<T,E,A> {
    private T name;
    private E age;
    private A email;
    //打印名称
    public void printName(){
        System.out.println(this.name+"\t"+this.age+"\t"+this.email);
    }

    public Persion(T name, E age, A email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public E getAge() {
        return age;
    }

    public void setAge(E age) {
        this.age = age;
    }

    public A getEmail() {
        return email;
    }

    public void setEmail(A email) {
        this.email = email;
    }

    public static void main(String[] args) {
        Persion<String,Integer,Character> persion = new Persion<>("牛皮",1,'q');
        persion.printName();
    }
}
