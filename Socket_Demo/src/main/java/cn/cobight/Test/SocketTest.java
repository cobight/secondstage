package cn.cobight.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketTest {
    public static void main(String[] args) throws Exception {

        InetSocketAddress isa = new InetSocketAddress("www.cobight.cn",80);
        Socket socket = new Socket();
        socket.connect(isa);
        String strSend = "GET /index.html HTTP/1.1\r\nHost: " + "www.cobight.cn" + "\r\nConnection: Close\r\n\r\n";
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = strSend.getBytes();
        outputStream.write(bytes);
        outputStream.flush();

//        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        byte[] temp=new byte[1024];
        int len=0;
        while((len = inputStream.read(temp)) != -1) {
            System.out.println(new String(temp,0,len,"GBK"));
        }
        //要关闭各种
//        bufferedReader.close();
//        inputStreamReader.close();
        inputStream.close();
        outputStream.close();
        socket.close();

    }
}
