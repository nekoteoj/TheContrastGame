package model;

import logic.GameMap;

public abstract class Bullet extends MovableEntity implements Renderable{

	public Bullet(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public void dead() {
		GameMap.getEntityObjects().remove(this);
		GameMap.getRenderObjects().remove(this);
	}
}
