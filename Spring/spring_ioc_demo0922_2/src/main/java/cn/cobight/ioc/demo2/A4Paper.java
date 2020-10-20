package cn.cobight.ioc.demo2;

import org.springframework.stereotype.Component;

/**
 * fileName:A4Paper
 * description:
 * author:cobight
 * createTime:2020/9/23 14:51
 * version:1.0.0
 */
@Component
public class A4Paper implements Paper {
    @Override
    public void setPaper() {
        System.out.println("使用A4纸打印...");
    }
}
