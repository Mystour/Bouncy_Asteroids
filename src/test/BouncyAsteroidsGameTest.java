package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import si.display.PlayerListener;
import si.model.BouncyAsteroidsGame;

import static org.junit.jupiter.api.Assertions.*;

class BouncyAsteroidsGameTest {
    private BouncyAsteroidsGame game;

    @BeforeEach
    void setUp() {
        game = new BouncyAsteroidsGame(new PlayerListener());
    }

    @Test
    void playerScoreIsInitiallyZero() {
        assertEquals(0, game.getPlayerScore());
    }

    @Test
    void playerLivesAreInitiallyThree() {
        assertEquals(3, game.getLives());
    }

    @Test
    void gameIsInitiallyPaused() {
        assertTrue(game.isPaused());
    }

    @Test
    void gameIsInitiallyNotOver() {
        assertFalse(game.isGameOver());
    }

    @Test
    void gameIsInitiallyNotLevelFinished() {
        assertFalse(game.isLevelFinished());
    }

    @Test
    void playerIsInitiallyAlive() {
        assertTrue(game.isPlayerAlive());
    }

    @Test
    void playerCanFireBullets() {
        game.updateGame();
        assertFalse(game.getBullets().isEmpty());
    }

    @Test
    void playerCanMove() {
        double initialX = game.getPlayer().getX();
        double initialY = game.getPlayer().getY();
        game.updateGame();
        assertNotEquals(initialX, game.getPlayer().getX());
        assertNotEquals(initialY, game.getPlayer().getY());
    }

    @Test
    void playerCanRotate() {
        double initialRotation = game.getPlayer().getRotation();
        game.updateGame();
        assertNotEquals(initialRotation, game.getPlayer().getRotation());
    }

    @Test
    void playerCanStartNewGame() {
        game.startNewGame();
        assertEquals(0, game.getPlayerScore());
        assertEquals(3, game.getLives());
        assertTrue(game.isPaused());
        assertFalse(game.isGameOver());
        assertFalse(game.isLevelFinished());
        assertTrue(game.isPlayerAlive());
    }
}
