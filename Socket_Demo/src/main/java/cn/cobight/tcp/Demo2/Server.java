package cn.cobight.tcp.Demo2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * fileName:Server
 * description:
 * author:cobight
 * createTime:2020/8/31 10:50
 * version:1.0.0
 */
public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Scanner scanner = new Scanner(System.in);
        Socket socket = null;

        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        OutputStream outputStream = null;
        PrintWriter printWriter = null;
        try {
            serverSocket = new ServerSocket(7777);
            System.out.println("服务端准备完毕，等待客户端发送消息！");
            socket = serverSocket.accept();
            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);

            while (true){
                String clientmsg = bufferedReader.readLine();
                System.out.println("客户端说："+clientmsg);
                System.out.println("请输入：");
                String serverMsg = scanner.next();
                printWriter.println(serverMsg);
                printWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream!=null)outputStream.close();
                if (printWriter!=null)printWriter.close();
                if (inputStream!=null)inputStream.close();
                if (bufferedReader!=null)bufferedReader.close();
                if (socket!=null)socket.close();
                if (serverSocket!=null)serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }

        }
    }
}
