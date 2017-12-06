package model;

import logic.GameMap;

public abstract class Enemy extends Entity implements GravityAffected, Renderable, Attackable, Movable {
	protected int hp;
	
	public Enemy(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public int getHp() {
		return hp;
	}

	
	public void setHp(int amount) {
		hp = amount;
	}

@Override
	public void dead() {
		GameMap.getEntityObjects().remove(this);
		GameMap.getRenderObjects().remove(this);
	}
}
