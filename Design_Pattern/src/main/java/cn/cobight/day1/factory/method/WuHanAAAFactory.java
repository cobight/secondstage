package cn.cobight.day1.factory.method;

import cn.cobight.day1.factory.simple.CDevolopTechnology;
import cn.cobight.day1.factory.simple.SoftwareTechnology;

/**
 * fileName:WuHanAAAFactory
 * description:
 * author:zz
 * createTime:2020/9/3 10:49
 * version:1.0.0
 */
public class WuHanAAAFactory implements StandaredFactory{
    @Override
    public SoftwareTechnology teachST() {
        return new CDevolopTechnology();
    }
}
