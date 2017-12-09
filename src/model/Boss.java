package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import logic.GameMap;
import model.utility.ClassResourceUtility;
import view.GameCanvas;
import view.GamePane;
import view.ViewManager;

public class Boss extends Enemy {

	protected static List<Image> imageFrame;
	private static final int BOSSWIDTH = 55;
	private static final int BOSSHEIGHT = 80;

	static {
		imageFrame = new ArrayList<>();
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/Boss/1R.png"), BOSSWIDTH, BOSSHEIGHT,
				true, false));
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/Boss/2R.png"), BOSSWIDTH, BOSSHEIGHT,
				true, false));
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/Boss/1L.png"), BOSSWIDTH, BOSSHEIGHT,
				true, false));
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/Boss/2L.png"), BOSSWIDTH, BOSSHEIGHT,
				true, false));
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
		gc.drawImage(imageFrame.get(walkState), position.first - GameCanvas.getCurrentInstance().getStartX(),
				position.second);
		// gc.setLineWidth(3);
		// gc.strokeRect(position.first - GameCanvas.getCurrentInstance().getStartX(),
		// position.second, width, height);
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
		Bullet bullet = new CriticalBullet(this.position.first + this.width, this.position.second + this.height / 2, 1,
				this.direction);
		GameMap.addEntity(bullet);
		return bullet;

	}

	@Override
	public void dead() {
		new AudioClip(ClassResourceUtility.getResourcePath("sound/boss_explode.wav")).play();
		super.dead();
		((GamePane) ViewManager.getInstance().getPane("game")).endGame(true);
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