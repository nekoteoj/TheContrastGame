package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import logic.GameMap;
import model.utility.ClassResourceUtility;
import view.GameCanvas;

public class Soldier extends Enemy {

	protected static List<Image> imageFrame;
	
	static {
		imageFrame = new ArrayList<>();
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/hero.png"), 70, 99, true, true));
	}
	
	public Soldier(int x, int y) {
		super(x, y, 70 - 25, 99);
		vx = 0;
		vy = 0;
		onAir = true;
		direction = 0;
		hp = 5;
	}
	

	
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(imageFrame.get(0), position.first - GameCanvas.getCurrentInstance().getStartX(), position.second);
		gc.setLineWidth(3);
		gc.strokeRect(position.first - GameCanvas.getCurrentInstance().getStartX(), position.second, width, height);
	}
	

	@Override
	public void checkCollide() {
		List<Entity> l = GameMap.getEntityObjects();
		boolean collideWithFloor = false;
		for (Entity o : l) {
			if (this != o && o.isCollide(this)) {
				fixCollide(o);
				if (vy > 0) {
					collideWithFloor = true;
				}
			}
		}
		setOnAir(collideWithFloor == false);
	}

	@Override
	public void fixCollide(Entity other) {
		//System.out.println("x = " + position.first + " | y = " + (position.second + height) + " | entity y = " + other.getPosition().second);
/*		if (onAir && position.second + height <= other.getPosition().second + other.getHeight()) {
			//onAir = false;
			System.out.println("We are here");
			vy = 0;
			position.second = other.getPosition().second - height;
		} else if (position.second + height < other.getPosition().second) {
			//onAir = true;
		}*/
		if (!onAir && vx > 0 && position.first + width >= other.getPosition().first) {
			vx = 0;
			position.first = other.getPosition().first - getWidth() - 2;
		} else if (!onAir && vx < 0 && position.first <= other.getPosition().first + other.width) {
			vx = 0;
			position.first = other.getPosition().first + other.getWidth() + 2;
		}
		if (isOnAir() && position.second + height <= other.getPosition().second + other.getHeight()) {
			vy = 0;
			position.second = other.getPosition().second - height;
		} else if (isOnAir() && vy < 0 && position.second <= other.getPosition().second + other.getHeight()) {
			vy = 0;
			position.second = other.getPosition().second + other.getHeight();
		}
	}


	public Bullet fire(int type) {
		Bullet bullet; 
		if (type == 1)
		{
			bullet = new NormalBullet(this.position.first + this.width, this.position.second + this.height / 2, 1, this.direction);
		} else {
			return null;
		}
GameMap.addEntity(bullet);
return bullet;
		
	}



	@Override
	public void dead() {
		 new AudioClip(ClassResourceUtility.getResourcePath("sound/soldier_destroy.wav")).play();
		super.dead();
	}
	
	
	
}
