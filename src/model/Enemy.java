package model;

import logic.GameMap;

public abstract class Enemy extends MovableEntity implements Renderable, Attackable, GravityAffected{
	protected int hp;
	
	public Enemy(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean isOnAir() {
		// TODO Auto-generated method stub
		return onAir;
	}

@Override
	public void moveDownGravity() {
		if (isOnAir()) {
				vy += 3;
		}
	}


@Override
	public void setOnAir(boolean onAir) {
		this.onAir = onAir;
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
