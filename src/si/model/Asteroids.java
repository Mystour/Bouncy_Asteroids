package si.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Asteroids implements Hittable {
    private String name;
    private boolean alive;
    private int size;
    private double x, y;
    private PlanetType type;
    private Random rand;
    private int height;
    public static final int SHIP_SCALE = 2;

    public Asteroids(int x, int y, PlanetType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.size = type.getRadius();
        this.rand = new Random(x * 100 + y);
        this.alive = true;
    }

    public PlanetType getType() {
        return type;
    }

    public boolean isHit(Bullet b) {
        boolean hit = getHitBox().intersects(b.getHitBox());
        if (hit) {
            alive = false;
        }
        return hit;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getPoints() {
        return type.getScore();
    }

    public boolean isPlayer() {
        return false;
    }

    public void move(double cX, double cY) {
        x += cX;
        y += cY;
    }


    public Rectangle2D getHitBox() {
        return new Rectangle2D(x, y, type.getRadius(), type.getRadius());
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getSize() { return size; }

}
