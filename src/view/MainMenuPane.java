package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import main.App;
import model.mainmenu.Menu;
import model.utility.ClassResourceUtility;

public class MainMenuPane extends BorderPane {
	
	private static AudioClip sound;
	
	static {
		sound = new AudioClip(ClassResourceUtility.getResourcePath("sound/mainmenusound.mp3"));
	}
	
//	private Button playButton = new Button("Start Game");
	private Canvas mainMenuCanvas;
	private Thread mainMenuLoop;
	private Runnable mainMenuRun;
	private boolean isMainMenuRunning;
	private Menu menu;
	
	public MainMenuPane() {
		super();
		menu = new Menu();
		mainMenuCanvas = new MainMenuCanvas(menu);
		this.setCenter(mainMenuCanvas);	
		
		mainMenuRun = () -> {
			while (isMainMenuRunning) {
				mainMenuLoopCallback();
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		isMainMenuRunning = false;
		
		
//		setStyle("-fx-background-color: DARKGREY");
//		setPrefSize(App.SCREEN_WIDTH, App.SCREEN_HEIGHT);
//		playButton.setPrefSize(300, 100);
//		setCenter(playButton);
//		playButton.setStyle("-fx-background-color: LIMEGREEN");
//		playButton.setOnAction(event -> {
//			ViewManager.getInstance().goTo("game");
//			((GamePane) ViewManager.getInstance().getPane("game")).startGameLoop();
//		});
	}
	
	private void mainMenuLoopCallback() {
		((MainMenuCanvas) mainMenuCanvas).draw();
	}
	
	public void startMainMenuLoop() {
		sound.setCycleCount(AudioClip.INDEFINITE);
		sound.play(0.8);
		isMainMenuRunning = true;
		mainMenuLoop = new Thread(mainMenuRun);
		mainMenuLoop.start();
	}
	
	public void stopMainMenuLoop() {
		isMainMenuRunning = false;
		sound.stop();
	}
	
	public MainMenuCanvas getMainMenuCanvas() {
		return (MainMenuCanvas) mainMenuCanvas;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
}
