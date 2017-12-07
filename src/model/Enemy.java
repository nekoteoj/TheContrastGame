package model;

import java.util.List;

import logic.GameMap;
import model.map.MapObject;

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

@Override
public void checkCollide() {
	List<Entity> l = GameMap.getEntityObjects();
	setOnAir(true);
	for (Entity o : l) {
		if (this != o && o.isCollide(this) && o instanceof MapObject) {
			fixCollide(o);
		}
	}
}

@Override
public void fixCollide(Entity other) {
		if (other instanceof MapObject) {
			if (position.second + height - vy <= other.position.second + 5 && vy >= 0) {
				vy = 0;
				position.second = other.position.second - height;
				setOnAir(false);
			}
		}
	}



	public int getHp() {
		return hp;
	}

	
	public void setHp(int amount) {
		hp = amount;
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
		GameMap.getEntityObjects().remove(this);
		GameMap.getRenderObjects().remove(this);
	}

public void findDirectionOfHero() {
Hero hero = (Hero) GameMap.getEntityObjects().parallelStream().filter(x -> x instanceof Hero).findAny().get();
	if (hero.getPosition().first < this.position.first) {
this.direction= 0; //left		
	} else {
		this.direction= 1; //right
	}
	
}
public void findDirectionOfHero(Hero hero) {
	if (hero.getPosition().first < this.position.first) {
		this.direction= 0; //left		
	} else {
		this.direction = 1; //right
	}
	
}
}
