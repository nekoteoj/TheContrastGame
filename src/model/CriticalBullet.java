package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import model.utility.ClassResourceUtility;
import model.utility.Pair;
import view.GameCanvas;

public class CriticalBullet extends Bullet {
	public final static int MP_USE = 10;
	
	public CriticalBullet(int x, int y, int target, int direction) {
		super(x, y, 8, 8);
		this.target = target;
		this.attackPoint = 10;
				new AudioClip(ClassResourceUtility.getResourcePath("sound/shot_3.wav")).play();
		startPosition = Pair.makePair(x, y);
		this.direction = direction;
		if (direction == 1) {
			vx = 25;
		} else if (direction == 0) {
			vx = -25;
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.ORANGE);
		gc.fillOval(position.first - GameCanvas.getCurrentInstance().getStartX(), position.second, 8, 8);
	}

}