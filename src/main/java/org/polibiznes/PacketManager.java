package org.polibiznes;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

/**
 * Klasa PacketManager jest odpowiedzialna za zarządzanie pakietami sieciowymi.
 */
public class PacketManager {
    private static String address;
    private static int port;

    static DatagramSocket socket;

    /**
     * Metoda connect łączy z serwerem.
     *
     * @param address adres serwera
     * @param port    port serwera
     * @throws IOException wyjątek w przypadku niepowodzenia połączenia
     */
    public static void connect(String address, int port) throws IOException {
        PacketManager.address = address;
        PacketManager.port = port;

        try {
            socket = new DatagramSocket();
        } catch (IOException e) {
            throw new IOException("Could not connect to the server");
        }
    }

    /**
     * Metoda send wysyła dane do serwera.
     *
     * @param data dane do wysłania
     */
    public static void send(String data) {
        try {
            byte[] buffer = data.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(address), port);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda receive odbiera dane od serwera.
     *
     * @return dane odebrane od serwera
     */
    public static String[][] receive() {
        try {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            String[] lines = message.split("\n");
            String[][] data = new String[lines.length][];
            for (int i = 0; i < lines.length; i++) {
                data[i] = lines[i].split("\t");
            }
            System.out.println(Arrays.deepToString(data));
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
