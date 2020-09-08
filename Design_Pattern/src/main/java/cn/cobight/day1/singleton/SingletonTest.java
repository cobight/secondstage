package cn.cobight.day1.singleton;

/**
 * fileName:SingletonTest
 * description:
 * author:zz
 * createTime:2020/9/3 11:48
 * version:1.0.0
 */
public class SingletonTest {
    public static void main(String[] args) {
       // StarvingSingleton starvingSingleton =new StarvingSingleton();
      //  System.out.println(StarvingSingleton.starvingSingleton);
        StarvingSingleton starvingSingleton =
                StarvingSingleton.getStarvingSingleton();
        StarvingSingleton starvingSingleton1 =
                StarvingSingleton.getStarvingSingleton();
        StarvingSingleton starvingSingleton2 =
                StarvingSingleton.getStarvingSingleton();
        StarvingSingleton starvingSingleton3 =
                StarvingSingleton.getStarvingSingleton();
        System.out.println(starvingSingleton==starvingSingleton1);
        System.out.println(starvingSingleton1==starvingSingleton2);
        System.out.println(starvingSingleton2==starvingSingleton3);
        System.out.println(starvingSingleton==starvingSingleton3);
    }
}
