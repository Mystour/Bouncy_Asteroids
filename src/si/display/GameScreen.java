package si.display;

import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import si.model.BouncyAsteroidsGame;
import si.model.Bullet;
import si.model.Player;
import ucd.comp2011j.engine.Screen;

public class GameScreen implements Screen {
    private static final long serialVersionUID = -8282302849760730222L;
    private BouncyAsteroidsGame game;
    private Canvas canvas;

    public Canvas getCanvas(){return canvas;}

    public GameScreen(BouncyAsteroidsGame game) {
        this.game = game;
        this.canvas = new Canvas(BouncyAsteroidsGame.SCREEN_WIDTH, BouncyAsteroidsGame.SCREEN_HEIGHT);
    }

    private void drawShape(GraphicsContext gc, Player p) {
        int x = p.getX();
        int y = p.getY();

        // Draw the ship
        int[] x_coords = new int[]{7, 6, 6, 5, 5, 4, 0, 0, 4, 5, 3, 3, 5, 6, 8, 9, 11, 11, 9, 10, 14, 14, 10, 9, 9, 8, 8, 7};
        int[] y_coords = new int[]{0, 1, 3, 4, 6, 7, 8, 10, 10, 11, 12, 13, 13, 12, 12, 13, 13, 12, 11, 10, 10, 8, 7, 6, 4, 3, 1, 0};

        double[] x_adjusted = new double[x_coords.length];
        double[] y_adjusted = new double[y_coords.length];
        for (int i = 0; i < x_coords.length; i++) {
            x_adjusted[i] = x + x_coords[i] * Player.SHIP_SCALE;
            y_adjusted[i] = y + y_coords[i] * Player.SHIP_SCALE;
        }
        gc.setFill(Color.GREEN);
        gc.fillPolygon(x_adjusted, y_adjusted, x_adjusted.length);
    }

    private void drawShape(GraphicsContext gc, Bullet b) {
        gc.setFill(Color.GREEN);
        gc.fillRect(b.getX(), b.getY(), b.BULLET_WIDTH, b.BULLET_HEIGHT);
    }



    public void paint() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, BouncyAsteroidsGame.SCREEN_WIDTH, BouncyAsteroidsGame.SCREEN_HEIGHT);
        if (game != null) {
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, BouncyAsteroidsGame.SCREEN_WIDTH, BouncyAsteroidsGame.SCREEN_HEIGHT);
            gc.setFill(Color.GREEN);
            gc.setTextAlign(TextAlignment.LEFT);
            gc.setTextBaseline(VPos.TOP);
            gc.setFont(new Font("Arial", 24));
            gc.fillText("Lives: " + game.getLives(), 0, 0);
            gc.setTextAlign(TextAlignment.RIGHT);
            gc.fillText("Score: " + game.getPlayerScore(), BouncyAsteroidsGame.SCREEN_WIDTH, 0);
            drawShape(gc, game.getShip());
//            for (Bullet bullet : game.getBullets()) {
//                drawShape(gc, bullet);
//            }
            if ((game.isPaused() || !game.isPlayerAlive()) && game.getLives() > 0) {
                gc.setTextAlign(TextAlignment.CENTER);
                gc.setTextBaseline(VPos.CENTER);
                gc.setFont(new Font("Arial", 24));
                gc.setFill(Color.GREEN);
                gc.fillText("Press 'p' to continue ", BouncyAsteroidsGame.SCREEN_WIDTH/2, BouncyAsteroidsGame.SCREEN_HEIGHT/2);

            } else if (!game.isPlayerAlive() && game.getLives() == 0) {
                gc.setTextAlign(TextAlignment.CENTER);
                gc.setTextBaseline(VPos.CENTER);
                gc.setFont(new Font("Arial", 48));
                gc.setFill(Color.GREEN);gc.fillText("Game over ", BouncyAsteroidsGame.SCREEN_WIDTH/2, BouncyAsteroidsGame.SCREEN_HEIGHT/2);
            }
        }
    }
}
