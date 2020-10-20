package cn.cobight.singleton;

/**
 * fileName:GeneralCustomer
 * description:
 * author:cobight
 * createTime:2020/9/8 15:08
 * version:1.0.0
 */
public class GeneralCustomer implements HouseInterface {
    @Override
    public void buy() {
        System.out.println("买房");
    }

    @Override
    public void sell() {
        System.out.println("卖房");
    }
}
