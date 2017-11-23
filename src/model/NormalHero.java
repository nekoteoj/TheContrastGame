package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameMap;
import model.utility.Pair;

public class NormalHero extends Hero {
	
	protected static List<Image> imageFrame;
	
	static {
		imageFrame = new ArrayList<>();
		imageFrame.add(new Image("hero.png", 70, 99, true, true));
	}

	protected int vx;
	protected int vy;
	protected boolean onAir;
	
	public NormalHero(int x, int y) {
		super(x, y, 70, 99);
		vx = 0;
		vy = 0;
		onAir = true;
	}
	
	@Override
	public boolean isOnAir() {
		// TODO Auto-generated method stub
		return onAir;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(imageFrame.get(0), position.first, position.second);
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
		position.second += vy;
		position.first += vx;
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
		for (Entity o : l) {
			if (this != o && o.isCollide(this)) {
				fixCollide(o);
			}
		}
	}

	@Override
	public void fixCollide(Entity other) {
		//System.out.println("x = " + position.first + " | y = " + (position.second + height) + " | entity y = " + other.getPosition().second);
		if (onAir && vy > 0 && position.second + height >= other.getPosition().second) {
			onAir = false;
			vy = 0;
			position.second = other.getPosition().second - height;
//			if (position.first + width >= other.getPosition().first) {
//				vx = 0;
//				position.first = other.getPosition().first - width;
//			}
//			if (position.first <= other.getPosition().first + other.width) {
//				vx = 0;
//				position.first = other.getPosition().first + width;
//			}
		} else if (position.second + height < other.getPosition().second) {
			onAir = true;
		}
	}
}
