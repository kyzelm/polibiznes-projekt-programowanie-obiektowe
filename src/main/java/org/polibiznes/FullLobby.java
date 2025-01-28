package org.polibiznes;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Klasa reprezentująca scenę, która wyświetla się, gdy lobby jest już pełne.
 */
public class FullLobby extends Scennable {

    /**
     * Konstruktor klasy FullLobby.
     * @param windowWidth szerokość okna
     * @param windowHeight wysokość okna
     */
    public FullLobby(int windowWidth, int windowHeight) {
        super(windowWidth, windowHeight);
    }

    /**
     * Metoda aktualizująca parametry sceny.
     * @param keyboardHandler obiekt obsługujący klawiaturę
     */
    void update(KeyboardHandler keyboardHandler) {
        if (keyboardHandler.isKeyPressed(KeyEvent.VK_ESCAPE)){
            SceneManager.changeScene(Scenes.MAIN_MENU);
        }
    }

    /**
     * Metoda renderująca scenę.
     * @param g2d obiekt klasy Graphics2D
     */
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
