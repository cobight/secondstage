package cn.cobight.day2.proxy.static1;

/**
 * fileName:Employee
 * description: 银行雇员（代理类）
 * author:zz
 * createTime:2020/9/7 11:11
 * version:1.0.0
 */
public class Employee  implements  Account{

    //委托类属性
    private Client client;

    /**
     * 使用构造给委托类赋值
     * @param client
     */
    public Employee(Client client) {
        this.client = client;
    }

    @Override
    public void queryAccountMoney() {
        System.out.println("代理类：准备ATM机。。。。。");
        //执行委托类的查询余额方法
        client.queryAccountMoney();
        System.out.println("代理类：修改ATM机。。。。。");
    }

    @Override
    public void updateMoney(double num) {
        System.out.println("代理类：大额存取准备工作。。。。");
        //执行委托类的存取钱方法
        client.updateMoney(num);
        System.out.println("代理类：善后工作。。。");
    }


}
