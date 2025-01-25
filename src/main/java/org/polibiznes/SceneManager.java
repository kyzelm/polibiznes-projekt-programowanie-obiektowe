package org.polibiznes;

public class SceneManager {

    private static Scenes currentScene = Scenes.MAIN_MENU;

    public static void changeScene(Scenes scene) {
        currentScene = scene;
    }

    public static Scenes getCurrentScene() {
        return currentScene;
    }
}
