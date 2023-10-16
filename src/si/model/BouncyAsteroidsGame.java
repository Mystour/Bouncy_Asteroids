package si.model;

import ucd.comp2011j.engine.Game;
import javafx.geometry.Rectangle2D;
import si.display.PlayerListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public class BouncyAsteroidsGame implements Game {
    private int playerLives;
    private int playerScore;
    private int count = 1;
    private boolean pause = true;
    public static final int SCREEN_WIDTH = 768;
    public static final int SCREEN_HEIGHT = 512;
    private static final Rectangle2D SCREEN_BOUNDS = new Rectangle2D(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    private List<Bullet> playerBullets;
    private List<Bullet> enemyBullets;
    private ArrayList<Hittable> targets;
    private final PlayerListener listener;
    private Player player;
    private Level[] level;
    private static final int NO_LEVELS = 10;
    private int currentLevel;

    public BouncyAsteroidsGame(PlayerListener listener) {
        this.listener = listener;
        startNewGame();
    }

    @Override
    public int getPlayerScore() {
        return playerScore;
    }

    public int getLives() {
        return playerLives;
    }

    public void checkForPause() {
        if (listener.hasPressedPause()) {
            pause = !pause;
            listener.resetPause();
        }
    }

    @Override
    public void updateGame() {
        if (!isPaused()) {
            player.tick();
            targets.clear();
            targets.addAll(level[currentLevel].getHittable());
            targets.add(player);
            playerBullets();
            if (getEnemyShips() != null) {
                enemyBullets();
                enemyBullets.addAll(level[currentLevel].move(currentLevel));
            }
            Asteroids();
            EnemyShips();
            movePlayer();
            level[currentLevel].move(currentLevel);
        }
    }


    private void movePlayer() {
        if (listener.isPressingFire()) {
            Bullet b = player.fire();
            if (b != null) {
                playerBullets.add(b);
            }
        }
        if (listener.isPressingUp()){
            player.accelerate();
        }
        else if (listener.isPressingLeft()) {
            player.rotate(-Math.PI/32);
        }
        else if (listener.isPressingRight()) {
            player.rotate(Math.PI/32);
        }
        player.move();
    }

    private void playerBullets() {
        List<Bullet> remove = new ArrayList<>();
        for (Bullet playerBullet : playerBullets) {
            if (playerBullet.isAlive() && playerBullet.getHitBox().intersects(SCREEN_BOUNDS)) {
                playerBullet.move();
                for (Hittable t : targets) {
                    if (!t.isPlayer() && t.isHit(playerBullet)) {
                        playerScore += t.getPoints();
                        hasOver10000();
                        playerBullet.destroy();
                    }
                }
            } else {
                remove.add(playerBullet);
            }
        }
        playerBullets.removeAll(remove);
    }

    private void enemyBullets() {
        List<Bullet> remove = new ArrayList<Bullet>();
        for (Bullet b : enemyBullets) {
            if (b.isAlive() && b.getHitBox().intersects(SCREEN_BOUNDS)) {
                b.move();
                for (Hittable t : targets) {
                    if (t != b) {
                        if (!t.isEnemy() && t.isHit(b)) {
                            if (t.isPlayer()) {
                                playerLives--;
                                pause = true;
                            }
                            b.destroy();
                        }
                    }
                }
            } else {
                remove.add(b);
            }
        }
        enemyBullets.removeAll(remove);
    }

    private void Asteroids() {
        for (int i = 0; i < level[currentLevel].getAsteroids().size(); i++) {
            Asteroids a = level[currentLevel].getAsteroids().get(i);
            if (a.isAlive() && a.getHitBox().intersects(SCREEN_BOUNDS)) {
                if (player.isHit(a)) {
                    playerLives--;
                    pause = true;
                }
            }
        }
    }

    private void EnemyShips() {
        for (EnemyShip s : level[currentLevel].getEnemyShips()) {
            if (s.isAlive() && s.getHitBox().intersects(SCREEN_BOUNDS)) {
                if (player.isHit(s)) {
                    playerLives--;
                    pause = true;
                }
            }
            for (Asteroids a : level[currentLevel].getAsteroids()) {
                if (a.isHit(s)) {
                    s.setAlive(false);
                }
            }
        }
    }


    public static Rectangle2D getScreenBounds() {
        return SCREEN_BOUNDS;
    }

    @Override
    public boolean isPaused() {
        return pause;
    }

    @Override
    public void startNewGame() {
        targets = new ArrayList<Hittable>();
        playerLives = 3;
        playerScore = 0;
        currentLevel = 0;
        playerBullets = new ArrayList<Bullet>();
        enemyBullets = new ArrayList<Bullet>();
        player = new Player();
        level = new Level[NO_LEVELS];
        level[0] = new Level(1, 0, 0, this);
        level[1] = new Level(1, 0, 1, this);
        level[2] = new Level(1, 1, 1, this);
        level[3] = new Level(2, 0, 0, this);
        level[4] = new Level(2, 1, 0, this);
        level[5] = new Level(2, 1, 1, this);
        level[6] = new Level(3, 0, 0, this);
        level[7] = new Level(3, 1, 0, this);
        level[8] = new Level(3, 1, 1, this);
        level[9] = new Level(3, 2, 1, this);
    }

    @Override
    public boolean isLevelFinished() {
        if (currentLevel < NO_LEVELS) {
            int noAsteroids = level[currentLevel].getAsteroidsRemaining();
            if (noAsteroids == 0 && level[currentLevel].getEnemyShips().isEmpty()){
                level[currentLevel+1].setLevelStartTime(Instant.now());
                return true;
            }
            else {
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public boolean isPlayerAlive() {
        return player.isAlive();
    }

    @Override
    public void resetDestroyedPlayer() {
        System.out.println("Resetting player");
        player.resetDestroyed();
        playerBullets = new ArrayList<Bullet>();
    }

    @Override
    public void moveToNextLevel() {
        pause = true;
        currentLevel++;
        player.resetDestroyed();
        playerBullets = new ArrayList<Bullet>();
    }

    @Override
    public boolean isGameOver() {
        return !(playerLives > 0 && currentLevel <= NO_LEVELS);
    }


    public int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public Player getShip() {
        return player;
    }

    public List<Bullet> getBullets() {
        ArrayList<Bullet> bullets = new ArrayList<>();
        bullets.addAll(playerBullets);
        bullets.addAll(enemyBullets);
        return bullets;
    }

    public List<Asteroids> getAsteroids() {
        return level[currentLevel].getAsteroids();
    }

    public List<EnemyShip> getEnemyShips() {
        return level[currentLevel].getEnemyShips();
    }

    public int getLevel() { return currentLevel + 1; }

    public Player getPlayer() { return player; }

    public void hasOver10000(){
        if (playerScore > 10000 * count){
            playerLives++;
            count++;
        }
    }
}
