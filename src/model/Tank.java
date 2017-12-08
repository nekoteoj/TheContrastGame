package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import logic.GameMap;
import model.utility.ClassResourceUtility;
import view.GameCanvas;

public class Tank extends Enemy {

	private static List<Image> imageFrame;
	private static AudioClip tankExplodeSound;
	
	private static final int FRAMEWIDTH = 76;
	private static final int FRAMEHEIGHT = 60;
	
	static {
		imageFrame = new ArrayList<>();
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/Tank/1L.png"), FRAMEWIDTH, FRAMEHEIGHT, true, true));     
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/Tank/2L.png"), FRAMEWIDTH, FRAMEHEIGHT, true, true));
		tankExplodeSound = new AudioClip(ClassResourceUtility.getResourcePath("sound/tank_explode.wav")); 
	}
	
	private int frameTick = 0;
	private int frameState = 0;
	
	public Tank(int x, int y) {
		super(x, y, FRAMEWIDTH, FRAMEHEIGHT);
		vx = 0;
		vy = 0;
		onAir = true;
		this.findDirectionOfHero();
		hp = 50;
	}
	

	
	@Override
	public void draw(GraphicsContext gc) {
		gc.setStroke(Color.AQUA);
		gc.strokeRect(position.first - GameCanvas.getCurrentInstance().getStartX(), position.second, width, height);
		if (frameTick > 5) {
			frameTick = 0;
			frameState = (frameState + 1) % 2;
		}
		gc.drawImage(imageFrame.get(frameState), position.first - GameCanvas.getCurrentInstance().getStartX(), position.second);
		frameTick++;
	}
	
	public Bullet fire() {
		Bullet bullet = new TankBullet(this.position.first + this.width, this.position.second + this.height / 4, 1, this.direction);
GameMap.addEntity(bullet);
return bullet;
		
	}



	@Override
	public void dead() {
		tankExplodeSound.play();
		super.dead();
	}
}