package view;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewManager {
	
	private static ViewManager instance = null;
	
	private Stage primaryStage;
	
	/**
	 * Keys for 2 map below
	 * key "menu" : mainMenu
	 * key "game" : game
	 */
	private ConcurrentMap<String, Scene> sceneMap = new ConcurrentHashMap<>();
	private ConcurrentMap<String, Parent> paneMap = new ConcurrentHashMap<>(); 
	
	public ViewManager(Stage primaryStage) {
		instance = this;
		paneMap.put("game", new GamePane());
		paneMap.put("menu", new MainMenuPane());
		sceneMap.put("menu", new Scene(paneMap.get("menu")));
		sceneMap.put("game", new Scene(paneMap.get("game")));
		this.primaryStage = primaryStage;
	}
	
	/*
	 * @param Page : 1 for MainMenuScene and 2 for GameScene
	 */
	public void goTo(String pageKey) {
		primaryStage.setScene(sceneMap.get(pageKey));
		paneMap.get(pageKey).requestFocus();
		if (paneMap.get(pageKey) instanceof GamePane) {
			((GamePane) paneMap.get(pageKey)).getGameCanvas().requestFocus();
		}
	}
	
	public Scene getScene(String key) {
		return sceneMap.get(key);
	}
	
	public Parent getPane(String key) {
		return paneMap.get(key);
	}
	
	public static ViewManager getInstance() {
		return instance;
	}
	
}
