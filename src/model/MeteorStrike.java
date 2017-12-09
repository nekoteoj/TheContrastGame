package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import logic.GameMap;
import model.map.MapObject;
import model.utility.ClassResourceUtility;
import model.utility.Pair;
import view.GameCanvas;

public class MeteorStrike extends Bullet implements GravityAffected {

	private static AudioClip fallingSound;
	private static AudioClip impactSound;
	private static List<Image> imageFrame;

	private static final int METEORWIDTH = 100;
	private static final int METEORHEIGHT = 100;

	static {
		imageFrame = new ArrayList<>();
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/MeteorStrike/1.png"), METEORWIDTH,
				METEORHEIGHT, true, false));
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/MeteorStrike/2.png"), METEORWIDTH,
				METEORHEIGHT, true, false));
		fallingSound = new AudioClip(ClassResourceUtility.getResourcePath("sound/falling_meteor.wav"));
		impactSound = new AudioClip(ClassResourceUtility.getResourcePath("sound/impact_meteor.wav"));
	}

	protected int meteorWidth = METEORWIDTH;
	protected int meteorHeight = METEORHEIGHT;
	protected int centerX;

	private int frameTick = 0;
	private int frameState = 0;

	public MeteorStrike(int x, int y, int target) {
		super(0, 0, 1, 1);
		int meteorX = x - ((this.meteorWidth - 50) / 2);
		meteorX = meteorX < 0 ? 0 : meteorX;
		this.centerX = (meteorX + (meteorX + this.meteorWidth)) / 2;
		// System.out.println("original center:"+this.centerX);
		this.width = this.meteorWidth;
		this.height = this.meteorHeight;
		this.position.first = meteorX;
		this.target = target;
		this.attackPoint = 15;
		this.vy = 0;
		startPosition = Pair.makePair(meteorX, 0);
		fallingSound.play();
	}

	@Override
	public void draw(GraphicsContext gc) {
		// gc.setFill(Color.RED);
		// gc.strokeRect(position.first - GameCanvas.getCurrentInstance().getStartX(),
		// position.second, width, height);
		if (frameTick > 30) {
			frameTick = 0;
			frameState = (frameState + 1) % 2;
		}
		gc.drawImage(imageFrame.get(frameState), position.first - GameCanvas.getCurrentInstance().getStartX(),
				position.second);
		frameTick++;
	}

	@Override
	public void checkCollide() {
		for (Entity e : GameMap.getEntityObjects()) {
			if (this != e && e.isCollide(this)) {
				if (target == 0 && e instanceof Enemy) {
					fixCollide(e);
					dead();
					break;
				} else if (target == 1 && e instanceof Hero) {
					fixCollide(e);
					dead();
					break;
				} else if (e instanceof MapObject) {
					dead();
					break;
				}
			}
		}
	}

	@Override
	public void dead() {
		System.out.println("Meteor: (" + this.position.first + ", " + this.position.second + ")");
		impactSound.play();
		GameMap.getEntityObjects().remove(this);
		GameMap.getRenderObjects().remove(this);
	}

	@Override
	public boolean isOnAir() {
		return true;
	}

	@Override
	public void setOnAir(boolean onAir) {
		return;
	}

	@Override
	public void moveDownGravity() {
		if (isOnAir() && vy < 3) {
			vy += 1;
		}
	}
}