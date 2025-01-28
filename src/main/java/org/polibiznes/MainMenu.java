package org.polibiznes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Klasa MainMenu dziedziczy po klasie Scennable i jest odpowiedzialna za wyświetlanie menu głównego gry.
 */
public class MainMenu extends Scennable {

    String[][] options = {{"Dołącz do gry", "Info", "Wyjdź"}, {"> Dołącz do gry <", "> Info <", "> Wyjdź <"}};
    int selectedOption = 0;

    /**
     * Konstruktor klasy MainMenu.
     *
     * @param windowWidth  szerokość okna
     * @param windowHeight wysokość okna
     */
    public MainMenu(int windowWidth, int windowHeight) {
        super(windowWidth, windowHeight);
    }

    /**
     * Metoda aktualizująca parametry sceny.
     *
     * @param keyboardHandler obiekt obsługujący klawiaturę
     */
    public void update(KeyboardHandler keyboardHandler) {
        if (keyboardHandler.isKeyPressed(KeyEvent.VK_UP)) {
            selectedOption = (selectedOption + 2) % 3;
        }

        if (keyboardHandler.isKeyPressed(KeyEvent.VK_DOWN)) {
            selectedOption = (selectedOption + 1) % 3;
        }

        if (keyboardHandler.isKeyPressed(KeyEvent.VK_ENTER)) {
            switch (selectedOption) {
                case 0:
                    SceneManager.changeScene(Scenes.LOAD_GAME_MENU);
                    break;
                case 1:
                    SceneManager.changeScene(Scenes.INFO);
                    break;
                case 2:
                    System.exit(0);
                    break;
            }
        }
    }

    /**
     * Metoda renderująca scenę.
     *
     * @param g2d obiekt klasy Graphics2D
     */
    public void render(Graphics2D g2d) {
        g2d.setColor(Color.PINK);
        font = new Font("Arial", Font.BOLD, 65);
        g2d.setFont(font);
        metrics = g2d.getFontMetrics(font);
        g2d.drawString("Polibiznes", windowWidth / 2 - metrics.stringWidth("Polibiznes") / 2, windowHeight / 4);

        g2d.setColor(Color.LIGHT_GRAY);
        font = new Font("Arial", Font.BOLD, 35);
        g2d.setFont(font);
        metrics = g2d.getFontMetrics(font);
        for (int i = 0; i < options[0].length; i++) {
            g2d.drawString(options[i == selectedOption ? 1 : 0][i], windowWidth / 2 - metrics.stringWidth(options[i == selectedOption ? 1 : 0][i]) / 2, windowHeight / 2 + (i + 1) * 75);
        }
    }
}
