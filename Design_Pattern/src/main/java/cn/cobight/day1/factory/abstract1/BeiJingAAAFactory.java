package cn.cobight.day1.factory.abstract1;


import cn.cobight.day1.factory.simple.SoftwareTechnology;

/**
 * fileName:BeiJingAAAFactory
 * description:
 * author:zz
 * createTime:2020/9/3 11:22
 * version:1.0.0
 */
public class BeiJingAAAFactory implements StandaredAbstractFactory {
    @Override
    public DiggerTechonology teachDT() {
        return new BigDiggerOperationTechnology();
    }

    @Override
    public SoftwareTechnology teachST() {
        return new UIDevolopTechnology();
    }
}
