package model;

import javafx.scene.shape.Rectangle;
import model.utility.Pair;

public abstract class Entity {

	protected Pair<Integer, Integer> position;
	protected int width;
	protected int height;
	protected Rectangle entityRect;

	public Entity(int x, int y, int width, int height) {
		position = Pair.makePair(x, y);
		this.width = width;
		this.height = height;
		entityRect = new Rectangle(x, y, width, height);
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
		return position.first < other.position.first + other.width && position.first + width > other.position.first
				&& position.second < other.position.second + other.height
				&& position.second + height > other.position.second;
	}
}
