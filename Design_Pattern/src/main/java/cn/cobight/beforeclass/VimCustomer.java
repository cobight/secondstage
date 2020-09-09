package cn.cobight.beforeclass;

/**
 * fileName:VimCustomer
 * description:
 * author:cobight
 * createTime:2020/9/8 15:16
 * version:1.0.0
 */
public class VimCustomer implements HouseInterface{
    @Override
    public void buy() {
        System.out.println("买需多房");
    }

    @Override
    public void sell() {
        System.out.println("卖许多房");
    }
}
