package si.model;

import javafx.geometry.Rectangle2D;

public class Asteroids implements Hittable, Collisible {
    private boolean alive;
    private double x, y;
    private final double rotation;
    private double speed_x;
    private double speed_y;
    private final PlanetType type;

    public Asteroids(double x, double y, double rotation, PlanetType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        double speed = type.getSpeed();
        this.rotation = rotation;
        this.speed_x = speed * Math.cos(rotation);
        this.speed_y = speed * Math.sin(rotation);
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

    public boolean isHit(EnemyShip s) {
        boolean hit = getHitBox().intersects(s.getHitBox());
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
        boolean collision = getHitBox().intersects(p.getHitBox());
        if (collision && p.getInvincibilityCountdown() <= 0) {
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

    @Override
    public boolean isEnemy() {
        return false;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public double getSpeedX() { return speed_x; }
    public double getSpeedY() { return speed_y; }

    public void setSpeedX(double speed_x) { this.speed_x = speed_x; }
    public void setSpeedY(double speed_y) { this.speed_y = speed_y; }

    public double getRotation() { return rotation; }
}
