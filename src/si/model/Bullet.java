package si.model;


import javafx.geometry.Rectangle2D;

public class Bullet implements Movable, Hittable {
    private double x, y;
    private boolean direction; // True for up, false for down
    private boolean alive = true;
    private Rectangle2D hitBox;
    private String name;
    private static int bulletCounter = 0;
    public static final int BULLET_HEIGHT = 8;
    public static final int BULLET_WIDTH = 4;
    private static final int BULLET_SPEED = 5;

    public Bullet(double x, double y, boolean direction, String name) {
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.name = name + " " + bulletCounter++;
        hitBox = new Rectangle2D(x, y, BULLET_WIDTH, BULLET_HEIGHT);
    }

    public void move() {
        if (direction) {
            y -= BULLET_SPEED;
        } else {
            y += BULLET_SPEED;
        }
        hitBox = new Rectangle2D(x, y, BULLET_WIDTH, BULLET_HEIGHT);
    }

    public double getX() { return x; }

    public double getY() {
        return y;
    }

    public boolean isHit(Bullet b) {
        boolean hit = hitBox.intersects(b.getHitBox());
        if (hit) {
            alive = false;
            b.alive = false;
        }
        return hit;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getPoints() {
        return 0;
    }

    public boolean isPlayer() {
        return false;
    }

    @Override
    public Rectangle2D getHitBox() {
        return hitBox;
    }

    public void destroy() {
        alive = false;
    }

}
