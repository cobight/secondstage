package cn.cobight.sbm.icodemo;

import org.springframework.stereotype.Component;

/**
 * fileName:ChiTu
 * description:
 * author:cobight
 * createTime:2020/10/13 9:57
 * version:1.0.0
 */
@Component
public class ChiTu implements Horse {
    @Override
    public void run() {
        System.out.println("chitu run");
    }
}
