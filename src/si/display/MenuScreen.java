package si.display;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import si.model.BouncyAsteroidsGame;
import ucd.comp2011j.engine.Screen;

import java.io.Serial;
import java.io.Serializable;

public class MenuScreen implements Screen, Serializable {
    @Serial
    private static final long serialVersionUID = 1616386874546775416L;
    private final Canvas canvas;

    public MenuScreen() {
        canvas = new Canvas(BouncyAsteroidsGame.SCREEN_WIDTH, BouncyAsteroidsGame.SCREEN_HEIGHT);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void paint() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, BouncyAsteroidsGame.SCREEN_WIDTH, BouncyAsteroidsGame.SCREEN_HEIGHT);

        // Set Background Color
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, BouncyAsteroidsGame.SCREEN_WIDTH, BouncyAsteroidsGame.SCREEN_HEIGHT);

        // Text Attributes
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);

        // Greeting
        gc.setFont(new Font("Monospaced", 40));
        gc.setFill(Color.CYAN);
        double OFFSET = BouncyAsteroidsGame.SCREEN_HEIGHT / 6;
        gc.fillText("Welcome to Bouncy Asteroids!!!!", BouncyAsteroidsGame.SCREEN_WIDTH/2, OFFSET);

        // Instructions
        gc.setFont(Font.font("Courier New", FontWeight.BOLD, 25));
        gc.setFill(Color.YELLOW);
        gc.fillText("To play a game press N", BouncyAsteroidsGame.SCREEN_WIDTH/2, OFFSET + 100);
        gc.fillText("To see the controls press A", BouncyAsteroidsGame.SCREEN_WIDTH/2, OFFSET + 200);
        gc.fillText("To see the High scores press H", BouncyAsteroidsGame.SCREEN_WIDTH/2, OFFSET + 300);
        gc.fillText("To exit press X", BouncyAsteroidsGame.SCREEN_WIDTH/2, OFFSET + 400);
    }
}