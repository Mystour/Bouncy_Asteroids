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

public class ScoreScreen implements Screen {
    private static final long serialVersionUID = 1616386874546775416L;
    private ScoreKeeper scoreKeeper;
    private Canvas canvas;

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
        gc.fillText("Space Invaders Hall of Fame", BouncyAsteroidsGame.SCREEN_WIDTH/2, BouncyAsteroidsGame.SCREEN_HEIGHT / 10);

        Score[] scores = scoreKeeper.getScores();
        gc.setFont(new Font("Arial", 16));
        gc.setTextAlign(TextAlignment.LEFT);
        for (int i = 0; i < scores.length; i++) {
            Score score = scores[i];
            gc.fillText(score.getName(), 2 * BouncyAsteroidsGame.SCREEN_WIDTH / 6, 96 + i * 32);
            gc.fillText("" + score.getScore(), 4 * BouncyAsteroidsGame.SCREEN_WIDTH / 6, 96 + i * 32);
        }
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(new Font("Arial", 28));
        gc.fillText("Press 'M' to return to the Main Menu", BouncyAsteroidsGame.SCREEN_WIDTH/2, BouncyAsteroidsGame.SCREEN_HEIGHT / 10 * 9);
    }
}