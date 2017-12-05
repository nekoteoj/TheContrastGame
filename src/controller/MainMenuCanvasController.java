package controller;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import javafx.scene.input.KeyCode;
import model.mainmenu.Menu;
import view.MainMenuCanvas;

public class MainMenuCanvasController {

	private MainMenuCanvas mainMenuCanvas;
	private Menu menu;

	private Set<KeyCode> pressedKey;

	public MainMenuCanvasController(MainMenuCanvas mainMenuCanvas, Menu menu) {
		this.mainMenuCanvas = mainMenuCanvas;
		this.pressedKey = new ConcurrentSkipListSet<>();
		this.menu = menu;
	}

	public void listenEvent() {
		mainMenuCanvas.setOnKeyPressed(event -> {
			KeyCode code = event.getCode();
			if (!pressedKey.contains(code)) {
				if (code == KeyCode.UP) {
					menu.shiftSelected(-1);
				}
				if (code == KeyCode.DOWN) {
					menu.shiftSelected(1);
				}
				if (code == KeyCode.ENTER) {
					menu.enter();
				}
				pressedKey.add(code);
			}
		});

		mainMenuCanvas.setOnKeyReleased(event -> {
			KeyCode code = event.getCode();
			pressedKey.remove(code);
		});
	}

}

