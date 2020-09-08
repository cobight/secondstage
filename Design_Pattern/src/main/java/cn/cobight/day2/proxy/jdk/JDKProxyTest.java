package cn.cobight.day2.proxy.jdk;

/**
 * fileName:JDKProxyTest
 * description:
 * author:zz
 * createTime:2020/9/7 11:57
 * version:1.0.0
 */
public class JDKProxyTest {
    public static void main(String[] args) {
        Client client = new Client();
        JDKEmpProxy jdkEmpProxy =new JDKEmpProxy(client);
        //多态
        Account account =   (Account)jdkEmpProxy.getJdkProxy();
        account.queryAccountMoney();
        account.updateMoney(100000);
    }
}
