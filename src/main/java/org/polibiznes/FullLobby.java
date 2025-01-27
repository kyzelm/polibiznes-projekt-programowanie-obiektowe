package org.polibiznes;

import java.awt.*;
import java.awt.event.KeyEvent;

public class FullLobby extends Scennable {
    public FullLobby(int windowWidth, int windowHeight) {
        super(windowWidth, windowHeight);
    }

    void update(KeyboardHandler keyboardHandler) {
        if (keyboardHandler.isKeyPressed(KeyEvent.VK_ESCAPE)){
            SceneManager.changeScene(Scenes.MAIN_MENU);
        }
    }

    void render(Graphics2D g2d) {
        g2d.setColor(Color.LIGHT_GRAY);
        font = new Font("Arial", Font.PLAIN, 20);
        g2d.setFont(font);
        metrics = g2d.getFontMetrics(font);
        g2d.drawString("[ESC] Powrót", 10, 10 + metrics.getAscent());

        font = new Font("Arial", Font.PLAIN, 35);
        g2d.setFont(font);
        metrics = g2d.getFontMetrics(font);
        g2d.drawString("Lobby jest już pełne", windowWidth / 2 - metrics.stringWidth("Lobby jest już pełne") / 2, windowHeight / 2);
    }
}
