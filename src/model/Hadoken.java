package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import logic.GameMap;
import model.utility.ClassResourceUtility;
import model.utility.Pair;
import view.GameCanvas;

public class Hadoken extends Bullet {
	public final static int MP_USE = 100;
	private Set<Entity> alreadyCollide;
	private static AudioClip bulletSound;
	private static List<Image> imageFrame;

	static {
		imageFrame = new ArrayList<>();
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/Hadoken/1R.png"), 50, 28, true, true));
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/Hadoken/1L.png"), 50, 28, true, true));
		bulletSound = new AudioClip(ClassResourceUtility.getResourcePath("sound/hadouken.wav"));
	}

	public Hadoken(int x, int y, int target, int direction) {
		super(x, y, 50, 28);
		this.target = target;
		this.attackPoint = 50;
		this.velocity = 15;
		this.alreadyCollide = new HashSet<>();
		bulletSound.play();
		startPosition = Pair.makePair(x, y);
		this.direction = direction;
		if (direction == 1) {
			vx = velocity;
		} else if (direction == 0) {
			vx = -velocity;
		}
	}

	public void addToCollide(Entity e) {
		this.alreadyCollide.add(e);
	}

	public void removeToCollide(Entity e) {
		this.alreadyCollide.remove(e);
	}

	public boolean isAlreadyCollide(Entity e) {
		return this.alreadyCollide.contains(e);
	}

	@Override
	public void draw(GraphicsContext gc) {
		// gc.setFill(Color.YELLOW);
		// gc.fillOval(position.first - GameCanvas.getCurrentInstance().getStartX(),
		// position.second, 10, 10);
		gc.drawImage(imageFrame.get((direction + 1) % 2), position.first - GameCanvas.getCurrentInstance().getStartX(),
				position.second);
	}

	@Override
	public void move() {
		position.first += vx;
		if (Math.abs(position.first - startPosition.first) >= 900) {
			dead();
		}
		checkCollide();
	}

	@Override
	public void checkCollide() {
		for (Entity e : GameMap.getEntityObjects()) {
			if (this != e && e.isCollide(this)) {
				if (target == 0 && e instanceof Enemy) {
					if (!(this.isAlreadyCollide(e))) {
						fixCollide(e);
						this.addToCollide(e);
					} else {
						continue;
					}
				} else if (target == 1 && e instanceof Hero) {
					if (!(this.isAlreadyCollide(e))) {
						fixCollide(e);
						this.addToCollide(e);
					} else {
						continue;
					}
				}
			} else if (this != e && !(e.isCollide(this)) && this.isAlreadyCollide(e)) {
				this.removeToCollide(e);
			}
		}
	}

	@Override
	public void fixCollide(Entity other) {
		if (!(other instanceof Attackable)) {
			return;
		}
		((Attackable) other).decreaseHp(this.attackPoint);
	}

}
