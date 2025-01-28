package org.polibiznes;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa KeyboardHandler implementuje interfejs KeyListener i jest odpowiedzialna za obsługę klawiatury.
 */
public class KeyboardHandler implements KeyListener {

    private final List<Integer> keys = new ArrayList<Integer>();

    /**
     * Metoda obsługująca zdarzenie naciśnięcia klawisza.
     *
     * @param e klawisz naciśnięty przez użytkownika
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Metoda obsługująca zdarzenie naciśnięcia klawisza.
     *
     * @param e klawisz naciśnięty przez użytkownika
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (!keys.contains(code)) {
            keys.add(code);
        }
    }

    /**
     * Metoda obsługująca zdarzenie zwolnienia klawisza.
     *
     * @param e klawisz zwolniony przez użytkownika
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
    }

    /**
     * Metoda sprawdzająca, czy dany klawisz jest wciśnięty.
     *
     * @param key klawisz
     * @return true, jeśli klawisz jest wciśnięty, w przeciwnym wypadku false
     */
    public boolean isKeyPressed(int key) {
        return keys.contains(key);
    }

    /**
     * Metoda resetująca listę wciśniętych klawiszy.
     */
    public void resetKeys() {
        keys.clear();
    }

    /**
     * Metoda zwracająca listę wciśniętych klawiszy.
     *
     * @return lista wciśniętych klawiszy
     */
    public List<Integer> getKeys() {
        return keys;
    }
}
