package cn.cobight.annotation.override;

import java.util.HashMap;

/**
 * fileName:ZhangFei
 * description:
 * author:zz
 * createTime:2020/8/27 8:56
 * version:1.0.0
 */
public class ZhangFei extends Person {

    /**
     * @ Override重写方法要求：
     * 1，方法名称必须一致和参数类型，位置，个数必须一致
     * 2, 权限修饰符 子类必须大于等于父类  public  pretected   default  private
     * 3,  返回值  必须相同或者是父类的子孙类
     * 4,  异常   必须相同或者是父类子孙类
     */
    @Override
    public HashMap introduce(String name, int age) throws  NullPointerException{
        return null;
    }
}
