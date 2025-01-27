package org.polibiznes;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

enum PlayerState {
    WAITING, ROLLING, BUYING, BUILDING
}

public class Game extends Scennable {
    String[][] gameData = {{"wait"}};

    PlayerState playerState = PlayerState.WAITING;
    int roll1 = 0;
    int roll2 = 0;

    public Game(int windowWidth, int windowHeight) {
        super(windowWidth, windowHeight);

    }

    void update(KeyboardHandler keyboardHandler) throws InterruptedException {
        if (playerState == PlayerState.WAITING) {
            gameData = PacketManager.receive();
            System.out.println(gameData[0][0]);
            if (gameData[0][0].equals("FULL")) {
                SceneManager.changeScene(Scenes.FULL_LOBBY);
            }
            if (gameData[0][0].equals("NICK")) {
                SceneManager.changeScene(Scenes.NICK_TAKEN);
            }
            if (gameData[0][0].equals("ROLL")) {
                System.out.println("ROLL");
                playerState = PlayerState.ROLLING;
            }
            if (gameData[0][0].equals("BUY")) {
                System.out.println("BUY");
                playerState = PlayerState.BUYING;
            }
            if (gameData[0][0].equals("BUILD")) {
                System.out.println("BUILD");
                playerState = PlayerState.BUILDING;
            }
            if (gameData[0][0].equals("WIN")) {
                System.out.println("WIN");
                SceneManager.changeScene(Scenes.WIN_GAME);
            }
            if (gameData[0][0].equals("LOSE")) {
                System.out.println("LOSE");
                SceneManager.changeScene(Scenes.GAME_OVER);
            }
        }

        if (playerState == PlayerState.ROLLING) {
            if (keyboardHandler.isKeyPressed(KeyEvent.VK_R)) {
                roll1 = (int) (Math.random() * 6) + 1;
                roll2 = (int) (Math.random() * 6) + 1;
                PacketManager.send("ROLL\t" + (roll1 + roll2));
                playerState = PlayerState.WAITING;
            }
        }

        if (playerState == PlayerState.BUYING) {
            if (keyboardHandler.isKeyPressed(KeyEvent.VK_Y)) {
                PacketManager.send("BUY\tYES");
                playerState = PlayerState.WAITING;
            }
            if (keyboardHandler.isKeyPressed(KeyEvent.VK_N)) {
                PacketManager.send("BUY\tNO");
                playerState = PlayerState.WAITING;
            }
        }
    }

