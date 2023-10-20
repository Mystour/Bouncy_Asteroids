package si.model;

import javafx.geometry.Rectangle2D;
public class Props implements Collisible{
    private boolean alive;
    private double x, y;
    private double speed_x;
    private double speed_y;
    private static final int radius = 30;

    public Props(double x, double y) {
        this.x = x;
        this.y = y;
        int speed = 1;
        double rotation = Math.random() * 2 * Math.PI;
        this.speed_x = speed * Math.cos(rotation);
        this.speed_y = speed * Math.sin(rotation);
        this.alive = true;
    }



    public boolean isAlive() {
        return alive;
    }


    public boolean isPlayer() {
        return false;
    }

    public boolean isEnemy() {
        return false;
    }

    public boolean isAsteroid() { return false; }

    public void move(double cX, double cY) {
        x += cX;
        y += cY;
    }

    public Rectangle2D getHitBox() {
        return new Rectangle2D(x, y, radius, radius);
    }

    public void tick() {
        move(speed_x, speed_y);
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public static int getRadius() {
        return radius;
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

    public void setSpeedX(double speed_x) {
        this.speed_x = speed_x;
    }

    public void setSpeedY(double speed_y) {
        this.speed_y = speed_y;
    }

    public boolean isCollision(Player p) {
        boolean collision = getHitBox().intersects(p.getHitBox());
        if (collision) {
            alive = false;
        }
        return collision;
    }
}
