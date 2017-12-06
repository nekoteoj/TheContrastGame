package model;

public abstract class Hero extends MovableEntity implements  Renderable, Attackable, GravityAffected{

	protected int hp;

	public Hero(int x, int y, int width, int height) {
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
		// TODO Auto-generated method stub
		return hp;
	}

	

	public void setHp(int amount) {
		hp = amount;
	}

	
	@Override
	public void dead() {
//TODO if the hero dies, we should do what? decreasing lives or displaying game over screen?		
	}
}
