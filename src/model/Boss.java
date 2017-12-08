package model;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameMap;
import main.App;
import model.utility.ClassResourceUtility;
import model.utility.ScoreIO;
import view.GameCanvas;
import view.GamePane;
import view.ViewManager;

public class Boss extends Enemy{
	
	protected static List<Image> imageFrame;
	protected static AudioClip heroWinSound;
	
	private static final int BOSSWIDTH = 55;
	private static final int BOSSHEIGHT = 80;
	
	static {
		heroWinSound = new AudioClip(ClassResourceUtility.getResourcePath("sound/herowinsound.mp3"));
		imageFrame = new ArrayList<>();
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/Boss/1R.png"), BOSSWIDTH, BOSSHEIGHT, true, false));
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/Boss/2R.png"), BOSSWIDTH, BOSSHEIGHT, true, false));
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/Boss/1L.png"), BOSSWIDTH, BOSSHEIGHT, true, false));
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/Boss/2L.png"), BOSSWIDTH, BOSSHEIGHT, true, false));
	}
  
	private int walkState;
	private int walkTick = 0;
	public static final int DEFAULT_HP = 200;
	
	public Boss(int x, int y) {
		super(x, y, BOSSWIDTH, BOSSHEIGHT);
		vx = 0;
		vy = 0;
		onAir = true;
		this.findDirectionOfHero();
		walkState = 2;
		this.hp = Boss.DEFAULT_HP;
	}
	

	
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(imageFrame.get(walkState), position.first - GameCanvas.getCurrentInstance().getStartX(), position.second);
//		gc.setLineWidth(3);
//		gc.strokeRect(position.first - GameCanvas.getCurrentInstance().getStartX(), position.second, width, height);
	}
	
	@Override
	public void move() {
		super.move();
		if (direction == 1) {
			if (vy != 0) {
				walkState = 1;
			} else if (vx == 0) {
				walkState = 0;
			} else {
				if (walkTick > 2) {
					walkState++;
					walkState %= 2;
					walkTick = 0;
				} else {
					walkTick++;
				}
			}
		} else {
			if (direction == 0) {
				if (vy != 0) {
					walkState = 3;
				} else if (vx == 0) {
					walkState = 2;
				} else {
					if (walkTick > 2) {
						walkState = walkState == 2 ? 3 : 2;
						walkTick = 0;
					} else {
						walkTick++;
					}
				}
			}
		}
	}
	
	public Bullet fire() {
		Bullet bullet = new CriticalBullet(this.position.first + this.width, this.position.second + this.height / 2, 1, this.direction);
GameMap.addEntity(bullet);
return bullet;
		
	}

	public void doWinEffect() {
		GraphicsContext gc = GameCanvas.getCurrentInstance().getGraphicsContext2D();
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
		heroWinSound.play();
	}

	@Override
	public void dead() {
		 new AudioClip(ClassResourceUtility.getResourcePath("sound/boss_explode.wav")).play();
		super.dead();
		((GamePane) ViewManager.getInstance().getPane("game")).stopGameLoop();
		doWinEffect();
		Platform.runLater(() -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("CONGRATULATION");
			alert.setHeaderText("YOU WIN");
			alert.setContentText("Good job. Have a nice day!");
			alert.showAndWait();
			if (!((GamePane) ViewManager.getInstance().getPane("game")).isCustomMap()) {
				ScoreIO.getInstance().addNewScore(System.nanoTime());
			}
			ViewManager.getInstance().goTo("menu");
		});
	}

	public Bullet fireMeteor(int x, int y) {
		Bullet bullet = new MeteorStrike(x, y, 1);
GameMap.addEntity(bullet);
return bullet;
	}

public Bullet fireMeteorArc(int x, int y) {
		Bullet bullet = new MeteorArc(x, y, 1);
GameMap.addEntity(bullet);
return bullet;
	}

public Entity fireLightning(int damage) {
		Entity lightning = new LightningBolt(damage);
GameMap.addEntity(lightning);
return lightning;
	}

public void jump() {
this.setVy(-20);
this.setOnAir(true);
}
}