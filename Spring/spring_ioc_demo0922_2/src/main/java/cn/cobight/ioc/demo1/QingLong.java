package cn.cobight.ioc.demo1;

import org.springframework.stereotype.Component;

/**
 * fileName:QingLong
 * description:
 * author:cobight
 * createTime:2020/9/22 20:30
 * version:1.0.0
 */
@Component
public class QingLong implements Weapon {
    @Override
    public void attack() {
        System.out.println("青龙刀，砍敌如切菜");
    }
}
