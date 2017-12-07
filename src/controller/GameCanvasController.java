package controller;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

import javafx.scene.input.KeyCode;
import logic.GameMap;
import model.BuffHero;
import model.Bullet;
import model.Hero;
import model.Movable;
import model.NormalBullet;
import model.NormalHero;
import view.GameCanvas;

public class GameCanvasController {

	GameCanvas gameCanvas;

	private Set<KeyCode> pressedKey;
	private static final Set<KeyCode> buffActivateKey;
	
	static {
		buffActivateKey = new TreeSet<>();
		buffActivateKey.add(KeyCode.Q);
		buffActivateKey.add(KeyCode.W);
		buffActivateKey.add(KeyCode.E);
		buffActivateKey.add(KeyCode.R);
	}

	public GameCanvasController(GameCanvas gameCanvas) {
		this.gameCanvas = gameCanvas;
		this.pressedKey = new ConcurrentSkipListSet<>();
	}

	public void listenEvent() {
//		Hero hero = (Hero) GameMap.getRenderObjects()
//			.parallelStream()
//			.filter(x -> x instanceof Hero)
//			.findAny()
//			.get();
		gameCanvas.setOnKeyPressed(event -> {
			KeyCode code = event.getCode();
			if (!pressedKey.contains(code)) {
				if (code == KeyCode.LEFT) {
					GameMap.getRenderObjects().parallelStream().filter(x -> x instanceof Hero)
							.forEach(x -> ((Movable) x).setVx(-10));
				}
				if (code == KeyCode.UP) {
					GameMap.getRenderObjects().parallelStream().filter(x -> x instanceof Hero).forEach(x -> {
//						((Movable) x).setVy(-20);
//						((Hero) x).setOnAir(true);
						((Hero) x).jump();
					});
				}
				if (code == KeyCode.RIGHT) {
					GameMap.getRenderObjects().parallelStream().filter(x -> x instanceof Hero)
							.forEach(x -> ((Movable) x).setVx(10));
				}
				if (code == KeyCode.Z) {
					Hero hero = GameMap.getRenderObjects().parallelStream().filter(x -> x instanceof Hero).map(x -> (Hero) x)
							.findAny().get();
					hero.fire(1);
				}
				if (code == KeyCode.X) {
					Hero hero = GameMap.getRenderObjects().parallelStream().filter(x -> x instanceof Hero).map(x -> (Hero) x)
							.findAny().get();
					hero.fire(2);
				}
				if (code == KeyCode.C) {
					Hero hero = GameMap.getRenderObjects().parallelStream().filter(x -> x instanceof Hero).map(x -> (Hero) x)
							.findAny().get();
					hero.fire(3);
				}
				pressedKey.add(code);
				if (pressedKey.containsAll(buffActivateKey)) {
					Hero hero = GameMap.getRenderObjects().parallelStream().filter(x -> x instanceof Hero).map(x -> (Hero) x)
							.findAny().get();
					if (hero instanceof NormalHero) {
						GameMap.addEntity(new BuffHero(hero.getPosition().first, hero.getPosition().second, hero.getVx(), hero.getVy(), hero.getDirection()));
						GameMap.removeEntity(hero);
					} else {
						GameMap.addEntity(new NormalHero(hero.getPosition().first, hero.getPosition().second, hero.getVx(), hero.getVy(), hero.getDirection()));
						GameMap.removeEntity(hero);
					}
				}
			}
		});

		gameCanvas.setOnKeyReleased(event -> {
			KeyCode code = event.getCode();
			if (code.isArrowKey()) {
				if (code == KeyCode.LEFT || code == KeyCode.RIGHT) {
					GameMap.getRenderObjects().parallelStream().filter(x -> x instanceof Hero)
							.forEach(x -> ((Movable) x).setVx(0));
				}
			}
			pressedKey.remove(code);
		});
	}

}
