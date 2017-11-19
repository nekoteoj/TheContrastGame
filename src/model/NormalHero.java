package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.utility.Pair;

public class NormalHero extends Hero {
	
	protected static List<Image> imageFrame;
	
	static {
		imageFrame = new ArrayList<>();
		imageFrame.add(new Image("hero.png", 70, 99, true, true));
	}
	
	protected Pair<Integer, Integer> position;
	protected int vx;
	protected int vy;
	protected boolean onAir;
	
	public NormalHero() {
		super();
		vx = 0;
		vy = 0;
		onAir = true;
		position = Pair.makePair(100, 0);
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
}
