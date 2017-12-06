package model.mainmenu;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import controller.MainController;
import exception.MapObjectNotFoundException;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.AudioClip;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.utility.ClassResourceUtility;
import view.GamePane;
import view.MainMenuPane;
import view.ViewManager;

public class Menu {
	
//	private List<String> mainList = Arrays.asList(
//			"Start Game", "Scoreboard", "Exit"
//	);
//	
//	private List<String> startList = {
//			"Map 1", "Load Custom Map", "Back"
//	};
	
	private static AudioClip clickSound;
	private static AudioClip readySound;
	
	static {
		clickSound = new AudioClip(ClassResourceUtility.getResourcePath("sound/clicksound.mp3"));
		readySound = new AudioClip(ClassResourceUtility.getResourcePath("sound/readysound.mp3"));
	}
	
	private List<String> mainList = new CopyOnWriteArrayList<>();
	private List<String> startList = new CopyOnWriteArrayList<>();
	
	{
		mainList.add("Start Game");
		mainList.add("Scoreboard");
		mainList.add("Exit");
		
		startList.add("Map 1");
		startList.add("Load Custom Map");
		startList.add("Back");
	}
	
	// state 0 when mainList 1 when startList
	private int state = 0;
	private int selected = 0;
	
	public Menu() {
		
	}
	
	public List<String> getItemList() {
		if (state == 1) {
			return startList;
		}
		return mainList;
	}
	
	public int getSelectedIndex() {
		return selected;
	}
	
	public void shiftSelected(int shift) {
		clickSound.play();
		selected += shift;
		if (state == 0) {
			selected = (selected + mainList.size()) % mainList.size();
		} else if (state == 1) {
			selected = (selected + startList.size()) % startList.size();
		}
	}
	
	public void goToStartList() {
		state = 1;
		selected = 0;
	}
	
	public void goToMainList() {
		state = 0;
		selected = 0;
	}
	
	public void enter() {
		if (state == 0) {
			clickSound.play();
			if (selected == 0) {
				goToStartList();
			} else if (selected == 1) {
				
			} else if (selected == 2) {
				Platform.runLater(() -> {					
					Platform.exit();
					System.exit(0);
				});
			}
		} else if (state == 1) {
			MainMenuPane mainMenuPane = (MainMenuPane) ViewManager.getInstance().getPane("menu");
			GamePane gamePane = (GamePane) ViewManager.getInstance().getPane("game");
			if (selected == 0) {
				mainMenuPane.stopMainMenuLoop();
				readySound.play();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ViewManager.getInstance().goTo("game");
				gamePane.startGameLoop(1);
			} else if (selected == 1) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Map file");
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Map Files", "*.dat", "*.txt"));
				File selectedFile = fileChooser.showOpenDialog(ViewManager.getInstance().getPrimaryStage());
				if (selectedFile != null) {
					mainMenuPane.stopMainMenuLoop();
					readySound.play();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ViewManager.getInstance().goTo("game");
					try {
						gamePane.startGameLoop(selectedFile.toURI());
					} catch (Exception e) {
						gamePane.stopGameLoop();
						ViewManager.getInstance().goTo("menu");
						Alert alert = new Alert(AlertType.ERROR, "Invalid Map Format", ButtonType.OK);
						alert.showAndWait();	
						Platform.runLater(() -> ((MainMenuPane) ViewManager.getInstance().getPane("menu")).getMainMenuCanvas().requestFocus());
						//e.printStackTrace();
					}
				}
			} else if (selected == 2) {
				clickSound.play();
				goToMainList();
			}
		}
	}
	
}
