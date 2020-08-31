package cn.cobight.udp.Demo1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * fileName:Server
 * description:
 * author:cobight
 * createTime:2020/8/31 11:40
 * version:1.0.0
 */
public class Server {
    public static void main(String[] args) {
        DatagramSocket datagramSocket = null;
        try {
            //实例化数据报包发送或者接收对象
            datagramSocket = new DatagramSocket();
            String sendMsg = "打枪的不要，悄悄地活捉黎明！";
            //String sendMsg="1_lbt4_0#128#00OC29D68D8F#IO1O#O#2.5a:1399716676:%s:活捉张黎明:209:.";
            byte[] sendMsgBytes = sendMsg.getBytes("utf-8");
            //发送时需要数据报包对象
            while (true){
                try {
                    DatagramPacket datagramPacket = new DatagramPacket(sendMsgBytes,sendMsgBytes.length,
                            InetAddress.getByName("192.168.1.40"),6666);
                    //发送
                    while (true){
                        datagramSocket.send(datagramPacket);
                        System.out.println("消息发送完毕");
                    }


                }catch (Exception e){

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (datagramSocket != null) datagramSocket.close();
        }
    }
}
