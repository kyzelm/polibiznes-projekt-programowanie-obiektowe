package org.polibiznes;

import java.awt.*;
import java.net.DatagramPacket;
import java.util.Arrays;

enum PlayerState {
    LOBBY_WAITING, WAITING, ROLLING, BUYING
}

public class Game extends Scennable {
    String[][] gameData = {
            {
                    "GAMESTATE", // [0][0] - action
                    "", // [0][1] - addicional data
            },
            {   // player 1
                    "", // [1][0] - address
                    "", // [1][1] - port
                    "", // [1][2] - nickname
                    "", // [1][3] - money
                    "", // [1][4] - position
                    "", // [1][5] - jail time
                    "", // [1][6] - properties
            },
            {   // player 2
                    "", // [2][0] - address
                    "", // [2][1] - port
                    "", // [2][2] - nickname
                    "", // [2][3] - money
                    "", // [2][4] - position
                    "", // [2][5] - jail time
                    "", // [2][6] - properties
            },
            {   // player 3
                    "", // [3][0] - address
                    "", // [3][1] - port
                    "", // [3][2] - nickname
                    "", // [3][3] - money
                    "", // [3][4] - position
                    "", // [3][5] - jail time
                    "", // [3][6] - properties
            },
            {   // player 4
                    "", // [4][0] - address
                    "", // [4][1] - port
                    "", // [4][2] - nickname
                    "", // [4][3] - money
                    "", // [4][4] - position
                    "", // [4][5] - jail time
                    "", // [4][6] - properties
            },
    };

    PlayerState playerState = PlayerState.LOBBY_WAITING;

    public Game(int windowWidth, int windowHeight) {
        super(windowWidth, windowHeight);

    }

    void update(KeyboardHandler keyboardHandler) {
        if (playerState == PlayerState.LOBBY_WAITING) {
            gameData = PacketManager.receive();
            System.out.println("Received: " + Arrays.deepToString(gameData));
        }

        if (playerState == PlayerState.WAITING) {
        }

        if (playerState == PlayerState.ROLLING) {
        }

        if (playerState == PlayerState.BUYING) {
        }
    }

    void render(Graphics2D g2d) {

    }
}
