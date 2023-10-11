package si.model;

import ucd.comp2011j.engine.Game;
import javafx.geometry.Rectangle2D;
import si.display.PlayerListener;

import java.util.ArrayList;
import java.util.List;

public class BouncyAsteroidsGame implements Game {
    private int playerLives;
    private int playerScore;
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
    public static final int BUNKER_TOP = 350;
    private static final int NO_LEVELS = 5;
    private int currentLevel = 0;

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
//            player.tick();
//            targets.clear();
//            targets.addAll(level[currentLevel].getHittable());
//            targets.add(player);
//            playerBullets();
//            enemyBullets();
//            enemyBullets.addAll(level[currentLevel].move());
              movePlayer();
        }
    }


    private void movePlayer() {
//        if (listener.isPressingFire()) {
//            Bullet b = player.fire();
//            if (b != null) {
//                playerBullets.add(b);
//            }
//        }
        if (listener.isPressingUp()){
            double radians = player.getRotation() + 3*Math.PI/2;
            int speed = player.getSpeed();
            int dx = (int) (speed * Math.cos(radians));
            int dy = (int) (speed * Math.sin(radians));
            player.move(dx, dy);
        } else if (listener.isPressingLeft()) {
            player.rotate(-Math.PI/32);
        } else if (listener.isPressingRight()) {
            player.rotate(Math.PI/32);
        }
    }

//    private void playerBullets() {
//        List<Bullet> remove = new ArrayList<Bullet>();
//        for (int i = 0; i < playerBullets.size(); i++) {
//            if (playerBullets.get(i).isAlive() && playerBullets.get(i).getHitBox().intersects(SCREEN_BOUNDS)) {
//                playerBullets.get(i).move();
//                for (Hittable t : targets) {
//                    if (t != playerBullets.get(i)) {
//                        if (t.isHit(playerBullets.get(i))) {
//                            playerScore += t.getPoints();
//                            playerBullets.get(i).destroy();
//                        }
//                    }
//                }
//            } else {
//                remove.add(playerBullets.get(i));
//            }
//        }
//        playerBullets.removeAll(remove);
//    }

//    private void enemyBullets() {
//        List<Bullet> remove = new ArrayList<Bullet>();
//        for (int i = 0; i < enemyBullets.size(); i++) {
//            Bullet b = enemyBullets.get(i);
//            if (b.isAlive() && b.getHitBox().intersects(SCREEN_BOUNDS)) {
//                b.move();
//                for (Hittable t : targets) {
//                    if (t != b) {
//                        if (t.isHit(b)) {
//                            if (t.isPlayer()) {
//                                playerLives--;
//                                pause = true;
//                            }
//                            b.destroy();
//                        }
//                    }
//                }
//            } else {
//                remove.add(b);
//            }
//        }
//        enemyBullets.removeAll(remove);
//    }

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
        playerBullets = new ArrayList<Bullet>();
        enemyBullets = new ArrayList<Bullet>();
        player = new Player();
//        level = new Level[5];
//        level[0] = new Level(0.5, 3, 10, this);
//        level[1] = new Level(1, 4, 11, this);
//        level[2] = new Level(1.5, 5, 12, this);
//        level[3] = new Level(2, 5, 14, this);
//        level[4] = new Level(2.5, 5, 16, this);
    }

    @Override
    public boolean isLevelFinished() {
//        if (currentLevel < NO_LEVELS) {
//            int noShips = level[currentLevel].getShipsRemaining();
//            return level[currentLevel].getBottomY() >= BUNKER_TOP || noShips == 0;
//        } else {
//            return true;
//        }
        return false;
    }

    @Override
    public boolean isPlayerAlive() {
        return player.isAlive();
    }

    @Override
    public void resetDestroyedPlayer() {
//        System.out.println("Resetting player");
//        player.resetDestroyed();
//        playerBullets = new ArrayList<Bullet>();
//        enemyBullets = new ArrayList<Bullet>();
//        System.out.println(enemyBullets.size());
    }

    @Override
    public void moveToNextLevel() {
//        pause = true;
//        currentLevel++;
//        player.resetDestroyed();
//        playerBullets = new ArrayList<Bullet>();
//        enemyBullets = new ArrayList<Bullet>();
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

//    public List<Bullet> getBullets() {
//        ArrayList<Bullet> bullets = new ArrayList<Bullet>();
//        bullets.addAll(playerBullets);
//        bullets.addAll(enemyBullets);
//        return bullets;
//    }

//    public List<EnemyShip> getEnemyShips() {
//        return level[currentLevel].getEnemyShips();
//    }
//
//    public List<Rectangle2D> getBunkers() {
//        return level[currentLevel].getBunkers();
//    }
}
