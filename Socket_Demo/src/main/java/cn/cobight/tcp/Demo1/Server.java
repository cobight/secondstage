package cn.cobight.tcp.Demo1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * fileName:Server
 * description:
 * author:cobight
 * createTime:2020/8/31 9:45
 * version:1.0.0
 */
public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;

        InputStream inputStream = null;
        BufferedReader bufferedReader = null;

        OutputStream outputStream = null;
        PrintWriter printWriter = null;

        try {
            //实例化对象，绑定端口
            serverSocket = new ServerSocket(8888);
            System.out.println("等待连接：");
            //产生阻塞，监听端口
            socket = serverSocket.accept();
            inputStream = socket.getInputStream();//字节流  读字节
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));//字符流  读字符
            //读数据
            String msg = bufferedReader.readLine();
            System.out.println("客户端说：" + msg);
            //说完了响应服务端
            outputStream = socket.getOutputStream();//输出流
            printWriter = new PrintWriter(outputStream);
            printWriter.println("你好客户端，你发送的信息" + msg + "已收到，现在给你回应！");
            printWriter.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) outputStream.close();
                if (printWriter != null) printWriter.close();
                if (inputStream != null) inputStream.close();
                if (bufferedReader != null) bufferedReader.close();
                if (serverSocket != null) serverSocket.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
