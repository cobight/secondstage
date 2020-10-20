package cn.cobight.ioc.demo2;

import org.springframework.stereotype.Component;

/**
 * fileName:A5Paper
 * description:
 * author:cobight
 * createTime:2020/9/23 8:31
 * version:1.0.0
 */
@Component
public class A5Paper implements Paper {
    @Override
    public void setPaper() {
        System.out.println("使用A5纸打印...");
    }
}
