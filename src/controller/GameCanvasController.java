package controller;

import javafx.scene.input.KeyCode;
import logic.GameMap;
import model.Hero;
import model.Movable;
import view.GameCanvas;

public class GameCanvasController {
	
	GameCanvas gameCanvas;
	
	public GameCanvasController(GameCanvas gameCanvas) {
		this.gameCanvas = gameCanvas;
	}
	
	public void listenEvent() {
		gameCanvas.setOnKeyPressed(event -> {
			KeyCode code = event.getCode();
			if (code.isArrowKey()) {
				if (code == KeyCode.LEFT) {
					GameMap.getRenderObjects().parallelStream()
						.filter(x -> x instanceof Hero)
						.forEach(x -> ((Movable) x).setVx(-10));
				} else if (code == KeyCode.UP) {
					GameMap.getRenderObjects().parallelStream()
						.filter(x -> x instanceof Hero)
						.forEach(x -> {
							((Movable) x).setVy(-20);
							((Hero) x).setOnAir(true);
						});
				} else if (code == KeyCode.RIGHT) {
					GameMap.getRenderObjects().parallelStream()
						.filter(x -> x instanceof Hero)
						.forEach(x -> ((Movable) x).setVx(10));
				}
			}
		});
		
		gameCanvas.setOnKeyReleased(event -> {
			KeyCode code = event.getCode();
			if (code.isArrowKey()) {
				if (code == KeyCode.LEFT || code == KeyCode.RIGHT) {
					GameMap.getRenderObjects().parallelStream()
						.filter(x -> x instanceof Hero)
						.forEach(x -> ((Movable) x).setVx(0));
				}
			}
		});
	}
	
}
