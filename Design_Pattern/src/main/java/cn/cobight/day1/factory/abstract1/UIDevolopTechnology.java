package cn.cobight.day1.factory.abstract1;


import cn.cobight.day1.factory.simple.SoftwareTechnology;

/**
 * fileName:UIDevolopTechnology
 * description:
 * author:zz
 * createTime:2020/9/3 11:18
 * version:1.0.0
 */
public class UIDevolopTechnology  implements SoftwareTechnology {
    @Override
    public void studyST() {
        System.out.println("头悬梁，锥刺股，努力学习UI技术，升职加薪，胜任CTO，赢取白富美，走向人生巅峰！");
    }
}
