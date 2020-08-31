package cn.cobight.tcp.Demo2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * fileName:Client
 * description:
 * author:cobight
 * createTime:2020/8/31 11:00
 * version:1.0.0
 */
public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream outputStream = null;
        PrintWriter printWriter = null;

        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        Scanner scanner = new Scanner(System.in);
        try {
            socket = new Socket("192.168.1.5",7777);
            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true){
                System.out.println("请输入：");
                String clientMsg = scanner.next();
                printWriter.println(clientMsg);
                printWriter.flush();
                String readLine = bufferedReader.readLine();
                System.out.println(readLine);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream!=null)inputStream.close();
                if (bufferedReader!=null)bufferedReader.close();
                if (outputStream!=null)outputStream.close();
                if (printWriter!=null)printWriter.close();
                if (socket!=null)socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
