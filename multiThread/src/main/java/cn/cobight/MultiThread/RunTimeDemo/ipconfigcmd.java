package cn.cobight.MultiThread.RunTimeDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ipconfigcmd {
    public static void main(String[] args) {
        s();
    }
    public static void s() {
        Runtime runtime = Runtime.getRuntime();
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            Process process = runtime.exec("ipconfig");
            inputStream = process.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                System.out.println(str);
            }
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
}
