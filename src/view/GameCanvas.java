package view;

import javafx.scene.canvas.Canvas;
import main.App;
import model.Hero;

public class GameCanvas extends Canvas {
	
	private int startX = 0;
	private static GameCanvas currentInstance = null;
	private boolean onTransition;
	private int transitionWidth = 150;
	
	public GameCanvas() {
		super(App.SCREEN_WIDTH, App.SCREEN_HEIGHT);
		currentInstance = this;
		this.onTransition = false;
	}
	
	public void setStartX(int startX) {
		this.startX = startX;
	}
	
	public int getStartX() {
		return startX;
	}
	
	public static GameCanvas getCurrentInstance() {
		return currentInstance;
	}
	
	public void checkStartPoint(Hero hero) {
		if (onTransition) {
			doTansition(hero);
		}
		if (hero.getPosition().first - startX > App.SCREEN_WIDTH - 150 && !onTransition) {
			//startX = hero.getPosition().first - (App.SCREEN_WIDTH - 150);
			onTransition = true;
		}
		if (hero.getPosition().first - startX < 150 - hero.getWidth()) {
			startX = hero.getPosition().first - 150 + hero.getWidth();
			if (startX < 0) {
				startX = 0;
			}
		}
	}
	
	public void doTansition(Hero hero) {
		startX = hero.getPosition().first - (App.SCREEN_WIDTH - transitionWidth);
		transitionWidth += 15;
		if (transitionWidth >= 2 * App.SCREEN_WIDTH / 3 + hero.getWidth()) {
			onTransition = false;
			transitionWidth = 150;
		}
	}

}
