package si.display;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import si.model.*;
import ucd.comp2011j.engine.Screen;

import java.io.Serial;
import java.io.Serializable;

public class GameScreen implements Screen, Serializable {
    @Serial
    private static final long serialVersionUID = -8282302849760730222L;
    private final BouncyAsteroidsGame game;
    private final Canvas canvas;

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
        double[] x_coords = new double[]{0, -1, -1, -2, -2, -3, -4, -4, -5, -5, -7, -7, -3, -2, -4, -4, -2, -1, 1, 2, 4, 4, 2, 3, 7, 7, 5, 5, 4, 4, 3, 2, 2, 1, 1, 0};
        double[] y_coords = new double[]{-6, -5, -3, -2, 0, 1, 1.25, 0, 0, 1.5, 2, 4, 4, 5, 6, 7, 7, 6, 6, 7, 7, 6, 5, 4, 4, 2, 1.5, 0, 0, 1.25, 1, 0, -2, -3, -5, -6};

        double[] x_adjusted = new double[x_coords.length];
        double[] y_adjusted = new double[y_coords.length];
        for (int i = 0; i < x_coords.length; i++) {
            x_adjusted[i] = x + (x_coords[i] * Math.cos(radius) - y_coords[i] * Math.sin(radius)) * Player.SHIP_SCALE;
            y_adjusted[i] = y + (x_coords[i] * Math.sin(radius) + y_coords[i] * Math.cos(radius)) * Player.SHIP_SCALE;
        }
        gc.setFill(Color.GRAY);
        gc.fillPolygon(x_adjusted, y_adjusted, x_adjusted.length);
    }

    private void drawShape(GraphicsContext gc, Bullet b) {
        double x = b.getX();
        double y = b.getY();
        double radius = b.getRotation() + Math.PI/2;
        // Draw the bullet
        int[] x_coords = new int[]{-Bullet.BULLET_WIDTH/2, Bullet.BULLET_WIDTH/2, Bullet.BULLET_WIDTH/2, -Bullet.BULLET_WIDTH/2};
        int[] y_coords = new int[]{-Bullet.BULLET_HEIGHT/2, -Bullet.BULLET_HEIGHT/2, Bullet.BULLET_HEIGHT/2, Bullet.BULLET_HEIGHT/2};

        double[] x_adjusted = new double[4];
        double[] y_adjusted = new double[4];
        for (int i = 0; i < 4; i++) {
            x_adjusted[i] = x + (x_coords[i] * Math.cos(radius) - y_coords[i] * Math.sin(radius)) * Player.SHIP_SCALE/2;
            y_adjusted[i] = y + (x_coords[i] * Math.sin(radius) + y_coords[i] * Math.cos(radius)) * Player.SHIP_SCALE/2;
        }

        if (b.getType().equals("player")) {
            gc.setFill(Color.GREEN);
            drawFlame(gc, b);
        } else {
            gc.setFill(Color.RED);
        }
        gc.fillPolygon(x_adjusted, y_adjusted, x_adjusted.length);
    }

    private void drawFlame(GraphicsContext gc, Bullet b) {
        double x = b.getX();
        double y = b.getY();
        double radius = b.getRotation() + Math.PI / 2;
        double scale = Player.SHIP_SCALE / 2.0;

        int[] x_coords = new int[]{-Bullet.BULLET_WIDTH, 0, Bullet.BULLET_WIDTH};
        int[] y_coords = new int[]{-Bullet.BULLET_HEIGHT, Bullet.BULLET_HEIGHT, -Bullet.BULLET_HEIGHT};

        double[] x_adjusted = new double[3];
        double[] y_adjusted = new double[3];
        for (int i = 0; i < 3; i++) {
            x_adjusted[i] = x + (x_coords[i] * Math.cos(radius) - y_coords[i] * Math.sin(radius)) * scale;
            y_adjusted[i] = y + (x_coords[i] * Math.sin(radius) + y_coords[i] * Math.cos(radius)) * scale;
        }

        // Create flame gradient
        LinearGradient flameGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.YELLOW),
                new Stop(1.0, Color.RED));
        gc.setFill(flameGradient);
        gc.fillPolygon(x_adjusted, y_adjusted, x_adjusted.length);
    }

    private void drawShape(GraphicsContext gc, Asteroids at) {
        gc.setFill(Color.GRAY);  // Choose a color for the asteroid
        gc.fillPolygon(at.getCoordinatesX(), at.getCoordinatesY(), at.getNumberOfVertices());  // Draw the asteroid

        for (Crater c : at.getCraters()) {
            gc.setFill(c.getColor());
            gc.fillPolygon(c.x_coords_detail(), c.y_coords_detail(), c.numVertices());
        }
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

        // creating eye-holes
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

    private void drawShape(GraphicsContext gc, Props p) {
        double x = p.getX();
        double y = p.getY();
        double radius = Props.getRadius();

        // Draw the props
        gc.setFill(Color.BLUE);
        gc.fillOval(x, y, radius, radius);

        // add "P" to its center
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(new Font("Arial", 24));

        gc.setFill(Color.GREEN);  // Set the font color to green
        gc.fillText("P", x + radius/2, y + radius/2);
    }

    private void drawShape(GraphicsContext gc, int tripleCountdown) {
        double tripleFireRemaining = tripleCountdown / 60.0;

        // Position and dimensions of the progress bar
        double width = 100;  // Width of the progress bar
        double height = 15;  // Height of the progress bar
        double x = BouncyAsteroidsGame.SCREEN_WIDTH / 2.0 - width / 2.0;
        double y = 30;

        // Draw the background of the progress bar
        gc.setFill(Color.GRAY);
        gc.fillRect(x, y, width, height);

        // Draw the foreground of the progress bar
        gc.setFill(Color.GREEN);
        gc.fillRect(x, y, width * (tripleFireRemaining / 10), height);

        // Display the remaining time next to the progress bar
        gc.setFill(Color.RED);
        gc.fillText("Triple Fire", x + width / 2.0, y);
    }

    public void paint() {
        // the sound part
        Sound.stopMenuSound();
        Sound.playGameSound();

        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, BouncyAsteroidsGame.SCREEN_WIDTH, BouncyAsteroidsGame.SCREEN_HEIGHT);
        if (game != null) {
            // Set Background Color
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, BouncyAsteroidsGame.SCREEN_WIDTH, BouncyAsteroidsGame.SCREEN_HEIGHT);

            // Draw stars
            int numOfStars = 100;
            gc.setFill(Color.WHITE);
            java.util.Random rand = new java.util.Random();   // create a random number generator to create random stars
            for (int i = 0; i < numOfStars; i++) {
                double x = rand.nextDouble() * BouncyAsteroidsGame.SCREEN_WIDTH;
                double y = rand.nextDouble() * BouncyAsteroidsGame.SCREEN_HEIGHT;
                double radius = rand.nextDouble() * 2;  // randomly determine the radius of the star,the maximum radius is 2
                gc.fillOval(x, y, radius, radius);
            }

            gc.setFill(Color.GREEN);
            gc.setTextAlign(TextAlignment.LEFT);  // Align the text to the left
            gc.setTextBaseline(VPos.TOP);
            gc.setFont(new Font("Arial", 24));
            gc.fillText("Lives: " + game.getLives(), 0, 0);
            gc.fillText("Level: " + game.getLevel(), 0, 30);

            gc.setTextAlign(TextAlignment.CENTER);
            gc.fillText("Time: " + game.getTime() + "s", BouncyAsteroidsGame.SCREEN_WIDTH/2.0, 0);

            if(game.getPlayer().getTripleFire()){
                 drawShape(gc, game.getPlayer().getTripleCountdown());
            }

            gc.setFill(Color.GREEN);
            gc.setTextAlign(TextAlignment.RIGHT);
            gc.fillText("Score: " + game.getPlayerScore(), BouncyAsteroidsGame.SCREEN_WIDTH, 0);
            if (game.getPlayer().isAlive() && shouldPaint()) {
                drawShape(gc, game.getPlayer());
            }
            for (Bullet bullet : game.getBullets()) {
                drawShape(gc, bullet);
            }
            for (Asteroids asteroid : game.getAsteroids()) {
                drawShape(gc, asteroid);
            }
            for (EnemyShip s : game.getEnemyShips()) {
                drawShape(gc, s);
            }
            for (Props p : game.getProps()) {
                drawShape(gc, p);
            }
            if ((game.isPaused() || !game.isPlayerAlive()) && game.getLives() > 0) {
                gc.setTextAlign(TextAlignment.CENTER);
                gc.setTextBaseline(VPos.CENTER);
                gc.setFont(new Font("Arial", 24));
                gc.setFill(Color.GREEN);
                gc.fillText("Press 'p' to continue ", BouncyAsteroidsGame.SCREEN_WIDTH/2.0, BouncyAsteroidsGame.SCREEN_HEIGHT/2.0);

            } else if (!game.isPlayerAlive() && game.getLives() == 0) {
                gc.setTextAlign(TextAlignment.CENTER);
                gc.setTextBaseline(VPos.CENTER);
                gc.setFont(new Font("Arial", 48));
                gc.setFill(Color.GREEN);
                gc.fillText("Game over ", BouncyAsteroidsGame.SCREEN_WIDTH/2.0, BouncyAsteroidsGame.SCREEN_HEIGHT/2.0);
            }
        }
    }

    private boolean shouldPaint(){
        Player p = game.getPlayer();
        return p.getInvincibilityCountdown() % 8 <= 3;
    }
}
