package si.model;


import javafx.geometry.Rectangle2D;

public class Player implements Hittable {
    private int x;
    private int y;
    private Rectangle2D hitBox;
    private int weaponCountdown;
    private boolean alive = true;
    public static final int SHIP_SCALE = 4;
    private static final int WIDTH = SHIP_SCALE * 8;

    public Player() {
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

    public void move(int x1, int y1) {
        Rectangle2D newBox = new Rectangle2D(x + x1, y+y1, 8 * SHIP_SCALE, 5 * SHIP_SCALE);
        if (BouncyAsteroidsGame.getScreenBounds().contains(newBox)) {
            hitBox = newBox;
            this.x += x1;
            this.y += y1;
        }
    }

}
