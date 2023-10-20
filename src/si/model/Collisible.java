package si.model;

import javafx.geometry.Rectangle2D;

public interface Collisible {
    boolean isAlive();
    boolean isCollision(Player p);
    boolean isProps();
    Rectangle2D getHitBox();
}
