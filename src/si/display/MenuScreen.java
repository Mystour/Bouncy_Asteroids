package si.display;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import si.model.BouncyAsteroidsGame;
import ucd.comp2011j.engine.Screen;

public class MenuScreen implements Screen {
    private static final long serialVersionUID = 1616386874546775416L;
    private Canvas canvas;

    public MenuScreen() {
        canvas = new Canvas(BouncyAsteroidsGame.SCREEN_WIDTH, BouncyAsteroidsGame.SCREEN_HEIGHT);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void paint() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, BouncyAsteroidsGame.SCREEN_WIDTH, BouncyAsteroidsGame.SCREEN_HEIGHT);
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, BouncyAsteroidsGame.SCREEN_WIDTH, BouncyAsteroidsGame.SCREEN_HEIGHT);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(new Font("Arial", 36));
        gc.setFill(Color.GREEN);
        gc.fillText("Welcome to Bouncy Asteroids!!!!", BouncyAsteroidsGame.SCREEN_WIDTH/2, BouncyAsteroidsGame.SCREEN_HEIGHT / 32);
        gc.setFont(new Font("Arial", 24));
        gc.fillText("To play a game press N", BouncyAsteroidsGame.SCREEN_WIDTH/2, BouncyAsteroidsGame.SCREEN_HEIGHT / 5);
        gc.fillText("To see the controls press A", BouncyAsteroidsGame.SCREEN_WIDTH/2, 2 * BouncyAsteroidsGame.SCREEN_HEIGHT / 5);
        gc.fillText("To see the High scores press H", BouncyAsteroidsGame.SCREEN_WIDTH/2, 3 * BouncyAsteroidsGame.SCREEN_HEIGHT / 5);
        gc.fillText("To exit press X", BouncyAsteroidsGame.SCREEN_WIDTH/2, 4 * BouncyAsteroidsGame.SCREEN_HEIGHT / 5);
    }
}
