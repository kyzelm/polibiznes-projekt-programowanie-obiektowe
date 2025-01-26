package org.polibiznes;

import java.awt.*;

enum PlayerState {
    WAITING, ROLLING, BUYING
}

public class Game extends Scennable {
    PlayerState playerState = PlayerState.WAITING;

    public Game(int windowWidth, int windowHeight) {
        super(windowWidth, windowHeight);

    }

    void update(KeyboardHandler keyboardHandler) {
        if (playerState == PlayerState.WAITING) {
            String message = PacketManager.receive();
            if (message != null) {
                System.out.println("Received: " + message);
            }

            String[] messageParts = message.split("\n");
            switch (messageParts[0]) {
                case "GAMESTATE":
                    break;
                case "ROLL":
                    break;
                case "BUY":
                    break;
            }
        }

        if (playerState == PlayerState.ROLLING) {
        }

        if (playerState == PlayerState.BUYING) {
        }
    }

    void render(Graphics2D g2d) {

    }
}
