package controller;

import javafx.scene.input.KeyCode;
import logic.GameMap;
import model.Bullet;
import model.Hero;
import model.Movable;
import model.NormalBullet;
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
			} else if (code.isWhitespaceKey()) {
				 if (code == KeyCode.SPACE) {
						Hero hero = GameMap.getRenderObjects().parallelStream()
								.filter(x -> x instanceof Hero)
								.map(x -> (Hero) x)
								.findAny()
								.get();
						Bullet bullet = new NormalBullet(hero.getPosition().first + hero.getWidth(), hero.getPosition().second + hero.getHeight() / 2, 0, hero.getDirection());
						GameMap.addEntity(bullet);
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
