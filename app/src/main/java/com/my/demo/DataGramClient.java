package com.my.demo;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * Created by zhaopeng on 2018/2/24.
 */

public class DataGramClient {

    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket(6666);

//        String str = "你好啊";
        byte[] data = convert(1990.05);
        DatagramPacket packet = new DatagramPacket(data, data.length, new InetSocketAddress("localhost", 8888));
        client.send(packet);
        client.close();
    }

    public static byte[] convert(double num) throws IOException {
        byte[] data = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        dos.writeDouble(num);
        dos.flush();
        data = bos.toByteArray();
        dos.close();
        bos.close();

        return data;
    }
}
