package view;

import java.net.URI;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import exception.MapObjectNotFoundException;
import javafx.scene.Group;
import logic.BotManager;
import logic.GameMap;
import logic.HeroStatusDrawer;
import logic.MoveManager;
import main.App;
import model.Entity;
import model.GameBackground;
import model.Renderable;
import model.SpecialAbilityUser;

public class GamePane extends Group {
	
	private GameCanvas gameCanvas;
	private GameMap gameMap;
	private Thread gameLoop;
	private boolean isGameRunning;
	private Runnable gameRun;
	private HeroStatusDrawer heroStatusDrawer;
	private BotManager botManager;

	public GamePane() {
		super();
		gameCanvas = new GameCanvas();
		gameMap = new GameMap();
		botManager = new BotManager();
		getChildren().add(gameCanvas);
		isGameRunning = false;
		heroStatusDrawer = new HeroStatusDrawer();
		
		gameRun = () ->  {
			for (;isGameRunning ;) {
				gameLoopCallback();
				try {
					Thread.sleep(17);
				} catch (Exception except) {
					System.out.println("Sleep Thread Fault!!!");
					except.printStackTrace();
				}
			}
		};
		
	}
	
	private void gameLoopCallback() {
		GameMap.getRenderObjects()//.stream()
//			.filter(x -> {
//				if (x instanceof Entity) {
//					Entity e = (Entity) x;
//					if (gameCanvas.getStartX() <= e.getPosition().first && e.getPosition().first <= gameCanvas.getStartX() + App.SCREEN_WIDTH ||
//							gameCanvas.getStartX() <= e.getPosition().first + e.getWidth() && e.getPosition().first + e.getWidth() <= gameCanvas.getStartX() + App.SCREEN_WIDTH) {
//						return true;
//					}
//					return false;
//				}
//				return true;
//			})
			.forEach(e -> {
			e.draw(gameCanvas.getGraphicsContext2D());
		});
		GameMap.getEntityObjects().forEach(e -> {
		if (e instanceof SpecialAbilityUser) {
			((SpecialAbilityUser) e).increaseMp(0.02);
		}
		});
		heroStatusDrawer.draw(gameCanvas.getGraphicsContext2D());
		botManager.handleCall();
	}
	
	public void startGameLoop(int map) {
		isGameRunning = true;
		gameLoop = new Thread(gameRun);
		gameLoop.start();
		gameMap.initialize();
		gameMap.loadMap(map);
	}
	
	public void startGameLoop(URI uri) throws MapObjectNotFoundException {
		isGameRunning = true;
		gameLoop = new Thread(gameRun);
		gameLoop.start();
		gameMap.initialize();
		gameMap.loadMap(uri);
	}
	
	public void stopGameLoop() {
		gameMap.stop();
		isGameRunning = false;
	}
	
	public GameCanvas getGameCanvas() {
		return gameCanvas;
	}
}
