package model;

import model.utility.Pair;

public abstract class Entity {
	
	protected Pair<Integer, Integer> position;
	protected int width;
	protected int height;
	
	public Entity(int x, int y, int width, int height) {
		position = Pair.makePair(x, y);
		this.width = width;
		this.height = height;
	}
	
	public Pair<Integer, Integer> getPosition() {
		return position;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public boolean isCollide(Entity other) {
		Pair<Integer, Integer> o = other.getPosition();
		if (o.first >= position.first && o.first <= position.first + width &&
				o.second >= position.second && o.second <= position.second + height) {
			return true;
		}
		o = Pair.makePair(o.first + other.getWidth(), o.second);
		if (o.first >= position.first && o.first <= position.first + width &&
				o.second >= position.second && o.second <= position.second + height) {
			return true;
		}
		o = Pair.makePair(o.first, o.second + other.getHeight());
		if (o.first >= position.first && o.first <= position.first + width &&
				o.second >= position.second && o.second <= position.second + height) {
			return true;
		}
		o = Pair.makePair(o.first, o.second + other.getHeight());
		if (o.first >= position.first && o.first <= position.first + width &&
				o.second >= position.second && o.second <= position.second + height) {
			return true;
		}
		return false;
	}
}
