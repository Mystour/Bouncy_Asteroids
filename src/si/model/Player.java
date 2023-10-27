package si.model;


import javafx.geometry.Rectangle2D;

import java.util.List;
import java.util.ArrayList;

public class Player implements Hittable {
    private double x;
    private double y;
    private double speed_x, speed_y;
    private Rectangle2D hitBox;
    private double rotation;  // Initialized to 0 by default
    private int weaponCountdown;
    private int invincibilityCountdown = 0;
    private int tripleCountdown = 60 * 10;
    private boolean alive = true;
    private int lives = 10;
    public static final int SHIP_SCALE = 4;
    private static final int WIDTH = SHIP_SCALE * 8;
    private boolean tripleFire;

    public Player() {
        // the player starts at the bottom of the screen, centered horizontally
        x = 400 - WIDTH;
        y = 450;
        tripleFire = false;

        hitBox = new Rectangle2D(x, y, 8 * SHIP_SCALE, 5 * SHIP_SCALE);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isHit(Bullet b) {
        boolean hit = hitBox.intersects(b.getHitBox());
        if (hit) {
            alive = false;
        }
        return hit;
    }

    public void tick() {
        if (invincibilityCountdown > 0) {
            invincibilityCountdown--;
        }
        if (weaponCountdown > 0) {
            weaponCountdown--;
        } else {
            weaponCountdown = 15;
        }
        if (tripleFire && tripleCountdown > 0) {
            tripleCountdown--;
        }
        else if (tripleCountdown <= 0){
            tripleFire = false;
            resetTripleCountdown();
        }
    }


    public void resetDestroyed() {
        alive = true;
        x = 400 - WIDTH;
        y = 450;
        hitBox = new Rectangle2D(x, y, 8 * SHIP_SCALE, 5 * SHIP_SCALE);
        speed_x = speed_y = 0;
    }

    public int getPoints() {
        return -100;
    }

    public boolean isPlayer() {
        return true;
    }

    @Override
    public Rectangle2D getHitBox() {
        return hitBox;
    }

    @Override
    public boolean isEnemy() {
        return false;
    }

    public List<Bullet> fire() {
        List<Bullet> b = new ArrayList<Bullet>();
        if (weaponCountdown == 0) {
            b.add(new Bullet(this.getHeadX(), this.getHeadY(), rotation + Math.PI * 3/2, "player"));
            if (tripleFire && tripleCountdown > 0){
                b.add(new Bullet(x - Math.cos(rotation) * 4 * SHIP_SCALE, y - Math.sin(rotation) * 30, rotation + Math.PI * 3/2, "player"));
                b.add(new Bullet(x + Math.cos(rotation) * 4 * SHIP_SCALE, y + Math.sin(rotation) * 30, rotation + Math.PI * 3/2, "player"));
            }
            Sound sound = Sound.getInstance();
            sound.playBulletSound();
        }
        return b;
    }

    public void move() {
        double dx = speed_x;
        double dy = speed_y;

        this.x += dx;
        this.y += dy;

        if (this.x > BouncyAsteroidsGame.SCREEN_WIDTH) {
            this.x -= BouncyAsteroidsGame.SCREEN_WIDTH;
        } else if (this.x < 0) {
            this.x += BouncyAsteroidsGame.SCREEN_WIDTH;
        }

        if (this.y > BouncyAsteroidsGame.SCREEN_HEIGHT) {
            this.y -= BouncyAsteroidsGame.SCREEN_HEIGHT;
        } else if (this.y < 0) {
            this.y += BouncyAsteroidsGame.SCREEN_HEIGHT;
        }

        hitBox = new Rectangle2D(this.x, this.y, 8 * SHIP_SCALE, 5 * SHIP_SCALE);
    }

    public void rotate(double degrees) {
        rotation += degrees;
        rotation = (rotation % 360 + 360) % 360; // Ensure rotation stays within [0, 360)
    }

    public double getRotation() {
        return rotation;
    }

    public void accelerate() {
        float acceleration = 0.2f;
        double radians = rotation + 3*Math.PI/2;
        speed_x += (float) (acceleration * Math.cos(radians));
        speed_y += (float) (acceleration * Math.sin(radians));
        double maxSpeed = 8;
        if (Math.pow(speed_x, 2) + Math.pow(speed_y, 2) > Math.pow(maxSpeed, 2)) {
            speed_x = (float) (maxSpeed * Math.cos(radians));
            speed_y = (float) (maxSpeed * Math.sin(radians));
        }
    }

    public int getInvincibilityCountdown() {
        return invincibilityCountdown;
    }

    public void setInvincibilityCountdown(int invincibilityCountdown) {
        this.invincibilityCountdown = invincibilityCountdown;
    }

    public int getTripleCountdown() {
        return tripleCountdown;
    }

    private void resetTripleCountdown() {
        this.tripleCountdown = 60 * 10;
    }

    public void setTripleFire() {
        this.tripleFire = true;
        resetTripleCountdown();
    }

    public boolean getTripleFire() {
        return tripleFire;
    }


    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public double getHeadX() {
        return x - 6 * SHIP_SCALE * Math.cos(rotation + Math.PI * 1/2);
    }

    public double getHeadY() {
        return y - 6 * SHIP_SCALE * Math.sin(rotation + Math.PI * 1/2);
    }
}
