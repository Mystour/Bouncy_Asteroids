package si.model;

import javafx.geometry.Rectangle2D;

import java.util.Random;

public class EnemyShip implements Hittable, Collisible {
    private boolean alive;
    private double x, y;
    private double speed_x, speed_y;
    private final AlienType type;
    private final Random rand;
    private final int height;
    public static final int SHIP_SCALE = 2;

    public EnemyShip(double x, double y, AlienType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.height = type.getHeight();
        this.rand = new Random((int)(x * 100 + y));
        double rotation = rand.nextDouble() * 2 * Math.PI;
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

    @Override
    public boolean isCollision(Player p) {
        boolean collision = getHitBox().intersects(p.getHitBox()) && p.getInvincibilityCountdown() <= 0;
        if (collision) {
            p.setAlive(false);
            p.setLives(p.getLives() - 1);
            p.setInvincibilityCountdown(120);  // 60 frames = 1 second
        }
        return collision;
    }

    @Override
    public boolean isProps() {
        return false;
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
            bul = new Bullet(a, b, rotation, "enemy");
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

    public boolean isEnemy() {
        return true;
    }
}
