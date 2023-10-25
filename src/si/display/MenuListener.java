package si.display;

import javafx.scene.Scene;
import ucd.comp2011j.engine.MenuCommands;

public class MenuListener implements MenuCommands {
    private boolean about;
    private boolean exit;
    private boolean high;
    private boolean menu;
    private boolean newGame;

    public void setListeners(Scene s) {
        s.setOnKeyTyped(e -> {
            if ("A".equalsIgnoreCase(e.getCharacter())) {
                about = true;
            } else if ("X".equalsIgnoreCase(e.getCharacter())) {
                exit = true;
            } else if ("H".equalsIgnoreCase(e.getCharacter())) {
                high = true;
            } else if ("M".equalsIgnoreCase(e.getCharacter())) {
                menu = true;
            } else if ("N".equalsIgnoreCase(e.getCharacter())) {
                newGame = true;
            }
        });
    }

    @Override
    public boolean hasPressedNewGame() {
        return newGame;
    }

    @Override
    public boolean hasPressedAboutScreen() {
        return about;
    }

    @Override
    public boolean hasPressedHighScoreScreen() {
        return high;
    }

    @Override
    public boolean hasPressedMenu() {
        return menu;
    }

    @Override
    public boolean hasPressedExit() {
        return exit;
    }

    @Override
    public void resetKeyPresses() {
        menu = false;
        about = false;
        newGame = false;
        high = false;
        exit = false;
    }
}
