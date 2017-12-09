package view;

import java.net.URI;

import exception.MapObjectNotFoundException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import logic.GameMap;
import logic.HeroStatusDrawer;
import model.SpecialAbilityUser;
import model.utility.ScoreIO;

public class GamePane extends Group {

	private GameCanvas gameCanvas;
	private GameMap gameMap;
	private Timeline gameLoop;
	private KeyFrame gameKeyFrame;
	private HeroStatusDrawer heroStatusDrawer;
	private long startTime;
	private boolean isCustomMap;

	public GamePane() {
		super();
		gameCanvas = new GameCanvas();
		gameMap = new GameMap();
		getChildren().add(gameCanvas);
		heroStatusDrawer = new HeroStatusDrawer();

		gameKeyFrame = new KeyFrame(Duration.millis(17), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ac) {
				gameLoopCallback();
			}
		});
	}

	private void gameLoopCallback() {
		GameMap.getRenderObjects().forEach(e -> {
			e.draw(gameCanvas.getGraphicsContext2D());
		});
		GameMap.getEntityObjects().forEach(e -> {
			if (e instanceof SpecialAbilityUser) {
				((SpecialAbilityUser) e).increaseMp(0.02);
			}
		});
		heroStatusDrawer.draw(gameCanvas.getGraphicsContext2D());
	}

	public void startGameLoop(int map) {
		isCustomMap = false;
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		gameLoop.getKeyFrames().add(gameKeyFrame);
		gameLoop.play();
		gameMap.initialize();
		gameMap.loadMap(map);
		startTime = System.nanoTime();
	}

	public void startGameLoop(URI uri) throws MapObjectNotFoundException {
		isCustomMap = true;
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		gameLoop.getKeyFrames().add(gameKeyFrame);
		gameLoop.play();
		gameMap.initialize();
		gameMap.loadMap(uri);
	}

	public void stopGameLoop() {
		gameMap.stop();
		gameLoop.stop();
	}

	public GameCanvas getGameCanvas() {
		return gameCanvas;
	}

	public long getStartTime() {
		return startTime;
	}

	public boolean isCustomMap() {
		return isCustomMap;
	}

	public void endGame(boolean isHeroWin) {
		stopGameLoop();
		if (isHeroWin) {
			gameCanvas.drawWinScreen();
			showWinDialog();
		} else {
			gameCanvas.drawLoseScreen();
			showLoseDialog();
		}
	}

	public void showWinDialog() {
		Platform.runLater(() -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("CONGRATULATION");
			alert.setHeaderText("YOU WIN");
			alert.setContentText("Good job. Have a nice day!");
			alert.showAndWait();
			if (!((GamePane) ViewManager.getInstance().getPane("game")).isCustomMap()) {
				ScoreIO.getInstance().addNewScore(System.nanoTime());
			}
			ViewManager.getInstance().goTo("menu");
		});
	}

	public void showLoseDialog() {
		Platform.runLater(() -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("GAME OVER");
			alert.setHeaderText("Game Over");
			alert.setContentText("We are sorry. You are dead.");
			alert.showAndWait();
			ViewManager.getInstance().goTo("menu");
		});
	}
}
