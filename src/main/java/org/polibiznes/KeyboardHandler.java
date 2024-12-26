package org.polibiznes;

import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener {

    public int left = 0, right = 0, up = 0, down = 0;

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
        // handle key typed event
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        int code = e.getKeyCode();

        if (code == java.awt.event.KeyEvent.VK_LEFT) {
            left = 1;
        }

        if (code == java.awt.event.KeyEvent.VK_RIGHT) {
            right = 1;
        }

        if (code == java.awt.event.KeyEvent.VK_UP) {
            up = 1;
        }

        if (code == java.awt.event.KeyEvent.VK_DOWN) {
            down = 1;
        }
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        int code = e.getKeyCode();

        if (code == java.awt.event.KeyEvent.VK_LEFT) {
            left = 0;
        }

        if (code == java.awt.event.KeyEvent.VK_RIGHT) {
            right = 0;
        }

        if (code == java.awt.event.KeyEvent.VK_UP) {
            up = 0;
        }

        if (code == java.awt.event.KeyEvent.VK_DOWN) {
            down = 0;
        }
    }
}
