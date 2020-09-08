package cn.cobight.day2.proxy.static1;

/**
 * fileName:Account
 * description:  账户服务，编写委托类和代理类的共同接口  静态代理
 * author:zz
 * createTime:2020/9/7 11:06
 * version:1.0.0
 */
public interface Account {


    /**
     * 查询余额
     */
    void  queryAccountMoney();

    /**
     * 存取钱服务
     * @param num
     */
    void  updateMoney(double num);


    //...其他服务
}
