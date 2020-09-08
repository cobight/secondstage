package cn.cobight.day1.factory.simple;

/**
 * fileName:AAAFactoryTest
 * description:
 * author:zz
 * createTime:2020/9/3 10:34
 * version:1.0.0
 */
public class AAAFactoryTest {
    public static void main(String[] args) {
        //测试不同参数返回不同对象
        SoftwareTechnology softwareTechnology = AAAFactory.teachST(1);
        if(softwareTechnology!=null){
            softwareTechnology.studyST();
        }
    }
}
