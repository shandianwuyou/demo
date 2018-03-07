package com.my.demo;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by zhaopeng on 2018/2/24.
 */

public class DataGramServer {

    public static void main(String[] args) throws IOException {

        DatagramSocket server = new DatagramSocket(8888);

        byte[] container = new byte[1024];
        DatagramPacket packet = new DatagramPacket(container, container.length);
        server.receive(packet);
        byte[] data = packet.getData();
        System.out.println(convert(data));
        server.close();
    }

    public static double convert(byte[] arr) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(arr);
        DataInputStream dis = new DataInputStream(bis);

        double data = dis.readDouble();
        dis.close();
        bis.close();
        return data;
    }
}
