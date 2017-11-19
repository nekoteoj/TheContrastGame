package controller;

import view.GamePane;
import view.ViewManager;

public class MainController {
	
	ViewManager viewManager;
	GameCanvasController gameCanvasController;
	
	public MainController() {
		viewManager = ViewManager.getInstance();
		gameCanvasController = new GameCanvasController(((GamePane) viewManager.getPane("game")).getGameCanvas());
	}
	
	public void on() {
		gameCanvasController.listenEvent();
	}
}
