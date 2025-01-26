package org.polibiznes;

import java.io.*;
import java.net.Socket;

public class PacketManager {
    private static String address;
    private static int port;

    private static BufferedReader bufferReader;
    private static PrintWriter printWriter;

    public static void connect(String address, int port) throws IOException {
        PacketManager.address = address;
        PacketManager.port = port;

        try (Socket socket = new Socket(address, port)) {
            bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new IOException("Could not connect to the server");
        }
    }

    public static void send(String data) {
        printWriter.println(data);
    }

    public static String receive() {
        try {
            return bufferReader.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}
