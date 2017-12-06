package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameMap;
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
		gc.setLineWidth(3);
		gc.strokeRect(position.first - GameCanvas.getCurrentInstance().getStartX(), position.second, width, height);
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
				walkState++;
				walkState %= 2;
			}
		} else {
			if (direction == 0) {
				if (vy != 0) {
					walkState = 3;
				} else if (vx == 0) {
					walkState = 2;
				} else {
					walkState = walkState == 2 ? 3 : 2;
				}
			}
		}
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

	@Override
	public void decreaseHp(int amount) {
		hp -= amount;
		if (hp <= 0) {
			hp = 0;
			dead();
		}
	}
	
}
