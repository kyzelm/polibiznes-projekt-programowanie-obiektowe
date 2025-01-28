package org.polibiznes;

import java.awt.*;
import java.io.IOException;

/**
 * Klasa Scennable jest klasą abstrakcyjną dla scen w grze.
 */
public abstract class Scennable {
    int windowWidth;
    int windowHeight;

    Font font = null;
    FontMetrics metrics = null;

    /**
     * Konstruktor klasy Scennable.
     *
     * @param windowWidth  szerokość okna
     * @param windowHeight wysokość okna
     */
    public Scennable(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    /**
     * Metoda aktualizująca parametry sceny.
     *
     * @param keyboardHandler obiekt obsługujący klawiaturę
     * @throws InterruptedException wyjątek w przypadku przerwania
     */
    abstract void update(KeyboardHandler keyboardHandler) throws InterruptedException;

    /**
     * Metoda renderująca scenę.
     *
     * @param g2d obiekt klasy Graphics2D
     */
    abstract void render(Graphics2D g2d);
}
