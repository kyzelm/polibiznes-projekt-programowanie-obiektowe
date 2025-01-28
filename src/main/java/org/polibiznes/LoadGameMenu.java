package org.polibiznes;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Klasa LoadGameMenu dziedziczy po klasie Scennable i jest odpowiedzialna za wyświetlanie menu wczytywania gry.
 */
public class LoadGameMenu extends Scennable {
    String nickname = "";

    /**
     * Konstruktor klasy LoadGameMenu.
     *
     * @param windowWidth  szerokość okna
     * @param windowHeight wysokość okna
     */
    public LoadGameMenu(int windowWidth, int windowHeight) {
        super(windowWidth, windowHeight);
    }

    /**
     * Metoda aktualizująca parametry sceny.
     *
     * @param keyboardHandler obiekt obsługujący klawiaturę
     */
    public void update(KeyboardHandler keyboardHandler) {
        if (keyboardHandler.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            SceneManager.changeScene(Scenes.MAIN_MENU);
        }

        if (keyboardHandler.isKeyPressed(8) && !nickname.isEmpty()) {
            nickname = nickname.substring(0, nickname.length() - 1);
        }

        if (keyboardHandler.isKeyPressed(KeyEvent.VK_ENTER) && !nickname.isEmpty()) {
            PacketManager.send("JOIN\t" + nickname);
            SceneManager.changeScene(Scenes.GAME);
            nickname = "";
        }

        if (nickname.length() < 15) {
            for (int i = 65; i <= 90; i++) {
                if (keyboardHandler.isKeyPressed(i)) {
                    nickname += (char) i;
                }
            }
        }
    }

    /**
     * Metoda renderująca scenę.
     *
     * @param g2d obiekt klasy Graphics2D
     */
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
