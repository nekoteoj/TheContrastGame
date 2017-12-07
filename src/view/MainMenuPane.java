package view;


import controller.MainController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import model.mainmenu.Menu;
import model.utility.ClassResourceUtility;

public class MainMenuPane extends BorderPane {
	
	private static AudioClip sound;
	
	static {
		sound = new AudioClip(ClassResourceUtility.getResourcePath("sound/mainmenusound.mp3"));
	}
	
	private Canvas mainMenuCanvas;
	private Timeline mainMenuLoop;
	private KeyFrame mainMenuKeyFrame;
	private Menu menu;
	
	public MainMenuPane() {
		super();
		menu = new Menu();
		mainMenuCanvas = new MainMenuCanvas(menu);
		this.setCenter(mainMenuCanvas);	
		
		mainMenuKeyFrame = new KeyFrame(Duration.millis(17), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mainMenuLoopCallback();
			}
		});
		
	}
	
	private void mainMenuLoopCallback() {
		((MainMenuCanvas) mainMenuCanvas).draw();
	}
	
	public void startMainMenuLoop() {
		sound.setCycleCount(AudioClip.INDEFINITE);
		sound.play(0.8);
		MainController.getCurrentInstance().getMainMenuCanvasController().reset();
		mainMenuLoop = new Timeline();
		mainMenuLoop.setCycleCount(Timeline.INDEFINITE);
		mainMenuLoop.getKeyFrames().add(mainMenuKeyFrame);
		mainMenuLoop.play();
	}
	
	public void stopMainMenuLoop() {
		mainMenuLoop.stop();
		MainController.getCurrentInstance().getMainMenuCanvasController().reset();
		sound.stop();
	}
	
	public MainMenuCanvas getMainMenuCanvas() {
		return (MainMenuCanvas) mainMenuCanvas;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
}
