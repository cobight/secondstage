package cn.cobight.udp.Demo1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * fileName:Client
 * description:
 * author:cobight
 * createTime:2020/8/31 11:48
 * version:1.0.0
 */
public class Client {
    public static void main(String[] args) {
        DatagramSocket datagramSocket = null;
        try {
            //实例化接受对象
            datagramSocket = new DatagramSocket(6666);
            byte[] dataByte = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(dataByte,dataByte.length);
            System.out.println("客户端已启动，等待服务端信息！");
            //接收
            datagramSocket.receive(datagramPacket);
            //执行完接受方法后，数据报包对象就不再为空，组装数据
            String serverMsg = new String(dataByte,0,datagramPacket.getLength(),"utf-8");
            System.out.println(serverMsg);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (datagramSocket != null) datagramSocket.close();
        }
    }
}
