package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameMap;
import main.App;
import model.Hero;
import model.utility.ClassResourceUtility;

public class GameCanvas extends Canvas {

	private static AudioClip winSoundEffect;
	private static AudioClip loseSoundEffect;

	static {
		loseSoundEffect = new AudioClip(ClassResourceUtility.getResourcePath("sound/herodeadeffect.mp3"));
		winSoundEffect = new AudioClip(ClassResourceUtility.getResourcePath("sound/herowinsound.mp3"));
	}

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
			// startX = hero.getPosition().first - (App.SCREEN_WIDTH - 150);
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
		if (startX > GameMap.getMapLength() - App.SCREEN_WIDTH) {
			startX = GameMap.getMapLength() - App.SCREEN_WIDTH;
			onTransition = false;
			transitionWidth = 150;
		}
		transitionWidth += 15;
		if (transitionWidth >= 2 * App.SCREEN_WIDTH / 3 + hero.getWidth()) {
			onTransition = false;
			transitionWidth = 150;
		}
	}

	public void drawLoseScreen() {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, App.SCREEN_WIDTH, App.SCREEN_HEIGHT);
		gc.setFont(new Font("Press Start 2P", 200));
		gc.setFill(Color.WHITE);
		gc.fillText("T_T", 80, 100);
		gc.setFont(new Font("Press Start 2P", 26));
		gc.fillText("This is much like Prog Meth.", 25, 400);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		loseSoundEffect.play();
	}

	public void drawWinScreen() {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.LIMEGREEN);
		gc.fillRect(0, 0, App.SCREEN_WIDTH, App.SCREEN_HEIGHT);
		gc.setFont(new Font("Press Start 2P", 200));
		gc.setFill(Color.WHITE);
		gc.fillText("WIN", 80, 100);
		gc.setFont(new Font("Press Start 2P", 36));
		gc.fillText("Congratulation!", 100, 400);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		winSoundEffect.play();
	}

}
