package model;

public abstract class Hero extends MovableEntity implements  Renderable, Attackable, GravityAffected, SpecialAbilityUser{

	protected int hp;
	protected double mp;

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

	
	public double getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}
	
	@Override
	public boolean isMpFull() {
	return mp == 100.0;	
	}
	
	public int getIntMp() {
	return (int)Math.floor(this.mp);	
	}
	
	@Override
	public void increaseMp(double amount) {
	mp += amount;
	if (mp > 100.0) {
		mp = 100.0;
	}
	}

	@Override
	public void decreaseMp(double amount) {
		mp -= amount;
		if (mp <= 0) {
			mp = 0.0;
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

	@Override
	public void dead() {
//TODO if the hero dies, we should do what? decreasing lives or displaying game over screen?		
	}
}
