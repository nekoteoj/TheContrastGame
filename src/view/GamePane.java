package view;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.Group;
import logic.GameMap;
import model.GameBackground;
import model.Renderable;

public class GamePane extends Group {
	
	private GameCanvas gameCanvas;
	private GameMap gameMap;
	private Thread gameLoop;
	
	public GameCanvas getGameCanvas() {
		return gameCanvas;
	}

	public GamePane() {
		super();
		gameCanvas = new GameCanvas();
		gameMap = new GameMap();
		getChildren().add(gameCanvas);
		
		gameMap.initialize();
		
		gameLoop = new Thread(() ->  {
			for (;;) {
				gameLoopCallback();
				try {
					Thread.sleep(17);
				} catch (Exception except) {
					System.out.println("Sleep Thread Fault!!!");
					except.printStackTrace();
				}
			}
		});
		
	}
	
	private void gameLoopCallback() {
		GameMap.getRenderObjects().forEach(e -> e.draw(gameCanvas.getGraphicsContext2D()));
	}
	
	public void startGameLoop() {
		gameLoop.start();
	}
	
	public void stopGameLoop() {
		gameLoop.interrupt();
	}
	
}
