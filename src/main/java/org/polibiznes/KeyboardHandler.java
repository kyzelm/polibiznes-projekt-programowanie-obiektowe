package org.polibiznes;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class KeyboardHandler implements KeyListener {

    private final List<Integer> keys = new ArrayList<Integer>();

    @Override
    public void keyTyped(KeyEvent e) {
        // handle key typed event
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (!keys.contains(code)) {
            keys.add(code);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
    }

    public boolean isKeyPressed(int key) {
        return keys.contains(key);
    }

    public void resetKeys() {
        keys.clear();
    }

    public List<Integer> getKeys() {
        return keys;
    }
}
