package model;

import logic.GameMap;

public abstract class Item extends Entity implements Renderable {


	public Item(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public Hero getHero() {
		return (Hero) GameMap.getEntityObjects().parallelStream().filter(x -> x instanceof Hero).findAny().get();
	}

	public void use() {
	this.dead();
	}
	
	public void dead() {
		GameMap.getEntityObjects().remove(this);
		GameMap.getRenderObjects().remove(this);
	}
	
}
