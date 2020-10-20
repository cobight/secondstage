package cn.cobight.ioc.demo2;

import org.springframework.stereotype.Component;

/**
 * fileName:ColorLnk
 * description:
 * author:cobight
 * createTime:2020/9/23 8:31
 * version:1.0.0
 */
@Component
public class ColorLnk implements  Lnk {
    @Override
    public void setColor() {
        System.out.println("使用彩色打印...");
    }
}
