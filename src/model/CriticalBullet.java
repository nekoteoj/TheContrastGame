package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import model.utility.ClassResourceUtility;
import model.utility.Pair;
import view.GameCanvas;

public class CriticalBullet extends Bullet {
	public final static int MP_USE = 10;

	private static AudioClip bulletSound;
	private static List<Image> imageFrame;

	static {
		imageFrame = new ArrayList<>();
		imageFrame.add(
				new Image(ClassResourceUtility.getResourcePath("img/model/CriticalBullet/1R.png"), 42, 10, true, true));
		imageFrame.add(
				new Image(ClassResourceUtility.getResourcePath("img/model/CriticalBullet/1L.png"), 42, 10, true, true));
		bulletSound = new AudioClip(ClassResourceUtility.getResourcePath("sound/shot_3.wav"));
	}

	public CriticalBullet(int x, int y, int target, int direction) {
		super(x, y, 42, 10);
		this.target = target;
		this.attackPoint = 10;
		this.velocity = 25;
		bulletSound.play();
		startPosition = Pair.makePair(x, y);
		this.direction = direction;
		if (direction == 1) {
			vx = velocity;
		} else if (direction == 0) {
			vx = -velocity;
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		// gc.setFill(Color.ORANGE);
		// gc.fillOval(position.first - GameCanvas.getCurrentInstance().getStartX(),
		// position.second, 8, 8);
		gc.drawImage(imageFrame.get((direction + 1) % 2), position.first - GameCanvas.getCurrentInstance().getStartX(),
				position.second);
	}

}