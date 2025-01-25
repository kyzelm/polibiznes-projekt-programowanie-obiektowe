package org.polibiznes;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Info extends Scennable {
    String[] text = {
            "Polibiznes to gra planszowa dla 2-4 graczy.",
            "Celem gry jest zdobycie jak największego majątku.",
            "Gracze poruszają się po planszy, kupując pola",
            "i pobierając opłaty od innych graczy.",
            "W grze występują różne rodzaje pól, które",
            "mogą zwiększyć majątek gracza lub go zubożyć.",
            "Gracz, który jako pierwszy zdobędzie 20 000$",
            "wygrywa grę.",
            "",
            "Sterowanie:",
            "Strzałki - nawigacja",
            "Enter - potwierdzenie",
            "Escape - powrót"
    };

    public Info(int windowWidth, int windowHeight) {
        super(windowWidth, windowHeight);
    }

    public void update(KeyboardHandler keyboardHandler) {
        if (keyboardHandler.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            SceneManager.changeScene(Scenes.MAIN_MENU);
        }
    }

    public void render(Graphics2D g2d) {
        g2d.setColor(Color.LIGHT_GRAY);
        font = new Font("Arial", Font.PLAIN, 20);
        g2d.setFont(font);
        metrics = g2d.getFontMetrics(font);
        g2d.drawString("[ESC] Powrót", 10, 10 + metrics.getAscent());

        font = new Font("Arial", Font.PLAIN, 25);
        g2d.setFont(font);
        metrics = g2d.getFontMetrics(font);
        for (int i = 0; i < text.length; i++) {
            g2d.drawString(text[i], windowWidth / 2 - metrics.stringWidth(text[i]) / 2, windowHeight / 4 + i * 30);
        }
    }
}
