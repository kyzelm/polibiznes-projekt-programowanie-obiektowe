package org.polibiznes;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new java.awt.Dimension(800, 600));
        this.setBackground(java.awt.Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        while (gameThread != null) {
            update();
            repaint();
        }

    }

    public void update() {
        // update game state
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(100, 100, 100, 100);
        g2d.dispose();
    }

}
