package si.model;


import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private Swarm swarm;
//    private double startingSpeed;

    private BouncyAsteroidsGame game;

    public Level(BouncyAsteroidsGame g){
        game = g;
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
        swarm = new Swarm(2, game);
    }
}
