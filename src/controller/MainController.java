package controller;

import view.GamePane;
import view.MainMenuPane;
import view.ViewManager;

public class MainController {
	
	ViewManager viewManager;
	GameCanvasController gameCanvasController;
	MainMenuCanvasController mainMenuCanvasController;
	private static MainController currentInstance = null;
	
	public MainController() {
		currentInstance = this;
		viewManager = ViewManager.getInstance();
		gameCanvasController = new GameCanvasController(((GamePane) viewManager.getPane("game")).getGameCanvas());
		mainMenuCanvasController = new MainMenuCanvasController(((MainMenuPane) viewManager.getPane("menu")).getMainMenuCanvas(), ((MainMenuPane) viewManager.getPane("menu")).getMenu());
	}
	
	public void on() {
		gameCanvasController.listenEvent();
		mainMenuCanvasController.listenEvent();
	}

	public GameCanvasController getGameCanvasController() {
		return gameCanvasController;
	}

	public MainMenuCanvasController getMainMenuCanvasController() {
		return mainMenuCanvasController;
	}
	
	public static MainController getCurrentInstance() {
		return currentInstance;
	}
}
