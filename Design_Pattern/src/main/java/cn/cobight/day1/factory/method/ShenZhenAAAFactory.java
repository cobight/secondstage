package cn.cobight.day1.factory.method;


import cn.cobight.day1.factory.simple.SoftwareTechnology;

/**
 * fileName:ShenZhenAAAFactory
 * description:
 * author:zz
 * createTime:2020/9/3 10:52
 * version:1.0.0
 */
public class ShenZhenAAAFactory implements StandaredFactory {
    @Override
    public SoftwareTechnology teachST() {
        return new BigDataDevolopTechnology();
    }
}
