package cn.cobight.tcp.Demo1;

import java.io.*;
import java.net.Socket;

/**
 * fileName:Client
 * description:
 * author:cobight
 * createTime:2020/8/31 10:31
 * version:1.0.0
 */
public class Client {

    public static void main(String[] args) {

        Socket socket = null;
        OutputStream outputStream = null;
        PrintWriter printWriter = null;

        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            //实例化，绑IP和Port
            while (true) {
                try {
                    socket = new Socket("192.168.1.5", 7777);
                } catch (Exception e) {

                }

                if (socket != null) break;
            }

            //发数据
            String msg = "今天就是天王老子来了，我也要活捉张黎明！";
            while (true) {
                outputStream = socket.getOutputStream();
                printWriter = new PrintWriter(outputStream);
                printWriter.println(msg);
                printWriter.flush();
                //服务器回消息
                inputStream = socket.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                try {
                    String back = bufferedReader.readLine();
                    System.out.println("受到数据：" + back);
                } catch (Exception e) {

                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) inputStream.close();
                if (bufferedReader != null) bufferedReader.close();
                if (outputStream != null) outputStream.close();
                if (printWriter != null) printWriter.close();
                if (socket != null) socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
