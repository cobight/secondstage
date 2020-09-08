package cn.cobight.day1.singleton;

/**
 * fileName:StarvingSingleton
 * description: 饿汉模式
 * author:zz
 * createTime:2020/9/3 11:46
 * version:1.0.0
 */
public class StarvingSingleton {

    //1，只提供私有构造  保证在其他地方通过new不能实例化对象
    private StarvingSingleton(){
    };
    //2, 静态(伴随着类的加载而产生，只产生一次)私有（不允许在其他地方进行调用）该类的对象属性
    private static StarvingSingleton starvingSingleton = new StarvingSingleton();

    public static StarvingSingleton getStarvingSingleton(){
        return starvingSingleton;
    }
}
