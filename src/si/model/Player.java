package si.model;


import javafx.geometry.Rectangle2D;

public class Player implements Hittable {
    private int x;
    private int y;
    private float speed_x, speed_y;
    private Rectangle2D hitBox;
    private double rotation;  // Initialized to 0 by default
    private int weaponCountdown;
    private boolean alive = true;
    public static final int SHIP_SCALE = 4;
    private static final int WIDTH = SHIP_SCALE * 8;

    public Player() {
        // the player starts at the bottom of the screen, centered horizontally
        x = 400 - WIDTH;
        y = 450;

        hitBox = new Rectangle2D(x, y, 8 * SHIP_SCALE, 5 * SHIP_SCALE);
    }

    public int getX() {
        return x;
    }

    public int getY() {
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

    public Bullet fire() {
        Bullet b = null;
        if (weaponCountdown == 0) {
            b = new Bullet(x + 3 * SHIP_SCALE, y - 1 * SHIP_SCALE, true, "Player");
        }
        return b;
    }

    public void move(float x1, float y1) {
        Rectangle2D newBox = new Rectangle2D(x + x1, y+y1, 8 * SHIP_SCALE, 5 * SHIP_SCALE);
        if (BouncyAsteroidsGame.getScreenBounds().contains(newBox)) {
            hitBox = newBox;
            this.x += (int) x1;
            this.y += (int) y1;
        }
    }

    public void rotate(double degrees) {
        rotation += degrees;
        rotation = (rotation % 360 + 360) % 360; // Ensure rotation stays within [0, 360)
    }

    public double getRotation() {
        return rotation;
    }

    public float getSpeed_x() {
        return speed_x;
    }

    public float getSpeed_y() {
        return speed_y;
    }

    public void accelerate() {
        float acceleration = 0.2f;
        double radians = rotation + 3*Math.PI/2;
        speed_x += (float) (acceleration * Math.cos(radians));
        speed_y += (float) (acceleration * Math.sin(radians));
    }

}
