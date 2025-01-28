package org.polibiznes;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Klasa Lose dziedziczy po klasie Scennable i jest odpowiedzialna za wyświetlanie informacji o przegranej.
 */
public class Lose extends Scennable {
    /**
     * Konstruktor klasy Lose.
     *
     * @param windowWidth  szerokość okna
     * @param windowHeight wysokość okna
     */
    public Lose(int windowWidth, int windowHeight) {
        super(windowWidth, windowHeight);
    }

    /**
     * Metoda aktualizująca parametry sceny.
     *
     * @param keyboardHandler obiekt obsługujący klawiaturę
     */
    void update(KeyboardHandler keyboardHandler) {
        if (keyboardHandler.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            SceneManager.changeScene(Scenes.MAIN_MENU);
        }
    }

    /**
     * Metoda renderująca scenę.
     *
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
        g2d.drawString("Niestety nie tym razem :(", windowWidth / 2 - metrics.stringWidth("Niestety nie tym razem :(") / 2, windowHeight / 2);
    }
}
