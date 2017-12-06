package model;

public abstract class Hero extends Entity implements GravityAffected, Renderable, Attackable, Movable {

	protected int hp;

	public Hero(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public int getHp() {
		// TODO Auto-generated method stub
		return hp;
	}

	@Override
	public void dead() {
//TODO if the hero dies, we should do what? decreasing lives or displaying game over screen?		
	}

	public void setHp(int amount) {
		hp = amount;
	}

}
