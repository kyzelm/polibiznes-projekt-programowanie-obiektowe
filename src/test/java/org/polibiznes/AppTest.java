package org.polibiznes;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    void testGetColor() {
        Color expected = Color.RED;
        assertEquals(expected, Game.colorBasedOnPlayer("1"));
        assertNotEquals(expected, Game.colorBasedOnPlayer("2"));
    }

    @Test
    void testKeyboardClear() {
        KeyboardHandler keyboardHandler = new KeyboardHandler();
        keyboardHandler.resetKeys();
        assertTrue(keyboardHandler.getKeys().isEmpty());
        assertFalse(keyboardHandler.isKeyPressed(1));
    }
}