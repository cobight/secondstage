package cn.cobight.beforeclass;

/**
 * fileName:zhengzhou21CenturyHouseAgent
 * description:
 * author:cobight
 * createTime:2020/9/8 15:09
 * version:1.0.0
 */
public class zhengzhou21CenturyHouseAgent implements StandardHouseAgent{
    private static volatile zhengzhou21CenturyHouseAgent z = null;
    private zhengzhou21CenturyHouseAgent(){}
    public static zhengzhou21CenturyHouseAgent getZhengzhou21CenturyHouseAgent(){
        if (z==null){
            synchronized (zhengzhou21CenturyHouseAgent.class){
                if (z==null){
                    z=new zhengzhou21CenturyHouseAgent();
                }
            }
        }
        return z;
    }
    @Override
    public HouseInterface createCustomer() {
        return new GeneralCustomer();
    }
}
