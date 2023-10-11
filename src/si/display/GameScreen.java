package si.display;

import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import si.model.*;
import ucd.comp2011j.engine.Screen;

public class GameScreen implements Screen {
    private static final long serialVersionUID = -8282302849760730222L;
    private SpaceInvadersGame game;
    private Canvas canvas;

    public Canvas getCanvas(){return canvas;}

    public GameScreen(SpaceInvadersGame game) {
        this.game = game;
        this.canvas = new Canvas(SpaceInvadersGame.SCREEN_WIDTH, SpaceInvadersGame.SCREEN_HEIGHT);
    }

    private void drawShape(GraphicsContext gc, Player p) {
        int x = p.getX();
        int y = p.getY();
        int[] x_coords = new int[]{0, 2, 2, 3, 3, 4, 4, 5, 5, 7, 7, 0, 0};
        int[] y_coords = new int[]{2, 2, 1, 1, 0, 0, 1, 1, 2, 2, 4, 4, 2};
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

    private void drawShape(GraphicsContext gc, EnemyShip es) {
        if (es.getType() == AlienType.A) {
            drawEnemyA(gc, es);
        } else if (es.getType() == AlienType.B) {
            drawEnemyB(gc, es);
        } else {
            drawEnemyC(gc, es);
        }
    }

    private void drawEnemyA(GraphicsContext gc, EnemyShip es) {
        int x = es.getX();
        int y = es.getY();
        int[] x_coords = new int[]{0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 8, 8, 7, 7, 6, 6, 5, 5, 4, 4, 3, 3, 2, 2, 1, 1, 0};
        int[] y_coords = new int[]{7, 4, 4, 3, 3, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 3, 3, 4, 4, 7, 7, 5, 5, 7, 7, 6, 6, 7, 7, 6, 6, 7, 7, 5, 5, 7, 7};
        double[] x_adjusted = new double[x_coords.length];
        double[] y_adjusted = new double[y_coords.length];
        for (int i = 0; i < x_coords.length; i++) {
            x_adjusted[i] = x + x_coords[i] * EnemyShip.SHIP_SCALE;
            y_adjusted[i] = y + y_coords[i] * EnemyShip.SHIP_SCALE;
        }
        gc.setFill(Color.GREEN);
        gc.fillPolygon(x_adjusted, y_adjusted, x_adjusted.length);
        gc.fillRect(x + 2 * EnemyShip.SHIP_SCALE, y + EnemyShip.SHIP_SCALE * 0, EnemyShip.SHIP_SCALE, EnemyShip.SHIP_SCALE);
        gc.fillRect(x + 6 * EnemyShip.SHIP_SCALE, y + EnemyShip.SHIP_SCALE * 0, EnemyShip.SHIP_SCALE, EnemyShip.SHIP_SCALE);

        // creating eye holes
        gc.setFill(Color.BLACK);
        gc.fillRect(x + 3 * EnemyShip.SHIP_SCALE, y + EnemyShip.SHIP_SCALE * 3, EnemyShip.SHIP_SCALE, EnemyShip.SHIP_SCALE);
        gc.fillRect(x + 5 * EnemyShip.SHIP_SCALE, y + EnemyShip.SHIP_SCALE * 3, EnemyShip.SHIP_SCALE, EnemyShip.SHIP_SCALE);
    }

    private void drawEnemyB(GraphicsContext gc, EnemyShip es) {
        int x = es.getX();
        int y = es.getY();
        int[] x_coords = new int[]{3, 2, 1, 0, 3, 6, 0, 2, 5, 1, 3, 6, 0, 2, 5, 7};
        int[] y_coords = new int[]{0, 1, 2, 3, 3, 3, 4, 5, 5, 6, 6, 6, 7, 7, 7, 7};
        int[] widths = new int[]{2, 4, 6, 2, 2, 2, 8, 1, 1, 1, 2, 1, 1, 1, 1, 1};
        int[] heights = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        gc.setFill(Color.GREEN);
        for (int i = 0; i < x_coords.length; i++) {
            gc.fillRect(x + x_coords[i] * EnemyShip.SHIP_SCALE, y + EnemyShip.SHIP_SCALE * y_coords[i], EnemyShip.SHIP_SCALE * widths[i], EnemyShip.SHIP_SCALE * heights[i]);
        }
    }

    private void drawEnemyC(GraphicsContext gc, EnemyShip es) {
        int x = es.getX();
        int y = es.getY();
        int[] x_coords = new int[]{3, 7, 7, 9, 9, 10, 10, 8, 8, 9, 9, 10, 10, 8, 8, 7, 7, 6, 6, 4, 4, 3, 3, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, 1, 1, 3, 3};
        int[] y_coords = new int[]{0, 0, 1, 1, 2, 2, 5, 5, 6, 6, 7, 7, 8, 8, 7, 7, 6, 6, 7, 7, 6, 6, 7, 7, 8, 8, 7, 7, 6, 6, 5, 5, 2, 2, 1, 1, 0};
        double[] x_adjusted = new double[x_coords.length];
        double[] y_adjusted = new double[y_coords.length];
        for (int i = 0; i < x_coords.length; i++) {
            x_adjusted[i] = x + x_coords[i] * EnemyShip.SHIP_SCALE;
            y_adjusted[i] = y + y_coords[i] * EnemyShip.SHIP_SCALE;
        }
        gc.setFill(Color.GREEN);
        gc.fillPolygon(x_adjusted, y_adjusted, x_adjusted.length);

        // creating holes
        gc.setFill(Color.BLACK);
        gc.fillRect(x + 2 * EnemyShip.SHIP_SCALE, y + EnemyShip.SHIP_SCALE * 2, EnemyShip.SHIP_SCALE * 2, EnemyShip.SHIP_SCALE * 1);
        gc.fillRect(x + 6 * EnemyShip.SHIP_SCALE, y + EnemyShip.SHIP_SCALE * 2, EnemyShip.SHIP_SCALE * 2, EnemyShip.SHIP_SCALE * 1);
        gc.fillRect(x + 4 * EnemyShip.SHIP_SCALE, y + EnemyShip.SHIP_SCALE * 5, EnemyShip.SHIP_SCALE * 2, EnemyShip.SHIP_SCALE * 1);
    }

    public void paint() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, SpaceInvadersGame.SCREEN_WIDTH, SpaceInvadersGame.SCREEN_HEIGHT);
        if (game != null) {
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, SpaceInvadersGame.SCREEN_WIDTH, SpaceInvadersGame.SCREEN_HEIGHT);
            gc.setFill(Color.GREEN);
            gc.setTextAlign(TextAlignment.LEFT);
            gc.setTextBaseline(VPos.TOP);
            gc.setFont(new Font("Arial", 24));
            gc.fillText("Lives: " + game.getLives(), 0, 0);
            gc.setTextAlign(TextAlignment.RIGHT);
            gc.fillText("Score: " + game.getPlayerScore(), SpaceInvadersGame.SCREEN_WIDTH, 0);
            drawShape(gc, game.getShip());
            for (Bullet bullet : game.getBullets()) {
                drawShape(gc, bullet);
            }
            for (EnemyShip s : game.getEnemyShips()) {
                drawShape(gc, s);
            }
            for (Rectangle2D b: game.getBunkers()){
                gc.setFill(Color.GREEN);
                gc.fillRect(b.getMinX(), b.getMinY(), b.getWidth(), b.getHeight());
            }
            if ((game.isPaused() || !game.isPlayerAlive()) && game.getLives() > 0) {
                gc.setTextAlign(TextAlignment.CENTER);
                gc.setTextBaseline(VPos.CENTER);
                gc.setFont(new Font("Arial", 24));
                gc.setFill(Color.GREEN);
                gc.fillText("Press 'p' to continue ", SpaceInvadersGame.SCREEN_WIDTH/2, SpaceInvadersGame.SCREEN_HEIGHT/2);

            } else if (!game.isPlayerAlive() && game.getLives() == 0) {
                gc.setTextAlign(TextAlignment.CENTER);
                gc.setTextBaseline(VPos.CENTER);
                gc.setFont(new Font("Arial", 48));
                gc.setFill(Color.GREEN);gc.fillText("Game over ", SpaceInvadersGame.SCREEN_WIDTH/2, SpaceInvadersGame.SCREEN_HEIGHT/2);
            }
        }
    }
}
