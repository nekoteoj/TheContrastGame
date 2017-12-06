package model;

import javafx.scene.canvas.GraphicsContext;

public class Tank extends Enemy {

	public Tank(int x, int y) {
		super(x, y, 100, 100);
	}
	
	@Override
	public boolean isCollide(Entity other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decreaseHp(int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkCollide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fixCollide(Entity other) {
		// TODO Auto-generated method stub
		
	}

}
