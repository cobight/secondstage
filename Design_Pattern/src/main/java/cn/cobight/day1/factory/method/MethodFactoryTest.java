package cn.cobight.day1.factory.method;


import cn.cobight.day1.factory.simple.SoftwareTechnology;

/**
 * fileName:MethodFactoryTest
 * description:
 * author:zz
 * createTime:2020/9/3 10:56
 * version:1.0.0
 */
public class MethodFactoryTest {
    public static void main(String[] args) {
      /*  WuHanAAAFactory wuHanAAAFactory =new WuHanAAAFactory();
        SoftwareTechnology softwareTechnology = wuHanAAAFactory.teachST();
        softwareTechnology.studyST();*/
        ShenZhenAAAFactory shenzhenAAAFactory =new ShenZhenAAAFactory();
        SoftwareTechnology softwareTechnology = shenzhenAAAFactory.teachST();
        softwareTechnology.studyST();
    }
}
