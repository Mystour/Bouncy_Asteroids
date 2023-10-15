package si.model;

import javafx.geometry.Rectangle2D;

import java.util.Random;

public class EnemyShip implements Hittable {
    private String name;
    private boolean alive;
    private double x, y;
    private double speed_x, speed_y;
    private double rotation;
    private AlienType type;
    private Random rand;
    private int height;
    public static final int SHIP_SCALE = 2;

    public EnemyShip(double x, double y, AlienType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.height = type.getHeight();
        this.rand = new Random((int)(x * 100 + y));
        this.rotation = rand.nextDouble() * 2 * Math.PI;
        this.speed_x = type.getSpeed() * Math.cos(rotation);
        this.speed_y = type.getSpeed() * Math.sin(rotation);
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

    public void setAlive(boolean alive) {
        this.alive = alive;
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

    public Bullet fire(double rotation) {
        Bullet bul = null;
        if (rand.nextInt() % 200 == 0) {
            double a = x + (double) (type.getWidth() * SHIP_SCALE) / 2;
            double b =  y + (SHIP_SCALE * height);
            bul = new Bullet(a, b, rotation);
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

    public double getSpeedX() {
        return speed_x;
    }

    public double getSpeedY() {
        return speed_y;
    }

    public void setSpeedX(double speed_x) {
        this.speed_x = speed_x;
    }

    public void setSpeedY(double speed_y) {
        this.speed_y = speed_y;
    }
}
