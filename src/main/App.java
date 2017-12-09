package main;

import controller.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import view.ViewManager;

public class App extends Application {

	public static int SCREEN_WIDTH = 800;
	public static int SCREEN_HEIGHT = 600;

	private ViewManager viewManager;
	private MainController mainController;

	@Override
	public void start(Stage primaryStage) throws Exception {

		viewManager = new ViewManager(primaryStage);

		mainController = new MainController();
		mainController.on();

		primaryStage.setTitle("The Contrast Game");
		primaryStage.show();

		primaryStage.setOnCloseRequest(event -> {
			Platform.exit();
			System.exit(0);
		});

		viewManager.goTo("menu");
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
