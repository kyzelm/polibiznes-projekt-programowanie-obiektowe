package org.polibiznes;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Główna klasa aplikacji.
 */
public class App {
    public static void main(String[] args) throws IOException, FontFormatException {
        JFrame window = new JFrame("Polibiznes");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setUndecorated(true);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setVisible(true);
    }
}
