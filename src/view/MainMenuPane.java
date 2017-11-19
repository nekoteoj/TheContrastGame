package view;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import main.App;

public class MainMenuPane extends BorderPane {
	
	private Button playButton = new Button("Start Game");
	
	public MainMenuPane() {
		super();
		setStyle("-fx-background-color: DARKGREY");
		setPrefSize(App.SCREEN_WIDTH, App.SCREEN_HEIGHT);
		playButton.setPrefSize(300, 100);
		setCenter(playButton);
		playButton.setStyle("-fx-background-color: LIMEGREEN");
		playButton.setOnAction(event -> {
			ViewManager.getInstance().goTo("game");
			((GamePane) ViewManager.getInstance().getPane("game")).startGameLoop();
		});
	}
	
}
