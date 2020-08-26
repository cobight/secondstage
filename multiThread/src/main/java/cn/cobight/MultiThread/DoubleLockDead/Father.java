package cn.cobight.MultiThread.DoubleLockDead;

/**
 * @ClassName Father
 * @Description TODO
 * @Author cobight
 * @CreateTime 2020/8/25 14:57
 * @Version 1.0
 **/
public class Father {
    public void say(){
        System.out.println("给我成绩单，我给你玩具");
    }
    public void get(){
        System.out.println("获取成绩单");
    }
}
