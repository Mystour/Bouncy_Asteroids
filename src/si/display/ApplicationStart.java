package si.display;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import si.model.BouncyAsteroidsGame;

import ucd.comp2011j.engine.GameManager;
import ucd.comp2011j.engine.ScoreKeeper;

public class ApplicationStart extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, BouncyAsteroidsGame.SCREEN_WIDTH, BouncyAsteroidsGame.SCREEN_HEIGHT);
        PlayerListener playerListener = new PlayerListener();
        playerListener.setListeners(scene);
        MenuListener menuListener = new MenuListener();
        menuListener.setListeners(scene);
        primaryStage.setTitle("Bouncy Asteroids");
        BouncyAsteroidsGame game = new BouncyAsteroidsGame(playerListener);
        GameScreen gameScreen = new GameScreen(game);
        MenuScreen menuScreen = new MenuScreen();
        ScoreKeeper scoreKeeper = new ScoreKeeper("scores.txt");
        GameManager mmm = new GameManager(game, root, menuListener, menuScreen,new AboutScreen(),new ScoreScreen(scoreKeeper), gameScreen, scoreKeeper);
        menuScreen.paint();
        primaryStage.setScene(scene);
        primaryStage.show();
        mmm.run();
    }
}