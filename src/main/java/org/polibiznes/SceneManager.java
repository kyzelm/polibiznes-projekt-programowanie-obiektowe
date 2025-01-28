package org.polibiznes;

/**
 * Klasa SceneManager jest odpowiedzialna za zarządzanie scenami w grze.
 */
public class SceneManager {

    private static Scenes currentScene = Scenes.MAIN_MENU;

    /**
     * Metoda changeScene zmienia aktualną scenę.
     *
     * @param scene nowa scena
     */
    public static void changeScene(Scenes scene) {
        currentScene = scene;
    }

    /**
     * Metoda getCurrentScene zwraca aktualną scenę.
     *
     * @return aktualna scena
     */
    public static Scenes getCurrentScene() {
        return currentScene;
    }
}
