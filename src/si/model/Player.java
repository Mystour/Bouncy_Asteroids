package si.model;


import javafx.geometry.Rectangle2D;

public class Player implements Hittable {
    private double x;
    private double y;
    private double speed_x, speed_y;
    private Rectangle2D hitBox;
    private double rotation;  // Initialized to 0 by default
    private int weaponCountdown;
    private int invincibilityCountdown = 0;
    private boolean alive = true;
    public static final int SHIP_SCALE = 4;
    private static final int WIDTH = SHIP_SCALE * 8;

    public Player() {
        // the player starts at the bottom of the screen, centered horizontally
        x = 400 - WIDTH;
        y = 450;

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

    public boolean isHit(Asteroids a) {
        boolean hit = invincibilityCountdown <= 0 && hitBox.intersects(a.getHitBox());
        if (hit) {
            alive = false;
            invincibilityCountdown = 120;  // 60 frames = 1 second
        }
        return hit;
    }

    public boolean isHit(EnemyShip s) {
        boolean hit = invincibilityCountdown <= 0 && hitBox.intersects(s.getHitBox());
        if (hit) {
            alive = false;
            invincibilityCountdown = 120;  // 60 frames = 1 second
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
    }

    public boolean isAlive() {
        return alive;
    }

    public void resetDestroyed() {
        alive = true;
        x = 400 - WIDTH;
        y = 450;
        hitBox = new Rectangle2D(x, y, 8 * SHIP_SCALE, 5 * SHIP_SCALE);
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

    public Bullet fire() {
        Bullet b = null;
        if (weaponCountdown == 0) {
            b = new Bullet(x, y, rotation + 3*Math.PI/2, "player");
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

        Rectangle2D newBox = new Rectangle2D(this.x, this.y, 8 * SHIP_SCALE, 5 * SHIP_SCALE);
        hitBox = newBox;
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
}
