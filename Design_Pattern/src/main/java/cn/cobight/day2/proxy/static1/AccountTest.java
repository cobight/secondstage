package cn.cobight.day2.proxy.static1;

/**
 * fileName:AccountTest
 * description:
 * author:zz
 * createTime:2020/9/7 11:20
 * version:1.0.0
 */
public class AccountTest {
    public static void main(String[] args) {
        //实例化委托类
        Client client = new Client();
        //实例化代理类
        Employee employee = new Employee(client);
        //执行 查询余额
        employee.queryAccountMoney();
        //执行 存取前
        employee.updateMoney(200000);
    }
}
