package org.polibiznes;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LoadGameMenu extends Scennable {
    String nickname = "";

    public LoadGameMenu(int windowWidth, int windowHeight) {
        super(windowWidth, windowHeight);
    }

    public void update(KeyboardHandler keyboardHandler) {
        if (keyboardHandler.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            SceneManager.changeScene(Scenes.MAIN_MENU);
        }

        if (keyboardHandler.isKeyPressed(8) && !nickname.isEmpty()) {
            nickname = nickname.substring(0, nickname.length() - 1);
        }

        if (keyboardHandler.isKeyPressed(KeyEvent.VK_ENTER)) {
        }

        if (nickname.length() < 15) {
            for (int i = 65; i <= 90; i++) {
                if (keyboardHandler.isKeyPressed(i)) {
                    nickname += (char) i;
                }
            }
        }
    }

    public void render(Graphics2D g2d) {
        g2d.setColor(Color.LIGHT_GRAY);
        font = new Font("Arial", Font.PLAIN, 20);
        g2d.setFont(font);
        metrics = g2d.getFontMetrics(font);
        g2d.drawString("[ESC] Powrót", 10, 10 + metrics.getAscent());

        g2d.setColor(Color.LIGHT_GRAY);
        font = new Font("Arial", Font.PLAIN, 30);
        g2d.setFont(font);
        metrics = g2d.getFontMetrics(font);
        g2d.drawString("Wpisz swój nick:", windowWidth / 2 - metrics.stringWidth("Wpisz swój nick:") / 2, windowHeight / 2 - 50);

        g2d.setColor(Color.PINK);
        font = new Font("Arial", Font.BOLD, 40);
        g2d.setFont(font);
        metrics = g2d.getFontMetrics(font);
        g2d.drawString(nickname, windowWidth / 2 - metrics.stringWidth(nickname) / 2, windowHeight / 2 + 50);
    }
}
