package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import si.display.PlayerListener;
import si.model.BouncyAsteroidsGame;

import static org.junit.jupiter.api.Assertions.*;

class MockPlayerListener extends PlayerListener {
    private boolean pressingFire = false;
    private boolean pressingUp = false;

    @Override
    public boolean isPressingFire() {
        return pressingFire;
    }

    public void setPressingFire(boolean pressingFire) {
        this.pressingFire = pressingFire;
    }

    @Override
    public boolean isPressingUp() {
        return pressingUp;
    }

    public void setPressingUp(boolean pressingUp) {
        this.pressingUp = pressingUp;
    }
}

class BouncyAsteroidsGameTest {
    private BouncyAsteroidsGame game;

    @BeforeEach
    void setUp() {
        new javafx.embed.swing.JFXPanel(); // This line will initialize JavaFX toolkit
        MockPlayerListener mockListener = new MockPlayerListener();
        game = new BouncyAsteroidsGame(mockListener);
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
    void playerCanRotate() {
        double initialRotation = game.getPlayer().getRotation();
        game.getPlayer().rotate(Math.PI/4);  // Rotate the player by 45 degrees
        assertNotEquals(initialRotation, game.getPlayer().getRotation(), "Player rotation should change after rotate method is called");
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
