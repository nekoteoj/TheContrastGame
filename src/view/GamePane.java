package view;

import java.net.URI;
import exception.MapObjectNotFoundException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.util.Duration;
import logic.GameMap;
import logic.HeroStatusDrawer;
import model.SpecialAbilityUser;

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
		GameMap.getRenderObjects()
			.forEach(e -> {
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
}
