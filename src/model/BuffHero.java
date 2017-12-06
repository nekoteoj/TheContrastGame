package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameMap;
import model.utility.ClassResourceUtility;
import view.GameCanvas;

public class BuffHero extends Hero {

protected static List<Image> imageFrame;
	
	static {
		imageFrame = new ArrayList<>();
		//TODO change image!
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/hero.png"), 70, 99, true, true));
	}

	
	public static int DEFAULT_HP = 100;
	
	public BuffHero(int x, int y, int vx, int vy, int direction) {
		super(x, y, 70 - 25, 99);
		this.vx = vx;
		this.vy = vy;
		onAir = true;
		this.direction = direction;
		hp = 100;
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

	@Override
	public void increaseMp(double amount) {
		 this.setMp(100.0);
		
	}

	@Override
	public void decreaseMp(double amount) {
		 
		return;
	}

	@Override
	public void decreaseHp(int amount) {
		 
		return;
	}
	


	
	
	

	}
