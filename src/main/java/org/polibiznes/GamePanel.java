package org.polibiznes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {

    final static int WIDTH = 1200;
    final static int HEIGHT = 800;

    final int FPS = 144;

    private Image bg = ImageIO.read(new File("src/main/resources/bg.jpg"));

    KeyboardHandler keyboardHandler = new KeyboardHandler();
    Thread gameThread;

    MainMenu mainMenu = new MainMenu(WIDTH, HEIGHT);
    Info info = new Info(WIDTH, HEIGHT);

    public GamePanel() throws IOException, FontFormatException {
        PacketManager.connect("localhost", 2137);

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboardHandler);
        this.setFocusable(true);

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastDrawTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastDrawTime) / drawInterval;
            timer += currentTime - lastDrawTime;
            lastDrawTime = currentTime;

            if (delta >= 1) {
                update();
                keyboardHandler.resetKeys();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }
        }

    }

    public void update() {
        switch (SceneManager.getCurrentScene()) {
            case MAIN_MENU:
                mainMenu.update(keyboardHandler);
                break;
            case INFO:
                info.update(keyboardHandler);
                break;
            case NEW_GAME_MENU:
                break;
            case LOAD_GAME_MENU:
                break;
            case GAME:
                break;
            case GAME_OVER:
                break;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(bg, 0, 0, getWidth(), getWidth(), null);

        switch (SceneManager.getCurrentScene()) {
            case MAIN_MENU:
                mainMenu.render(g2d);
                break;
            case INFO:
                info.render(g2d);
                break;
            case NEW_GAME_MENU:
                break;
            case LOAD_GAME_MENU:
                break;
            case GAME:
                break;
            case GAME_OVER:
                break;
        }
    }
}