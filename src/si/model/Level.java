package si.model;


import java.util.ArrayList;
import java.util.List;

import java.time.Duration;
import java.time.Instant;

public class Level {
    private Swarm swarm;
    int numA, numB, numC;

    private final BouncyAsteroidsGame game;
    private Instant enemyStartTime;
    private Instant propsStartTime;


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


    public List<Hittable> getHittable() {
        return new ArrayList<Hittable>(swarm.getHittable());
    }

    public List<Collisible> getCollisible() {
        return new ArrayList<Collisible>(swarm.getCollisible());
    }

    public List<Asteroids> getAsteroids() {
        return swarm.getAsteroids();
    }

    public List<EnemyShip> getEnemyShips() {
        return swarm.getEnemyShips();
    }
    public List<Props> getProps(){ return swarm.getProps();}

    public void reset() {
        swarm = new Swarm(numA, numB, numC, game);
        enemyStartTime = Instant.now();
        propsStartTime = Instant.now();
    }


    public List<Bullet> move(int currentLevel) {
        swarm.move();
        if (has30SecondsPassed()) {
            EnemyShip newShip = createEnemyShip(currentLevel);
            swarm.addEnemyShip(newShip);
            enemyStartTime = Instant.now();
        }
        if (has20SecondsPassed()) {
            Props newProps = createProps();
            swarm.addProps(newProps);
            propsStartTime = Instant.now();
        }

        Player player = game.getPlayer();
        if (player.has10SecondsPassed()){
            player.setTripleFire(false);
        }
        return getEbullet();
    }

    private boolean has30SecondsPassed() {
        return Duration.between(enemyStartTime, Instant.now()).getSeconds() >= 30;
    }

    private boolean has20SecondsPassed() {
        return Duration.between(propsStartTime, Instant.now()).getSeconds() >= 20;
    }

    private EnemyShip createEnemyShip(int currentLevel) {
        double x, y;
        AlienType type;
        if (currentLevel <= 5) {
            type = AlienType.A;
        } else {
            type = AlienType.B;
        }
        x = type.getWidth() + Math.random() * (game.getScreenWidth() - 2 * type.getWidth());
        y = type.getHeight() + Math.random() * (game.getScreenHeight() - 2 * type.getHeight());
        while (Math.abs(x - game.getPlayer().getX()) < 20 && Math.abs(y - game.getPlayer().getY()) < 20) {
            x = type.getWidth() + Math.random() * game.getScreenWidth();
            y = type.getHeight() + Math.random() * game.getScreenHeight();
        }
        return new EnemyShip(x, y, type);
    }

    private Props createProps() {
        double x, y;
        x = Props.getRadius() + Math.random() * (game.getScreenWidth() - 2 * Props.getRadius());
        y = Props.getRadius() + Math.random() * (game.getScreenHeight() - 2 * Props.getRadius());
        while (Math.abs(x - game.getPlayer().getX()) < 20 && Math.abs(y - game.getPlayer().getY()) < 20) {
            x = Props.getRadius() + Math.random() * game.getScreenWidth();
            y = Props.getRadius() + Math.random() * game.getScreenHeight();
        }
        return new Props(x, y);
    }

    private List<Bullet> getEbullet() {
        List<Bullet> eBullets = new ArrayList<Bullet>();
        Player player = game.getPlayer();
        double x = player.getX();
        double y = player.getY();
        List<EnemyShip> ships = swarm.getEnemyShips();
        for (EnemyShip s : ships) {
            double rotation = Math.atan2(y - s.getY(), x - s.getX());  // the range is [-pi, pi]
            Bullet b = s.fire(rotation);
            if (b != null) {
                eBullets.add(b);
            }
        }
        return eBullets;
    }

    public void setEnemyStartTime(Instant enemyStartTime) {
        this.enemyStartTime = enemyStartTime;
    }

    public void setPropsStartTime(Instant propsStartTime) {
        this.propsStartTime = propsStartTime;
    }

    public Swarm getSwarm() {
        return swarm;
    }
}
