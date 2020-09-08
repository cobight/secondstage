package cn.cobight.day1.factory.abstract1;


import cn.cobight.day1.factory.simple.SoftwareTechnology;

/**
 * fileName:AbstractAAAFactoryTest
 * description:
 * author:zz
 * createTime:2020/9/3 11:23
 * version:1.0.0
 */
public class AbstractAAAFactoryTest {
    public static void main(String[] args) {
        BeiJingAAAFactory beiJingAAAFactory = new BeiJingAAAFactory();
        DiggerTechonology diggerTechonology = beiJingAAAFactory.teachDT();
        diggerTechonology.studyDT();
        SoftwareTechnology softwareTechnology = beiJingAAAFactory.teachST();
        softwareTechnology.studyST();
    }
}
