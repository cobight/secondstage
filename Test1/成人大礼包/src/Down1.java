import cn.cobight.Version2_SplitePack.BiliTool;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * fileName:Down1
 * description:
 * author:cobight
 * createTime:2020/9/7 21:43
 * version:1.0.0
 */
public class Down1 {
    public static void main(String[] args) throws Exception {
        BiliTool.downbili("BV1o5411873u");
        M3u8ThreadDownLoad.down("https://play.093ch.com/20200126/90/901/901.mp4.m3u8",12);
    }
}
