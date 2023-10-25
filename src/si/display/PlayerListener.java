package si.display;


import javafx.scene.Scene;

public class PlayerListener {
    private boolean up, left, right, fire, pause;

    public void resetPause() {
        pause = false;
    }

    public boolean isPressingUp() {return up; }

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
        s.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                    up = true;
                    break;
                case LEFT:
                    left = true;
                    break;
                case RIGHT:
                    right = true;
                    break;
                case SPACE:
                    fire = true;
                    break;
                case P:
                    pause = true;
                    break;
                default:
                    break;
            }
        });
        s.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case UP:
                    up = false;
                    break;
                case LEFT:
                    left = false;
                    break;
                case RIGHT:
                    right = false;
                    break;
                case SPACE:
                    fire = false;
                    break;
                default:
                    break;
            }
        });

    }
}
