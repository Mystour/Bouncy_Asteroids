package si.display;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PlayerListener {
    private boolean left;
    private boolean right;
    private boolean fire;
    private boolean pause;

    public void resetPause() {
        pause = false;
    }

    public boolean isPressingLeft() {
        return left;
    }

    public boolean isPressingRight() {
        return right;
    }

    public boolean isPressingFire() {
        return fire;
    }

    public boolean hasPressedPause() {
        return pause;
    }

    public void setListeners(Scene s) {
        s.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.LEFT) {
                    left = true;
                } else if (e.getCode() == KeyCode.RIGHT) {
                    right = true;
                } else if (e.getCode() == KeyCode.SPACE) {
                    fire = true;
                } else if (e.getCode() == KeyCode.P) {
                    pause = true;
                }
            }
        });
        s.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.LEFT) {
                    left = false;
                } else if (e.getCode() == KeyCode.RIGHT) {
                    right = false;
                } else if (e.getCode() == KeyCode.SPACE) {
                    fire = false;
                }
            }
        });

    }
}
