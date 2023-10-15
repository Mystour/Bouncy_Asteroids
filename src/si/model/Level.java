package si.model;


import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.List;

import java.time.Duration;
import java.time.Instant;

public class Level {
    private Swarm swarm;
    int numA, numB, numC, numS;

    private BouncyAsteroidsGame game;
    private Instant levelStartTime;

    private boolean passed;


    public Level(int A, int B, int C, int S, BouncyAsteroidsGame g){
        game = g;
        numA = A;
        numB = B;
        numC = C;
        numS = S;
        reset();
    }

    public int getAsteroidsRemaining() {
        return swarm.getAsteroidsRemaining();
    }


    public List<Hittable> getHittable() {
        List<Hittable> targets = new ArrayList<Hittable>();
        targets.addAll(swarm.getHittable());
        return targets;
    }

    public List<Asteroids> getAsteroids() {
        return swarm.getAsteroids();
    }

    public List<EnemyShip> getEnemyShips() {
        return swarm.getEnemyShips();
    }

    public void reset() {
        swarm = new Swarm(numA, numB, numC, game);
        levelStartTime = Instant.now();
    }


    public List<Bullet> move() {
        swarm.move();
        List<Bullet> eBullets = new ArrayList<Bullet>();
        if (has30SecondsPassed()) {
            EnemyShip newShip = createEnemyShip();
            swarm.addEnemyShip(newShip);

            Player player = game.getPlayer();
            double x = player.getX();
            double y = player.getY();
            List<EnemyShip> ships = swarm.getEnemyShips();
            for (EnemyShip s : ships) {
                double rotation = Math.atan2(s.getY() - y, x - s.getX());
                Bullet b = s.fire(rotation);
                if (b != null) {
                    eBullets.add(b);
                }
            }
            levelStartTime = Instant.now();
        }
        return eBullets;
    }

    private boolean has30SecondsPassed() {
        passed = Duration.between(levelStartTime, Instant.now()).getSeconds() >= 3;
        return passed;
    }

    public boolean getPassed() {
        return passed;
    }

    private EnemyShip createEnemyShip() {
        double x, y;
        x = Math.random() * game.getScreenWidth();
        y = Math.random() * game.getScreenHeight();
        while (Math.abs(x - game.getPlayer().getX()) < 20 && Math.abs(y - game.getPlayer().getY()) < 20) {
            x = Math.random() * game.getScreenWidth();
            y = Math.random() * game.getScreenHeight();
        }
        return new EnemyShip(x, y, AlienType.A);
    }
}
