package si.model;


import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private Swarm swarm;
//    private double startingSpeed;
    int numA, numB, numC;

    private BouncyAsteroidsGame game;

    public Level(int A, int B, int C, BouncyAsteroidsGame g){
        game = g;
        numA = A;
        numB = B;
        numC = C;
        reset();
    }

    public int getAsteroidsRemaining() {
        return swarm.getAsteroidsRemaining();
    }
//
//    public int getBottomY() {
//        return swarm.getBottomY();
//    }

    public List<Hittable> getHittable() {
        List<Hittable> targets = new ArrayList<Hittable>();
        targets.addAll(swarm.getHittable());
        return targets;
    }

    public List<Asteroids> getAsteroids() {
        return swarm.getAsteroids();
    }

    public void reset() {
        swarm = new Swarm(numA, numB, numC, game);
    }

    public void move() {
        swarm.move();
    }
}
