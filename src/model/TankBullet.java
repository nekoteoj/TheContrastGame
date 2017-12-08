package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import model.utility.ClassResourceUtility;
import model.utility.Pair;
import view.GameCanvas;

public class TankBullet extends Bullet {
	
	private static List<Image> imageFrame;
	private static AudioClip tankBulletSound;
	
	private static final int BULLETWIDTH = 26;
	private static final int BULLETHEIGHT = 22;
	
	static {
		imageFrame = new ArrayList<>();
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/TankBullet/1L.png"), BULLETWIDTH, BULLETHEIGHT, true, true));
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/TankBullet/2L.png"), BULLETWIDTH, BULLETHEIGHT, true, true));
		tankBulletSound = new AudioClip(ClassResourceUtility.getResourcePath("sound/shot_tank.wav"));
	}
	
	private int frameState = 0;
	private int frameTick = 0;
	
public final static int MP_USE = 0;
	public TankBullet(int x, int y, int target, int direction) {
		super(x, y, BULLETWIDTH, BULLETHEIGHT);
		this.target = target;
		this.attackPoint = 2;
		this.velocity = 20;
		tankBulletSound.play();
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
//		gc.setFill(Color.BLUE);
//		gc.fillOval(position.first - GameCanvas.getCurrentInstance().getStartX(), position.second, 6, 6);
		if (frameTick > 30) {
			frameTick = 0;
			frameState = (frameState + 1) % 2;
		}
		gc.drawImage(imageFrame.get(frameState), position.first - GameCanvas.getCurrentInstance().getStartX(), position.second);
		frameTick++;
	}

}