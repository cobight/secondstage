package cn.cobight.day1.factory.abstract1;


import cn.cobight.day1.factory.simple.SoftwareTechnology;

/**
 * fileName:StandaredAbstractFactory
 * description:
 * author:zz
 * createTime:2020/9/3 11:12
 * version:1.0.0
 */
public interface StandaredAbstractFactory {

    /**
     * 教授挖掘机技术
     * @return
     */
    DiggerTechonology teachDT();

    /**
     * 教授软件技术
     * @return
     */
    SoftwareTechnology teachST();
}
