package si.model;

import javafx.geometry.Rectangle2D;

public interface Hittable {
	boolean isAlive();
	int getPoints();
	boolean isPlayer();
	boolean isHit(Bullet b);
	Rectangle2D getHitBox();
}

