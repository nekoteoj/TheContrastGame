package model;

import logic.GameMap;

public abstract class Item extends Entity implements Renderable {
private int timeToLive;

	public Item(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.timeToLive = 600;
		// TODO Auto-generated constructor stub
	}
	
	public void decreaseTimeToLive(int amount) {
		this.timeToLive -= 1;
		if (this.timeToLive <= 0) {
			this.dead();
		}
	}
	
	public Hero getHero() {
		return (Hero) GameMap.getEntityObjects().parallelStream().filter(x -> x instanceof Hero).findAny().get();
	}

	public void use(Hero hero) {
	this.dead();
	}
	
	public void dead() {
		GameMap.getEntityObjects().remove(this);
		GameMap.getRenderObjects().remove(this);
	}
	
}
