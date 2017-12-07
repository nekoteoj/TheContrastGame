package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import logic.GameMap;
import model.utility.ClassResourceUtility;
import model.utility.Pair;
import view.GameCanvas;

public class NormalBullet extends Bullet {
	
	private static AudioClip bulletSound;
	private static List<Image> imageFrame;
	
	static {
		imageFrame = new ArrayList<>();
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/NormalBullet/1R.png"), 20, 6, true, true));
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/NormalBullet/1L.png"), 20, 6, true, true));
		bulletSound = new AudioClip(ClassResourceUtility.getResourcePath("sound/shot_1.wav"));
		bulletSound.setVolume(0.5);
	}
	
	public final static int MP_USE = 0;
	 
	public NormalBullet(int x, int y, int target, int direction) {
		super(x, y, 20, 6);
		this.target = target;
		this.attackPoint = 2;
		this.velocity = 15;
		startPosition = Pair.makePair(x, y);
		this.direction = direction;
		bulletSound.play();
		if (direction == 1) {
			vx = velocity;
		} else if (direction == 0) {
			vx = -velocity;
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
//		gc.setFill(Color.RED);
//		gc.fillOval(position.first - GameCanvas.getCurrentInstance().getStartX(), position.second, 20, 10);
		gc.drawImage(imageFrame.get((direction + 1) % 2), position.first - GameCanvas.getCurrentInstance().getStartX(), position.second);
	}

}