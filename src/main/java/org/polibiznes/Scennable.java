package org.polibiznes;

import java.awt.*;

public abstract class Scennable {
    int windowWidth;
    int windowHeight;

    Font font = null;
    FontMetrics metrics = null;

    public Scennable(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    abstract void update(KeyboardHandler keyboardHandler);
    abstract void render(Graphics2D g2d);
}
