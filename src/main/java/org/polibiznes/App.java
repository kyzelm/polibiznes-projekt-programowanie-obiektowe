package org.polibiznes;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JFrame window = new JFrame("Polibiznes");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocation(100, 100);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setVisible(true);
    }
}
