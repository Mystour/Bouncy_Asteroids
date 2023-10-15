package si.model;

import java.util.ArrayList;
import java.util.List;

public class Swarm implements Movable {
    private List<Asteroids> asteroids;
    private List<EnemyShip> ships;
    private double x, y;
    private Player player;
    private BouncyAsteroidsGame game;

    public Swarm(int numA, int numB, int numC, int numS, BouncyAsteroidsGame g) {
        game = g;
        asteroids = new ArrayList<>();
        ships = new ArrayList<>();
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

        for (int i = 0; i < numS; i++) {
            x = Math.random() * game.getScreenWidth();
            y = Math.random() * game.getScreenHeight();
            while (Math.abs(x - player.getX()) < 20 && Math.abs(y - player.getY()) < 20) {
                x = Math.random() * game.getScreenWidth();
                y = Math.random() * game.getScreenHeight();
            }
            EnemyShip s = new EnemyShip(x, y, AlienType.A);
            ships.add(s);
        }
    }

    public void move() {
        List<Asteroids> removeA = new ArrayList<Asteroids>();
        List<Asteroids> addA = new ArrayList<Asteroids>();
        List<EnemyShip> removeS = new ArrayList<EnemyShip>();
        for (Asteroids s : asteroids) {
            if (!s.isAlive()) {
                removeA.add(s);
                PlanetType type = s.getType();
                if (type == PlanetType.A) {
                    int x = s.getX();
                    int y = s.getY();
                    double rotation = s.getRotation();
                    Asteroids a = new Asteroids(x, y, rotation + Math.random() * Math.PI / 9, PlanetType.B);
                    Asteroids b = new Asteroids(x, y, rotation - Math.random() * Math.PI / 9, PlanetType.B);

                    addA.add(a);
                    addA.add(b);
                } else if (type == PlanetType.B) {
                    int x = s.getX();
                    int y = s.getY();
                    double rotation = s.getRotation();
                    Asteroids a = new Asteroids(x, y, rotation + Math.random() * Math.PI / 9, PlanetType.C);
                    Asteroids b = new Asteroids(x, y, rotation - Math.random() * Math.PI / 9, PlanetType.C);

                    addA.add(a);
                    addA.add(b);
                }
            }
        }
        asteroids.removeAll(removeA);  // remove dead asteroids
        asteroids.addAll(addA);        // add new asteroids

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

        if (game.getCurrentLevel() != null && game.getCurrentLevel().getPassed()) {
            for (EnemyShip s : ships) {
                if (!s.isAlive()) {
                    removeS.add(s);
                }
            }
            ships.removeAll(removeS);

            for (EnemyShip s: ships){
                double speed_x, speed_y;
                AlienType type = s.getType();
                int height = type.getHeight();
                int width = type.getWidth();

                speed_x = s.getSpeedX();
                speed_y = s.getSpeedY();

                if (s.getX() + speed_x > game.getScreenWidth() - width || s.getX() + speed_x < 0) {
                    speed_x = -speed_x;
                    s.setSpeedX(speed_x);
                }
                if (s.getY() + speed_y > game.getScreenHeight() - height || s.getY() + speed_y < 0) {
                    speed_y = -speed_y;
                    s.setSpeedY(speed_y);
                }
                s.move(speed_x, speed_y);
            }
        }
    }

    public List<Hittable> getHittable() {
        return new ArrayList<Hittable>(asteroids);
    }

    public List<Asteroids> getAsteroids() {
        return new ArrayList<Asteroids>(asteroids);
    }

    public List<EnemyShip> getEnemyShips() {
        return new ArrayList<EnemyShip>(ships);
    }

    public int getAsteroidsRemaining() { return asteroids.size(); }
}