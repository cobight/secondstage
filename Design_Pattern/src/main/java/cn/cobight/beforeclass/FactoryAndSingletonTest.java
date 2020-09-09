package cn.cobight.beforeclass;

/**
 * fileName:FactoryAndSingletonTest
 * description:
 * author:cobight
 * createTime:2020/9/8 15:21
 * version:1.0.0
 */
public class FactoryAndSingletonTest {
    public static void main(String[] args) {
        zhengzhou21CenturyHouseAgent z = zhengzhou21CenturyHouseAgent.getZhengzhou21CenturyHouseAgent();
        HouseInterface GeneralCustomer = z.createCustomer();
        GeneralCustomer.buy();
        GeneralCustomer.sell();
        shenzhen21CenturyHouseAgent s = shenzhen21CenturyHouseAgent.getshenzhen21CenturyHouseAgent();
        HouseInterface VipCustomer = s.createCustomer();
        VipCustomer.buy();
        VipCustomer.sell();
    }
}
