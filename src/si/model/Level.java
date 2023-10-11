package si.model;


import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private double startingSpeed;
    private int rows;
    private int cols;
    private BouncyAsteroidsGame game;


    public List<Hittable> getHittable() {
        List<Hittable> targets = new ArrayList<Hittable>();
        return targets;
    }

}
