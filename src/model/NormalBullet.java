package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import logic.GameMap;
import model.utility.ClassResourceUtility;
import model.utility.Pair;
import view.GameCanvas;

public class NormalBullet extends Bullet {
	public final static int MP_USE = 0;
	 
	 	
	
	
	

	public NormalBullet(int x, int y, int target, int direction) {
		super(x, y, 4, 4);
		this.target = target;
		this.attackPoint = 2;
		new AudioClip(ClassResourceUtility.getResourcePath("sound/shot_1.wav")).play();
		startPosition = Pair.makePair(x, y);
		this.direction = direction;
		if (direction == 1) {
			vx = 15;
		} else if (direction == 0) {
			vx = -15;
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillOval(position.first - GameCanvas.getCurrentInstance().getStartX(), position.second, 4, 4);
	}

}