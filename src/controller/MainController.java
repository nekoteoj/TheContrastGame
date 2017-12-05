package controller;

import view.GamePane;
import view.MainMenuPane;
import view.ViewManager;

public class MainController {
	
	ViewManager viewManager;
	GameCanvasController gameCanvasController;
	MainMenuCanvasController mainMenuCanvasController;
	
	public MainController() {
		viewManager = ViewManager.getInstance();
		gameCanvasController = new GameCanvasController(((GamePane) viewManager.getPane("game")).getGameCanvas());
		mainMenuCanvasController = new MainMenuCanvasController(((MainMenuPane) viewManager.getPane("menu")).getMainMenuCanvas(), ((MainMenuPane) viewManager.getPane("menu")).getMenu());
	}
	
	public void on() {
		gameCanvasController.listenEvent();
		mainMenuCanvasController.listenEvent();
	}
}
