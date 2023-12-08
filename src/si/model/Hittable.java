package si.model;

import javafx.geometry.Rectangle2D;

public interface Hittable {
	boolean isAlive();
	int getPoints();
	boolean isPlayer();
	boolean isHit(Bullet b);
	Rectangle2D getHitBox();

	boolean isEnemy();

	// to find the position of the target
	double getX();
	double getY();
}

