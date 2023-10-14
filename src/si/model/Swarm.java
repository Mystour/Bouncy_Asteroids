package si.model;

import javafx.geometry.Rectangle2D;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Swarm implements Movable {
    private List<Asteroids> asteroids;
    private boolean direction = true; // true for moving right false for left
    private double x, y;
    private int space = 30;
    private Asteroids[][] AsteroidGrid;
//    private int rows, cols;
    private int count = 0;
    private double speed = 1;

    private double radius;
    private Player player;
    private BouncyAsteroidsGame game;

    public Swarm(int numA, int numB, int numC, BouncyAsteroidsGame g) {
        game = g;
        asteroids = new ArrayList<Asteroids>();
        player = game.getPlayer();
        for (int i = 0; i < numA; i++) {
            x = Math.random() * game.getScreenWidth();
            y = Math.random() * game.getScreenHeight();
            while (Math.abs(x - player.getX()) <  PlanetType.A.getRadius() && Math.abs(y - player.getY()) < PlanetType.A.getRadius()) {
                x = Math.random() * game.getScreenWidth();
                y = Math.random() * game.getScreenHeight();
            }
            Asteroids a = new Asteroids(x, y, Math.random()* 2 * Math.PI, PlanetType.A);
            asteroids.add(a);
        }
        for (int i = 0; i < numB; i++) {
            x = Math.random() * game.getScreenWidth();
            y = Math.random() * game.getScreenHeight();
            while (Math.abs(x - player.getX()) <  PlanetType.B.getRadius() && Math.abs(y - player.getY()) < PlanetType.B.getRadius()) {
                x = Math.random() * game.getScreenWidth();
                y = Math.random() * game.getScreenHeight();
            }
            Asteroids a = new Asteroids(x, y, Math.random()* 2 * Math.PI, PlanetType.B);
            asteroids.add(a);
        }
        for (int i = 0; i < numC; i++) {
            x = Math.random() * game.getScreenWidth();
            y = Math.random() * game.getScreenHeight();
            while (Math.abs(x - player.getX()) <  PlanetType.C.getRadius() && Math.abs(y - player.getY()) < PlanetType.C.getRadius()) {
                x = Math.random() * game.getScreenWidth();
                y = Math.random() * game.getScreenHeight();
            }
            Asteroids a = new Asteroids(x, y, Math.random()* 2 * Math.PI, PlanetType.C);
            asteroids.add(a);
        }
    }

    public void move() {
        List<Asteroids> remove = new ArrayList<Asteroids>();
        List<Asteroids> add = new ArrayList<Asteroids>();
        for (Asteroids s : asteroids) {
            if (!s.isAlive()) {
                remove.add(s);
                PlanetType type = s.getType();
                if (type == PlanetType.A) {
                    int x = s.getX();
                    int y = s.getY();
                    double rotation = s.getRotation();
                    Asteroids a = new Asteroids(x, y, rotation + Math.random() * Math.PI / 9, PlanetType.B);
                    Asteroids b = new Asteroids(x, y, rotation - Math.random() * Math.PI / 9, PlanetType.B);

                    add.add(a);
                    add.add(b);
                } else if (type == PlanetType.B) {
                    int x = s.getX();
                    int y = s.getY();
                    double rotation = s.getRotation();
                    Asteroids a = new Asteroids(x, y, rotation + Math.random() * Math.PI / 9, PlanetType.C);
                    Asteroids b = new Asteroids(x, y, rotation - Math.random() * Math.PI / 9, PlanetType.C);

                    add.add(a);
                    add.add(b);
                }
            }
        }
        asteroids.removeAll(remove);  // remove dead asteroids
        asteroids.addAll(add);        // add new asteroids

        // randomly move asteroids
        for (Asteroids s : asteroids) {
            double speed_x, speed_y;
            PlanetType type = s.getType();
            int radius = type.getRadius();
            speed_x = s.getSpeedX();
            speed_y = s.getSpeedY();

            if (s.getX() + speed_x > game.getScreenWidth() - radius || s.getX() + speed_x < 0) {
                speed_x = -speed_x;
                s.setSpeedX(speed_x);
            }
            if (s.getY() + speed_y > game.getScreenHeight() - radius || s.getY() + speed_y < 0) {
                speed_y = -speed_y;
                s.setSpeedY(speed_y);
            }
            s.move(speed_x, speed_y);
        }
    }

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

    public int getAsteroidsRemaining() { return asteroids.size(); }
}
