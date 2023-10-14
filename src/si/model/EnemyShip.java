package si.model;

import javafx.geometry.Rectangle2D;

import java.util.Random;

public class EnemyShip implements Hittable {
    private String name;
    private boolean alive;
    private double x, y;
    private AlienType type;
    private Random rand;
    private int height;
    public static final int SHIP_SCALE = 2;

    public EnemyShip(int x, int y, AlienType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.height = type.getHeight();
        this.rand = new Random(x * 100 + y);
        this.alive = true;
    }

    public AlienType getType() {
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

    public Bullet fire() {
        Bullet bul = null;
        if (rand.nextInt() % 200 == 0) {
            int a = ((int) x + (type.getWidth() * SHIP_SCALE) / 2);
            int b = (int) y + (SHIP_SCALE * height);
            bul = new Bullet(a, b, false, name);
        }
        return bul;
    }

    public Rectangle2D getHitBox() {
        return new Rectangle2D(x, y, SHIP_SCALE * type.getWidth(), SHIP_SCALE * type.getHeight());
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

}
