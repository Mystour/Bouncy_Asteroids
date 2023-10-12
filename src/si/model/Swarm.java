package si.model;

import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.List;

public class Swarm implements Movable {
    private List<Asteroids> asteroids;
    private boolean direction = true; // true for moving right false for left
    private double x = 50, y = 40;
    private int space = 30;
    private Asteroids[][] shipGrid;
//    private int rows, cols;
    private int number;
    private int count = 0;
    private double moveX;
    private double moveY;
    private BouncyAsteroidsGame game;

    public Swarm(int numA, BouncyAsteroidsGame g) {
        number = numA;
        asteroids = new ArrayList<Asteroids>();
        for (int i = 0; i < number; i++) {
            Asteroids a = new Asteroids((int) x + (1 + space) * i, (int) y, PlanetType.A);
            asteroids.add(a);
        }
    }

    public void move() {
        List<Asteroids> remove = new ArrayList<Asteroids>();
        for (Asteroids s : asteroids) {
            if (!s.isAlive()) {
                remove.add(s);
            }
        }
        asteroids.removeAll(remove);  // remove dead asteroids

        // randomly move asteroids
        for (Asteroids s : asteroids) {
            s.move(0, 0);
        }

//        if (count % 25 == 0) {
//            double cX = ((direction) ? moveX : -moveX);
//            double cY = 0;
//            if (x + getWidth() + cX > game.getScreenWidth() - 20 || getAdjustedX() + cX < 20) {
//                direction = !direction;
//                cY = moveY * 5;
//                cX = ((direction) ? moveX : -moveX);
//                if (direction) {
//                    moveX += 0.25;
//                }
//            } else {
//                y = y + cY;
//                x = x + cX;
//            }
//            for (Asteroids s : asteroids) {
//                s.move(cX, cY);
//            }
//
//        }
    }

//    private int getAdjustedX() {
//        int w = 0;
//        for (int i = 0; i < cols; i++) {
//            int c = 0;
//            for (int j = 0; j < rows; j++) {
//                if (!shipGrid[j][i].isAlive()) {
//                    c++;
//                }
//            }
//            if (c == rows) {
//                w++;
//            } else {
//                break;
//            }
//        }
//        return (int) x + w * space;
//    }

//    public int getBottomY() {
//        double b = 0;
//        for (Asteroids e : getBottom()) {
//            Rectangle2D hb = e.getHitBox();
//            if (e.getY() + hb.getHeight() > b) {
//                b = e.getY() + hb.getWidth();
//            }
//        }
//        return (int)b;
//    }

//    private int getWidth() {
//        int w = cols;
//        for (int i = cols - 1; i >= 0; i--) {
//            int c = 0;
//            for (int j = 0; j < rows; j++) {
//                if (!shipGrid[j][i].isAlive()) {
//                    c++;
//                }
//            }
//            if (c == rows) {
//                w--;
//            } else {
//                break;
//            }
//        }
//        return w * space;
//    }

    public void tick() {
        count++;
    }

    public List<Hittable> getHittable() {
        return new ArrayList<Hittable>(asteroids);
    }

//    public List<Asteroids> getBottom() {
//        List<Asteroids> bottomasteroids = new ArrayList<Asteroids>();
//
//        for (int i = 0; i < cols; i++) {
//            boolean found = false;
//            for (int j = rows - 1; j >= 0 && !found; j--) {
//                if (shipGrid[j][i].isAlive()) {
//                    found = true;
//                    bottomasteroids.add(shipGrid[j][i]);
//                }
//            }
//        }
//        return bottomasteroids;
//    }

    public List<Asteroids> getAsteroids() {
        return new ArrayList<Asteroids>(asteroids);
    }

    public int getAsteroidsRemaining() { return number; }
}
