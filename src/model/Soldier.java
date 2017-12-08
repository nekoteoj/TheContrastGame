package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import logic.GameMap;
import model.map.MapObject;
import model.utility.ClassResourceUtility;
import view.GameCanvas;

public class Soldier extends Enemy {

	protected static List<Image> imageFrame;
	
	static {
		imageFrame = new ArrayList<>();
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/Soldier/1R.png"), 50, 77, true, false));
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/Soldier/2R.png"), 50, 77, true, false));
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/Soldier/1L.png"), 50, 77, true, false));
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/Soldier/2L.png"), 50, 77, true, false));
	}
  
	private int walkState;
	private int walkTick = 0;
	
	public Soldier(int x, int y) {
		super(x, y, 50, 77);
		vx = 0;
		vy = 0;
		onAir = true;
		direction = 0;
		hp = 5;
		walkState = 2;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(imageFrame.get(walkState), position.first - GameCanvas.getCurrentInstance().getStartX(), position.second);
//		gc.setLineWidth(3);
//		gc.strokeRect(position.first - GameCanvas.getCurrentInstance().getStartX(), position.second, width, height);
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
		Bullet bullet = new NormalBullet(this.position.first + this.width, this.position.second + this.height / 2, 1, this.direction);
GameMap.addEntity(bullet);
return bullet;
		
	}

	@Override
	public void dead() {
		 new AudioClip(ClassResourceUtility.getResourcePath("sound/soldier_destroy.wav")).play();
		super.dead();
	}
	
	
	
}
