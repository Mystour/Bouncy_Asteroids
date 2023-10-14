package si.display;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import si.model.*;
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
        double x = p.getX();
        double y = p.getY();
        double radius = p.getRotation();

        // Draw the ship
        int[] x_coords = new int[]{0, -1, -1, -2, -2, -3, -7, -7, -3, -2, -4, -4, -2, -1, 1, 2, 4, 4, 2, 3, 7, 7, 3, 2, 2, 1, 1, 0};
        int[] y_coords = new int[]{-6, -5, -3, -2, 0, 1, 2, 4, 4, 5, 6, 7, 7, 6, 6, 7, 7, 6, 5, 4, 4, 2, 1, 0, -2, -3, -5, -6};

        double[] x_adjusted = new double[x_coords.length];
        double[] y_adjusted = new double[y_coords.length];
        for (int i = 0; i < x_coords.length; i++) {
            x_adjusted[i] = x + (x_coords[i] * Math.cos(radius) - y_coords[i] * Math.sin(radius)) * Player.SHIP_SCALE;
            y_adjusted[i] = y + (x_coords[i] * Math.sin(radius) + y_coords[i] * Math.cos(radius)) * Player.SHIP_SCALE;
        }
        gc.setFill(Color.GREEN);
        gc.fillPolygon(x_adjusted, y_adjusted, x_adjusted.length);
    }

    private void drawShape(GraphicsContext gc, Bullet b) {
        double x = b.getX();
        double y = b.getY();
        double radius = b.getRotation();
        // Draw the bullet
        int[] x_coords = new int[]{-Bullet.BULLET_WIDTH/2, Bullet.BULLET_WIDTH/2, Bullet.BULLET_WIDTH/2, -Bullet.BULLET_WIDTH/2};
        int[] y_coords = new int[]{-Bullet.BULLET_HEIGHT/2, -Bullet.BULLET_HEIGHT/2, Bullet.BULLET_HEIGHT/2, Bullet.BULLET_HEIGHT/2};

        double[] x_adjusted = new double[4];
        double[] y_adjusted = new double[4];
        for (int i = 0; i < 4; i++) {
            x_adjusted[i] = x + (x_coords[i] * Math.cos(radius) - y_coords[i] * Math.sin(radius)) * Player.SHIP_SCALE/2;
            y_adjusted[i] = y + (x_coords[i] * Math.sin(radius) + y_coords[i] * Math.cos(radius)) * Player.SHIP_SCALE/2;
        }

        gc.setFill(Color.GREEN);
        gc.fillPolygon(x_adjusted, y_adjusted, x_adjusted.length);
    }

    private void drawShape(GraphicsContext gc, Asteroids at) {
        PlanetType type = at.getType();
        drawAsteroid(gc, at, type.getRadius());
    }

    public void drawAsteroid(GraphicsContext gc, Asteroids at, int size) {
        double x = at.getX();  // The x coordinate of our asteroid
        double y = at.getY();  // The y coordinate of our asteroid

        gc.setFill(Color.GRAY);  // Choose a color for the asteroid
        gc.fillOval(x, y, size, size);  // draw a circle at (x, y) with a diameter the same as our size.
    }

    private void drawShape(GraphicsContext gc, EnemyShip es) {
        if (es.getType() == AlienType.A) {
            drawEnemyA(gc, es);
        } else {
            drawEnemyB(gc, es);
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
            gc.fillText("Level: " + game.getLevel(), 0, 30);
            gc.setTextAlign(TextAlignment.RIGHT);
            gc.fillText("Score: " + game.getPlayerScore(), BouncyAsteroidsGame.SCREEN_WIDTH, 0);
            drawShape(gc, game.getShip());
            for (Bullet bullet : game.getBullets()) {
                drawShape(gc, bullet);
            }
            for (Asteroids asteroid : game.getAsteroids()) {
                drawShape(gc, asteroid);
            }
            for (EnemyShip s : game.getEnemyShips()) {
                drawShape(gc, s);
            }
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