    void render(Graphics2D g2d) {
        g2d.setColor(Color.PINK);
        font = new Font("Arial", Font.BOLD, 30);
        metrics = g2d.getFontMetrics(font);
        g2d.setFont(font);
        g2d.drawString("Polibiznes", (windowWidth - metrics.stringWidth("Polibiznes")) / 2, 50);

        font = new Font("Arial", Font.BOLD, 20);
        metrics = g2d.getFontMetrics(font);
        g2d.setFont(font);

        if (gameData[0][0].equals("wait")) {
            return;
        }

        if (!(gameData[1].length == 0)) {
            g2d.setColor(Color.RED);
            g2d.drawString(gameData[1][2].isEmpty() ? " " : gameData[1][2], 20, 20 + metrics.getAscent());

            g2d.setColor(Color.LIGHT_GRAY);
            g2d.drawString("Pieniądze: " + (gameData[1][3].isEmpty() ? " " : gameData[1][3]), 20, 40 + metrics.getAscent());
        }

        if (!(gameData[2].length == 0)) {
            g2d.setColor(Color.BLUE);
            g2d.drawString(gameData[2][2].isEmpty() ? " " : gameData[2][2], windowWidth - metrics.stringWidth(gameData[2][2].isEmpty() ? " " : gameData[2][2]) - 20, 20 + metrics.getAscent());

            g2d.setColor(Color.LIGHT_GRAY);
            g2d.drawString("Pieniądze: " + (gameData[2][3].isEmpty() ? " " : gameData[2][3]), windowWidth - metrics.stringWidth("Pieniądze: " + (gameData[2][3].isEmpty() ? " " : gameData[2][3])) - 20, 40 + metrics.getAscent());
        }

        if (!(gameData[3].length == 0)) {
            g2d.setColor(Color.GREEN);
            g2d.drawString(gameData[3][2].isEmpty() ? " " : gameData[3][2], 20, windowHeight - 20);

            g2d.setColor(Color.LIGHT_GRAY);
            g2d.drawString("Pieniądze: " + (gameData[3][3].isEmpty() ? " " : gameData[3][3]), 20, windowHeight - 40);
        }

        if (!(gameData[4].length == 0)) {
            g2d.setColor(Color.YELLOW);
            g2d.drawString(gameData[4][2].isEmpty() ? " " : gameData[4][2], windowWidth - metrics.stringWidth(gameData[4][2].isEmpty() ? " " : gameData[4][2]) - 20, windowHeight - 20);

            g2d.setColor(Color.LIGHT_GRAY);
            g2d.drawString("Pieniądze: " + (gameData[4][3].isEmpty() ? " " : gameData[4][3]), windowWidth - metrics.stringWidth("Pieniądze: " + (gameData[4][3].isEmpty() ? " " : gameData[4][3])) - 20, windowHeight - 40);
        }

        if (gameData[1].length == 0 || gameData[2].length == 0 || gameData[3].length == 0 || gameData[4].length == 0) {
            return;
        }

        for (int i = 0; i < 7; i++) {
            g2d.setColor(colorBasedOnPlayer(gameData[i + 5][2]));
            g2d.fillRect((windowWidth / 2) + (i - 4) * 80, (windowHeight / 2) - 3 * 80, 80, 80);
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillRect(((windowWidth / 2) + (i - 4) * 80) + 3, ((windowHeight / 2) - 3 * 80) + 3, 74, 74);

            if (gameData[1][4].equals("" + i)) {
                g2d.setColor(Color.RED);
                font = new Font("Arial", Font.BOLD, 20);
                g2d.setFont(font);
                g2d.drawString("X", (windowWidth / 2) + (i - 4) * 80 + 20, (windowHeight / 2) - 3 * 80 + 30);
            }

            if (gameData[2][4].equals("" + i)) {
                g2d.setColor(Color.BLUE);
                font = new Font("Arial", Font.BOLD, 20);
                g2d.setFont(font);
                g2d.drawString("X", (windowWidth / 2) + (i - 4) * 80 + 20, (windowHeight / 2) - 3 * 80 + 60);
            }

            if (gameData[3][4].equals("" + i)) {
                g2d.setColor(Color.GREEN);
                font = new Font("Arial", Font.BOLD, 20);
                g2d.setFont(font);
                g2d.drawString("X", (windowWidth / 2) + (i - 4) * 80 + 50, (windowHeight / 2) - 3 * 80 + 30);
            }

            if (gameData[4][4].equals("" + i)) {
                g2d.setColor(Color.YELLOW);
                font = new Font("Arial", Font.BOLD, 20);
                g2d.setFont(font);
                g2d.drawString("X", (windowWidth / 2) + (i - 4) * 80 + 50, (windowHeight / 2) - 3 * 80 + 60);
            }
        }

        for (int i = 7; i < 13; i++) {
            g2d.setColor(colorBasedOnPlayer(gameData[i + 5][2]));
            g2d.fillRect((windowWidth / 2) + 3 * 80, (windowHeight / 2) + (i - 10) * 80, 80, 80);
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillRect(((windowWidth / 2) + 3 * 80) + 3, ((windowHeight / 2) + (i - 10) * 80) + 3, 74, 74);

            if (gameData[1][4].equals("" + i)) {
                g2d.setColor(Color.RED);
                font = new Font("Arial", Font.BOLD, 20);
                g2d.setFont(font);
                g2d.drawString("X", (windowWidth / 2) + 3 * 80 + 20, (windowHeight / 2) + (i - 10) * 80 + 30);
            }

            if (gameData[2][4].equals("" + i)) {
                g2d.setColor(Color.BLUE);
                font = new Font("Arial", Font.BOLD, 20);
                g2d.setFont(font);
                g2d.drawString("X", (windowWidth / 2) + 3 * 80 + 20, (windowHeight / 2) + (i - 10) * 80 + 60);
            }

            if (gameData[3][4].equals("" + i)) {
                g2d.setColor(Color.GREEN);
                font = new Font("Arial", Font.BOLD, 20);
                g2d.setFont(font);
                g2d.drawString("X", (windowWidth / 2) + 3 * 80 + 50, (windowHeight / 2) + (i - 10) * 80 + 30);
            }

            if (gameData[4][4].equals("" + i)) {
                g2d.setColor(Color.YELLOW);
                font = new Font("Arial", Font.BOLD, 20);
                g2d.setFont(font);
                g2d.drawString("X", (windowWidth / 2) + 3 * 80 + 50, (windowHeight / 2) + (i - 10) * 80 + 60);
            }
        }

        for (int i = 13; i < 20; i++) {
            g2d.setColor(colorBasedOnPlayer(gameData[i + 5][2]));
            g2d.fillRect((windowWidth / 2) + (16 - i) * 80, (windowHeight / 2) + 3 * 80, 80, 80);
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillRect(((windowWidth / 2) + (16 - i) * 80) + 3, ((windowHeight / 2) + 3 * 80) + 3, 74, 74);

            if (gameData[1][4].equals("" + i)) {
                g2d.setColor(Color.RED);
                font = new Font("Arial", Font.BOLD, 20);
                g2d.setFont(font);
                g2d.drawString("X", (windowWidth / 2) + (16 - i) * 80 + 20, (windowHeight / 2) + 3 * 80 + 30);
            }

            if (gameData[2][4].equals("" + i)) {
                g2d.setColor(Color.BLUE);
                font = new Font("Arial", Font.BOLD, 20);
                g2d.setFont(font);
                g2d.drawString("X", (windowWidth / 2) + (16 - i) * 80 + 20, (windowHeight / 2) + 3 * 80 + 60);
            }

            if (gameData[3][4].equals("" + i)) {
                g2d.setColor(Color.GREEN);
                font = new Font("Arial", Font.BOLD, 20);
                g2d.setFont(font);
                g2d.drawString("X", (windowWidth / 2) + (16 - i) * 80 + 50, (windowHeight / 2) + 3 * 80 + 30);
            }

            if (gameData[4][4].equals("" + i)) {
                g2d.setColor(Color.YELLOW);
                font = new Font("Arial", Font.BOLD, 20);
                g2d.setFont(font);
                g2d.drawString("X", (windowWidth / 2) + (16 - i) * 80 + 50, (windowHeight / 2) + 3 * 80 + 60);
            }
        }

        for (int i = 20; i < 26; i++) {
            g2d.setColor(colorBasedOnPlayer(gameData[i + 5][2]));
            g2d.fillRect((windowWidth / 2) - 4 * 80, (windowHeight / 2) + (23 - i) * 80, 80, 80);
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillRect(((windowWidth / 2) - 4 * 80) + 3, ((windowHeight / 2) + (23 - i) * 80) + 3, 74, 74);

            if (gameData[1][4].equals("" + i)) {
                g2d.setColor(Color.RED);
                font = new Font("Arial", Font.BOLD, 20);
                g2d.setFont(font);
                g2d.drawString("X", (windowWidth / 2) - 4 * 80 + 20, (windowHeight / 2) + (23 - i) * 80 + 30);
            }

            if (gameData[2][4].equals("" + i)) {
                g2d.setColor(Color.BLUE);
                font = new Font("Arial", Font.BOLD, 20);
                g2d.setFont(font);
                g2d.drawString("X", (windowWidth / 2) - 4 * 80 + 20, (windowHeight / 2) + (23 - i) * 80 + 60);
            }

            if (gameData[3][4].equals("" + i)) {
                g2d.setColor(Color.GREEN);
                font = new Font("Arial", Font.BOLD, 20);
                g2d.setFont(font);
                g2d.drawString("X", (windowWidth / 2) - 4 * 80 + 50, (windowHeight / 2) + (23 - i) * 80 + 30);
            }

            if (gameData[4][4].equals("" + i)) {
                g2d.setColor(Color.YELLOW);
                font = new Font("Arial", Font.BOLD, 20);
                g2d.setFont(font);
                g2d.drawString("X", (windowWidth / 2) - 4 * 80 + 50, (windowHeight / 2) + (23 - i) * 80 + 60);
            }
        }

        if (playerState == PlayerState.ROLLING) {
            g2d.setColor(Color.LIGHT_GRAY);
            font = new Font("Arial", Font.BOLD, 30);
            g2d.setFont(font);
            metrics = g2d.getFontMetrics(font);
            g2d.drawString("Rzut kostkami [R]", (windowWidth - metrics.stringWidth("Rzut kostkami [R]")) / 2, (windowHeight - metrics.getHeight()) / 2);
        }

        if (playerState == PlayerState.BUYING) {
            g2d.setColor(Color.LIGHT_GRAY);
            font = new Font("Arial", Font.BOLD, 30);
            g2d.setFont(font);
            metrics = g2d.getFontMetrics(font);
            g2d.drawString("Kupić pole? [Y/N]", (windowWidth - metrics.stringWidth("Kupić pole? [Y/N]")) / 2, (windowHeight - metrics.getHeight()) / 2);

            font = new Font("Arial", Font.BOLD, 20);
            g2d.setFont(font);
            metrics = g2d.getFontMetrics(font);
            g2d.drawString(gameData[Integer.parseInt(gameData[0][1]) + 5][0], (windowWidth - metrics.stringWidth(gameData[Integer.parseInt(gameData[0][1]) + 5][0])) / 2, (windowHeight + metrics.getHeight()) / 2);
            g2d.drawString("Cena: " + gameData[Integer.parseInt(gameData[0][1]) + 5][1], (windowWidth - metrics.stringWidth("Cena: " + gameData[Integer.parseInt(gameData[0][1]) + 5][1])) / 2, (windowHeight + metrics.getHeight()) / 2 + 30);
        }
    }

    Color colorBasedOnPlayer(String player) {
        switch (player) {
            case "1":
                return Color.RED;
            case "2":
                return Color.BLUE;
            case "3":
                return Color.GREEN;
            case "4":
                return Color.YELLOW;
            default:
                return Color.GRAY;
        }
    }
}
