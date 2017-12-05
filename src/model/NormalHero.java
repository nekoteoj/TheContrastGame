package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameMap;
import model.utility.ClassResourceUtility;
import model.utility.Pair;
import view.GameCanvas;

public class NormalHero extends Hero {
	
	protected static List<Image> imageFrame;
	
	static {
		imageFrame = new ArrayList<>();
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/hero.png"), 70, 99, true, true));
	}

	
	public static int DEFAULT_HP = 100;
	protected int hp;
	protected int vx;
	protected int vy;
	protected boolean onAir;
	// 1 right 0 left
	protected int direction;
	
	public NormalHero(int x, int y) {
		super(x, y, 70 - 25, 99);
		vx = 0;
		vy = 0;
		onAir = true;
		direction = 1;
		hp = 100;
	}
	
	@Override
	public boolean isOnAir() {
		// TODO Auto-generated method stub
		return onAir;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(imageFrame.get(0), position.first - GameCanvas.getCurrentInstance().getStartX(), position.second);
		gc.setLineWidth(3);
		gc.strokeRect(position.first - GameCanvas.getCurrentInstance().getStartX(), position.second, width, height);
	}
	
	@Override
	public void moveDownGravity() {
		if (isOnAir()) {
				vy += 3;
		}
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		if (vx == 0 && vy == 0) {
			return;
		}
		if (vx != 0) {
			direction = vx > 0 ? 1 : 0;
		}
		position.second += vy;
		position.first += vx;
		if (position.second < 0) {
			position.second = 0;
			vy = 0;
		}
		if (position.first < 0) {
			position.first = 0;
			vx = 0;
		}
		checkCollide();
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}
	
	@Override
	public void setOnAir(boolean onAir) {
		this.onAir = onAir;
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
	public int getDirection() {
		return direction;
	}

	@Override
	public int getHp() {
		// TODO Auto-generated method stub
		return hp;
	}

	@Override
	public void setHp(int amount) {
		hp = amount;
	}

	@Override
	public void decreaseHp(int amount) {
		hp -= amount;
		if (hp < 0) {
			hp = 0;
			dead();
		}
	}

	@Override
	public void dead() {
		
	}
}
