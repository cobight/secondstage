package cn.cobight.day2.proxy.jdk;



/**
 * fileName:Client
 * description: 客户 （委托类）
 * author:zz
 * createTime:2020/9/7 11:08
 * version:1.0.0
 */
public class Client implements  Account {
    @Override
    public void queryAccountMoney() {
        System.out.println("----------查询余额。。。。");
    }

    @Override
    public void updateMoney(double num) {
        System.out.println("----------存/取"+num+"元钱");
    }
}
