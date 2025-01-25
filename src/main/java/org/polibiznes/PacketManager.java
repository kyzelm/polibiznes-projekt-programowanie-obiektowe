package org.polibiznes;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class PacketManager {
    private static DatagramSocket socket;
    private static String address;
    private static int port;
    private static DatagramPacket packet;

    public static void connect(String address, int port) {
        PacketManager.address = address;
        PacketManager.port = port;
        try {
            socket = new DatagramSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void send(byte[] data) {
        try {
            packet = new DatagramPacket(data, data.length, InetAddress.getByName(address), port);
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] receive() {
        byte[] data = new byte[1024];
        packet = new DatagramPacket(data, data.length);
        try {
            socket.receive(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packet.getData();
    }
}
