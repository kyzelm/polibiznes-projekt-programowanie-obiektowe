package org.polibiznes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Klasa reprezentująca panel gry.
 */
public class GamePanel extends JPanel implements Runnable {

    final static int WIDTH = 1200;
    final static int HEIGHT = 800;

    final int FPS = 144;

    private Image bg = ImageIO.read(new File("src/main/resources/bg.jpg"));

    KeyboardHandler keyboardHandler = new KeyboardHandler();
    Thread gameThread;

    MainMenu mainMenu = new MainMenu(WIDTH, HEIGHT);
    Info info = new Info(WIDTH, HEIGHT);
    LoadGameMenu loadGameMenu = new LoadGameMenu(WIDTH, HEIGHT);
    FullLobby fullLobby = new FullLobby(WIDTH, HEIGHT);
    NickTaken nickTaken = new NickTaken(WIDTH, HEIGHT);
    Game game = new Game(WIDTH, HEIGHT);
    Win win = new Win(WIDTH, HEIGHT);
    Lose lose = new Lose(WIDTH, HEIGHT);

    /**
     * Konstruktor klasy GamePanel.
     * @throws IOException wyjątek w przypadku błędu odczytu pliku
     */
    public GamePanel() throws IOException {
        PacketManager.connect("localhost", 2137);

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboardHandler);
        this.setFocusable(true);

        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Metoda uruchamiająca główną pętlę gry.
     */
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
                try {
                    update();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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

    /**
     * Metoda aktualizująca parametry aktywnej sceny.
     * @throws InterruptedException wyjątek w przypadku błędu w obsłudze wątku
     */
    public void update() throws InterruptedException {
        switch (SceneManager.getCurrentScene()) {
            case MAIN_MENU:
                mainMenu.update(keyboardHandler);
                break;
            case INFO:
                info.update(keyboardHandler);
                break;
            case LOAD_GAME_MENU:
                loadGameMenu.update(keyboardHandler);
                break;
            case FULL_LOBBY:
                fullLobby.update(keyboardHandler);
                break;
            case NICK_TAKEN:
                nickTaken.update(keyboardHandler);
                break;
            case GAME:
                game.update(keyboardHandler);
                break;
            case WIN_GAME:
                win.update(keyboardHandler);
                break;
            case GAME_OVER:
                lose.update(keyboardHandler);
                break;
        }
    }

    /**
     * Metoda rysująca aktywną scenę.
     * @param g obiekt klasy Graphics
     */
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
            case LOAD_GAME_MENU:
                loadGameMenu.render(g2d);
                break;
            case FULL_LOBBY:
                fullLobby.render(g2d);
                break;
            case NICK_TAKEN:
                nickTaken.render(g2d);
                break;
            case GAME:
                game.render(g2d);
                break;
            case WIN_GAME:
                win.render(g2d);
                break;
            case GAME_OVER:
                lose.render(g2d);
                break;
        }
    }
}