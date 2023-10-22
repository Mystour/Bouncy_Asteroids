package si.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

import java.util.ArrayList;
import java.util.List;

public class Asteroids implements Hittable, Collisible {
    private boolean alive;
    private double x, y;
    private double rotation;
    private double speed_x;
    private double speed_y;
    private final PlanetType type;
    private final int numDetails = (int)(Math.random()*10+5); // Number of additional details
    private final List<Crater> craters ;

    public Asteroids(double x, double y, double rotation, PlanetType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        double speed = type.getSpeed();
        this.rotation = rotation;
        this.speed_x = speed * Math.cos(rotation);
        this.speed_y = speed * Math.sin(rotation);
        this.alive = true;

        this.craters = generateCraters();
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

    public int getPoints() {
        return type.getScore();
    }

    public boolean isPlayer() {
        return false;
    }

    public void move(double cX, double cY) {
        x += cX;
        y += cY;
        for (Crater crater : craters) {
            for (int i = 0; i < crater.getNumVertices(); i++) {
                crater.getX_coords_detail()[i] += cX;
                crater.getY_coords_detail()[i] += cY;
            }
        }
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
    public void setRotation(double rotation) { this.rotation = rotation; }

    private List<Crater> generateCraters() {
        List<Crater> craters = new ArrayList<Crater>();
        for (int i = 0; i < numDetails; i++) {
            // Create an asteroid detail (a smaller irregular polygon)
            int detailSize = (int) (Math.random() * type.getRadius() / 2); // The size of the detail is a fraction of the asteroid's size
            double detailX = x + Math.random() * type.getRadius() - detailSize / 2; // The position of the detail is somewhere on the asteroid
            double detailY = y + Math.random() * type.getRadius() - detailSize / 2;

            int numVertices = (int) (Math.random() * 10 + 3); // Random number of vertices for each detail
            double[] x_coords_detail = new double[numVertices];
            double[] y_coords_detail = new double[numVertices];
            for (int j = 0; j < numVertices; j++) {
                // For each vertex, gets a random position around the centroid of the detail
                x_coords_detail[j] = detailX + detailSize / 2 + (Math.cos(2 * Math.PI * j / numVertices) * detailSize / 2 * (0.5 + Math.random() / 2));
                y_coords_detail[j] = detailY + detailSize / 2 + (Math.sin(2 * Math.PI * j / numVertices) * detailSize / 2 * (0.5 + Math.random() / 2));
            }

            RadialGradient gradient = new RadialGradient(
                    0, 0, detailX + detailSize / 2, detailY + detailSize / 2, detailSize / 2, false, CycleMethod.NO_CYCLE,
                    new Stop(0.0, Color.LIGHTGRAY),
                    new Stop(0.8, Color.DARKGRAY),
                    new Stop(1.0, Color.BLACK)
            );
            craters.add(new Crater(gradient, x_coords_detail, y_coords_detail, numVertices));
        }
        return craters;
    }

    public List<Crater> getCraters() {
        return craters;
    }
}
