package cn.cobight.singleton.proxy;

import cn.cobight.singleton.GeneralCustomer;
import cn.cobight.singleton.HouseInterface;

/**
 * fileName:ProxyTest
 * description:
 * author:cobight
 * createTime:2020/9/8 15:32
 * version:1.0.0
 */
public class ProxyTest {
    public static void main(String[] args) {
        GeneralCustomer generalCustomer = new GeneralCustomer();
        HouseProxy21CenturyJDK houseProxy21CenturyJDK = new HouseProxy21CenturyJDK(generalCustomer);
        HouseInterface proxyInstance =  houseProxy21CenturyJDK.getProxyInstance();
        proxyInstance.buy();
        proxyInstance.sell();
    }
}
