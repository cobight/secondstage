package cn.cobight.singleton;

/**
 * fileName:shenzhen21CenturyHouseAgent
 * description:
 * author:cobight
 * createTime:2020/9/8 15:17
 * version:1.0.0
 */
public class shenzhen21CenturyHouseAgent implements StandardHouseAgent{
    private shenzhen21CenturyHouseAgent(){}
    private static volatile shenzhen21CenturyHouseAgent s = null;
    public static synchronized shenzhen21CenturyHouseAgent getshenzhen21CenturyHouseAgent2(){
        if (s == null){//synchronized会让多线程阻塞
            s = new shenzhen21CenturyHouseAgent();
        }
        return s;
    }
    public static shenzhen21CenturyHouseAgent getshenzhen21CenturyHouseAgent(){
        if (s == null){//开始创建的时候会阻塞，后来就不会阻塞了
            synchronized (shenzhen21CenturyHouseAgent.class){
                if (s == null) {
                    s = new shenzhen21CenturyHouseAgent();
                }
            }
        }
        return s;
    }

    @Override
    public HouseInterface createCustomer() {
        return new VimCustomer();
    }
}
