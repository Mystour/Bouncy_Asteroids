package si.display;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import si.model.SpaceInvadersGame;
import ucd.comp2011j.engine.Screen;

public class AboutScreen implements Screen {
    private static final long serialVersionUID = -1264717778772722118L;
    private boolean menu = false;
    private Canvas canvas;
    private PlayerListener listener;

    public AboutScreen() {
        canvas = new Canvas(SpaceInvadersGame.SCREEN_WIDTH, SpaceInvadersGame.SCREEN_HEIGHT);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void paint() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, SpaceInvadersGame.SCREEN_WIDTH, SpaceInvadersGame.SCREEN_HEIGHT);
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, SpaceInvadersGame.SCREEN_WIDTH, SpaceInvadersGame.SCREEN_HEIGHT);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(new Font("Arial", 28));
        gc.setFill(Color.GREEN);
        gc.fillText("Space Invader Controls", SpaceInvadersGame.SCREEN_WIDTH / 2, 64);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(new Font("Arial", 20));
        int start = 128;
        int gap = 48;
        gc.fillText("Move Left", 1 * SpaceInvadersGame.SCREEN_WIDTH / 6, start + 0 * gap);
        gc.fillText("left arrow", 4 * SpaceInvadersGame.SCREEN_WIDTH / 6, start + 0 * gap);
        gc.fillText("Move Right", 1 * SpaceInvadersGame.SCREEN_WIDTH / 6, start + 1 * gap);
        gc.fillText("right arrow", 4 * SpaceInvadersGame.SCREEN_WIDTH / 6, start + 1 * gap);
        gc.fillText("Fire", 1 * SpaceInvadersGame.SCREEN_WIDTH / 6, start + 2 * gap);
        gc.fillText("space bar", 4 * SpaceInvadersGame.SCREEN_WIDTH / 6, start + 2 * gap);
        gc.fillText("Play/Pause", 1 * SpaceInvadersGame.SCREEN_WIDTH / 6, start + 3 * gap);
        gc.fillText("p", 4 * SpaceInvadersGame.SCREEN_WIDTH / 6, start + 3 * gap);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(new Font("Arial", 28));
        gc.fillText("Press 'M' to return to the Main Menu", SpaceInvadersGame.SCREEN_WIDTH / 2, 416);
    }

    private double calcCenterX(String t, int size) {
        Font font = new Font("Arial", size);
        Text internal = new Text(t);
        internal.setFont(font);
        double textWidth = internal.getLayoutBounds().getWidth();
        return SpaceInvadersGame.SCREEN_WIDTH / 2 - textWidth;
    }
}
