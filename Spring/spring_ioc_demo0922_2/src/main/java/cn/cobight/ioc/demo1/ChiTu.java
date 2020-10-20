package cn.cobight.ioc.demo1;

import org.springframework.stereotype.Component;

/**
 * fileName:ChiTu
 * description:
 * author:cobight
 * createTime:2020/9/22 20:29
 * version:1.0.0
 */
@Component
public class ChiTu implements Horse {
    @Override
    public void run() {
        System.out.println("兔马快如闪电，日行千里，夜行八百");
    }
}
