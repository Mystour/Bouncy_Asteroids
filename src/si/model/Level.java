package si.model;


import java.util.ArrayList;
import java.util.List;

public class Level {
    private Swarm swarm;
    int numA, numB, numC;

    private final BouncyAsteroidsGame game;
    private double levelTime = 0;
    private int enemyCountdown = 60 * 30;


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

    public void reset() {
        swarm = new Swarm(numA, numB, numC, game);
    }


    public List<Bullet> move(int currentLevel) {
        swarm.move();
        if (enemyCountdown <= 0) {
            EnemyShip newShip = createEnemyShip(currentLevel);
            swarm.addEnemyShip(newShip);
            enemyCountdown = 60 * 30;
        }

        Player player = game.getPlayer();
        if (player.has10SecondsPassed()){
            player.setTripleFire(false);
        }
        return getEbullet();
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

    public void tick(){
        levelTime += 1/60.0;
        enemyCountdown--;
    }

    public double getLevelTime(){
        return levelTime;
    }


    public Swarm getSwarm() {
        return swarm;
    }
}
