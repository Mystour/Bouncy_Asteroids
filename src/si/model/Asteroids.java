package si.model;

import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.List;

public class Asteroids implements Hittable, Collisible {
    private boolean alive;
    private double x, y;
    private double rotation;
    private double speed_x;
    private double speed_y;
    private final PlanetType type;
    private final int numDetails; // Number of additional details
    private final List<Crater> craters;

    private final List<Double> coordinatesX = new ArrayList<>();
    private final List<Double> coordinatesY = new ArrayList<>();
    private final double[] shapeX;
    private final double[] shapeY;
    private final int numberOfVertices = 10; // change to vary the number of vertices on the asteroid

    public Asteroids(double x, double y, double rotation, PlanetType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        double speed = type.getSpeed();
        this.rotation = rotation;
        this.speed_x = speed * Math.cos(rotation);
        this.speed_y = speed * Math.sin(rotation);
        this.alive = true;

        this.numDetails= (int) (Math.random() * 10 + 3); // Random number of vertices for each asteroid
        this.craters = generateCraters();
        generateShape();
        this.shapeX = coordinatesX.stream().mapToDouble(Double::doubleValue).toArray();
        this.shapeY = coordinatesY.stream().mapToDouble(Double::doubleValue).toArray();
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

        for (int i = 0; i < numberOfVertices; i++) {
            shapeX[i] += cX;
            shapeY[i] += cY;
        }

        for (Crater crater : craters) {
            crater.move(cX, cY);
        }
    }


    public Rectangle2D getHitBox() {
        return new Rectangle2D(x - type.getRadius(), y - type.getRadius(), type.getRadius() * 2, type.getRadius() * 2);
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
            int detailSize = (int) type.getRadius(); // The size of the detail is a fraction of the asteroid's size
            double detailX = x + Math.random() * type.getRadius() - detailSize / 2; // The position of the detail is somewhere on the asteroid
            double detailY = y + Math.random() * type.getRadius() - detailSize / 2;

            int numVertices = (int) (Math.random() * 10 + 3); // Random number of vertices for each detail
            double[] x_coords_detail = new double[numVertices];
            double[] y_coords_detail = new double[numVertices];
            for (int j = 0; j < numVertices; j++) {
                // For each vertex, gets a random position around the centroid of the detail
                x_coords_detail[j] = detailX + (Math.cos(2 * Math.PI * j / numVertices) * detailSize * (0.5 + Math.random() / 2));
                y_coords_detail[j] = detailY + (Math.sin(2 * Math.PI * j / numVertices) * detailSize * (0.5 + Math.random() / 2));
            }
            craters.add(new Crater(detailSize, detailX, detailY, x_coords_detail, y_coords_detail, numVertices));
        }
        return craters;
    }

    public List<Crater> getCraters() {
        return craters;
    }

    public void generateShape() {
        for (int i = 0; i < 360; i += 360 / numberOfVertices) {
            double radius = type.getRadius() * (0.9 + Math.random() * 0.2);
            double radians = Math.toRadians(i);
            coordinatesX.add(x + radius * Math.cos(radians));
            coordinatesY.add(y + radius * Math.sin(radians));
        }
    }

    public double[] getCoordinatesX() {
        return shapeX;
    }

    public double[] getCoordinatesY() {
        return shapeY;
    }

    public int getNumberOfVertices() {
        return numberOfVertices;
    }
}
