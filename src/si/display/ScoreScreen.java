package si.display;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import si.model.BouncyAsteroidsGame;
import ucd.comp2011j.engine.Score;
import ucd.comp2011j.engine.ScoreKeeper;
import ucd.comp2011j.engine.Screen;

import java.io.Serial;
import java.io.Serializable;

public class ScoreScreen implements Screen, Serializable {
    @Serial
    private static final long serialVersionUID = 1616386874546775416L;
    private final ScoreKeeper scoreKeeper;
    private final Canvas canvas;

    public ScoreScreen(ScoreKeeper sc) {
        this.scoreKeeper = sc;
        this.canvas = new Canvas(BouncyAsteroidsGame.SCREEN_WIDTH, BouncyAsteroidsGame.SCREEN_HEIGHT);
    }
    public Canvas getCanvas(){
        return canvas;
    }

    public void paint() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0, BouncyAsteroidsGame.SCREEN_WIDTH, BouncyAsteroidsGame.SCREEN_HEIGHT);
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,BouncyAsteroidsGame.SCREEN_WIDTH, BouncyAsteroidsGame.SCREEN_HEIGHT);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(new Font("Arial", 28));
        gc.setFill(Color.GREEN);
        gc.fillText("Bouncy Asteroids Hall of Fame", BouncyAsteroidsGame.SCREEN_WIDTH/2.0, BouncyAsteroidsGame.SCREEN_HEIGHT / 10.0);

        Score[] scores = scoreKeeper.getScores();
        gc.setFont(new Font("Arial", 16));
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setStroke(Color.WHITE);
        for (int i = 0; i < scores.length; i++) {
            Score score = scores[i];
            gc.fillText(score.getName(), 2 * BouncyAsteroidsGame.SCREEN_WIDTH / 6.0, 96 + i * 32);
            gc.fillText("" + score.getScore(), 4 * BouncyAsteroidsGame.SCREEN_WIDTH / 6.0, 96 + i * 32);
            gc.strokeLine(2 * BouncyAsteroidsGame.SCREEN_WIDTH / 6.0, 96 + i * 32 + 16, 4 * BouncyAsteroidsGame.SCREEN_WIDTH / 6.0 + 40, 96 + i * 32 + 16); // add an underline
        }
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(new Font("Arial", 20));
        gc.setFill(Color.YELLOW);
        gc.fillText("Note: Scores are only saved if you", BouncyAsteroidsGame.SCREEN_WIDTH/2.0, BouncyAsteroidsGame.SCREEN_HEIGHT / 20.0 * 17);
        gc.fillText("higher than the lowest score and exit the game normally with 'X'", BouncyAsteroidsGame.SCREEN_WIDTH/2.0, BouncyAsteroidsGame.SCREEN_HEIGHT / 20.0 * 18);
        gc.setFont(new Font("Arial", 28));
        gc.setFill(Color.GREEN);
        gc.fillText("Press 'M' to return to the Main Menu", BouncyAsteroidsGame.SCREEN_WIDTH/2.0, BouncyAsteroidsGame.SCREEN_HEIGHT / 20.0 * 19);
    }
}