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
        this.rand = new Random((int)(x * 100 + y));  // seed the random number generator with the position of the ship
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
        if (rand.nextInt() % type.getFireRate() == 0) {
            double a = x + (double) (type.getWidth() * SHIP_SCALE) / 2;
            double b =  y + (SHIP_SCALE * height);
            bul = new Bullet(a, b, rotation, "enemy");
        }
        return bul;
    }

    public Rectangle2D getHitBox() {
        return new Rectangle2D(x, y, SHIP_SCALE * type.getWidth(), SHIP_SCALE * type.getHeight());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSpeedX() {
        return speed_x;
    }

    public double getSpeedY() {
        return speed_y;
    }

    public double getSpeed() {
        return Math.sqrt(Math.pow(speed_x, 2) + Math.pow(speed_y, 2));
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

    public void dodge(double x, double y) {
        double distance = Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
        if (distance < 100) {
            double rotation = Math.atan2(y - this.y, x - this.x);
            double speed = getSpeed();
            speed_x = -speed * Math.cos(rotation);
            speed_y = -speed * Math.sin(rotation);
        }
    }
}
