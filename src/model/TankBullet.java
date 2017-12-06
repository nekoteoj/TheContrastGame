package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import model.utility.ClassResourceUtility;
import model.utility.Pair;
import view.GameCanvas;

public class TankBullet extends Bullet {
public final static int MP_USE = 0;
	public TankBullet(int x, int y, int target, int direction) {
		super(x, y, 6, 6);
		this.target = target;
		this.attackPoint = 2;
		this.velocity = 20;
		new AudioClip(ClassResourceUtility.getResourcePath("sound/shot_tank.wav")).play();
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
		gc.setFill(Color.BLUE);
		gc.fillOval(position.first - GameCanvas.getCurrentInstance().getStartX(), position.second, 6, 6);
	}

}