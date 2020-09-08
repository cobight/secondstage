package cn.cobight.day1.factory.method;


import cn.cobight.day1.factory.simple.SoftwareTechnology;

/**
 * fileName:StandaredFactory
 * description:
 * author:zz
 * createTime:2020/9/3 10:47
 * version:1.0.0
 */
public interface StandaredFactory {

    /**
     * 定义工厂接口（工厂规范）
     */
    SoftwareTechnology teachST();
}
