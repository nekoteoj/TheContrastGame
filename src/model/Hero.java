package model;

import logic.GameMap;

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

	public void setMp(double d) {
		this.mp = d;
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
	
	public Bullet fire(int type) {
		Bullet bullet; 
		if (type == 1)
		{
			bullet = new NormalBullet(this.position.first + this.width, this.position.second + this.height / 2, 0, this.direction);
		} else if ((type == 2) && (this.mp >= 10.0)) {
			bullet = new CriticalBullet(this.position.first + this.width, this.position.second + this.height / 2, 0, this.direction);
			this.decreaseMp(10.0);
		} else if ((type == 3) && (isMpFull())) {
			bullet = new Hadoken(this.position.first + this.width, this.position.second + this.height / 2, 0, this.direction);
			this.setMp(0.0);
		} else {
			return null;
		}
GameMap.addEntity(bullet);
return bullet;
		
	}

	@Override
	public void dead() {
//TODO if the hero dies, we should do what? decreasing lives or displaying game over screen?		
	}
}
