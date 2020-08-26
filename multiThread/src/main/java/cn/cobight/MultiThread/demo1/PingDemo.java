package cn.cobight.MultiThread.demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PingDemo {
    public static void pingIp(String ip) {
        //获取与当前环境运行的接口一个实例
        Runtime runtime = Runtime.getRuntime();
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            //exec = execute 执行windows下cmd中的命令，获取当前命令运行进程对象

            Process process = runtime.exec("ping " + ip);
            //获取到该对象执行命令的结果，放入输入流
            inputStream = process.getInputStream();
            //InputStreamReader 把字节流转换字符流
            //字符输入流 readLine  按行读取
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                //System.out.println("执行结果："+str);
                if (str.contains("TTL")) {
                    System.out.println(ip + "ping 通了");
                    return;
                }
            }
            System.out.println(ip + "ping 不通");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 5; i++) {
            pingIp("192.168.1."+i);
        }
        long stop = System.currentTimeMillis();
        System.out.println("用时:"+((stop-start)/1000)+"秒!");
    }
}
