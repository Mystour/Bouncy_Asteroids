package si.model;


import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private Swarm swarm;
    int numA, numB, numC, numS;

    private BouncyAsteroidsGame game;

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
        swarm = new Swarm(numA, numB, numC, numS, game);
    }


    public List<Bullet> move() {
        swarm.move();
        Player player = game.getPlayer();
        double x = player.getX();
        double y = player.getY();
        List<EnemyShip> ships = swarm.getEnemyShips();
        List<Bullet> eBullets = new ArrayList<Bullet>();
        for (EnemyShip s : ships) {
            double rotation = Math.atan2(s.getY() - y, x - s.getX());  // adjusted y direction
            Bullet b = s.fire(rotation);
            if (b != null) {
                eBullets.add(b);
            }
        }
        return eBullets;
    }
}
