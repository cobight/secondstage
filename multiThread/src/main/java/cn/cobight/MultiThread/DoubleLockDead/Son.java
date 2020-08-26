package cn.cobight.MultiThread.DoubleLockDead;

/**
 * @ClassName Son
 * @Description TODO
 * @Author cobight
 * @CreateTime 2020/8/25 14:59
 * @Version 1.0
 **/
public class Son {
    public void say(){
        System.out.println("给我玩具，我给你成绩单");
    }
    public void get(){
        System.out.println("获取玩具");
    }
}
