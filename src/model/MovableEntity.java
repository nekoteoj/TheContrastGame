package model;

import logic.GameMap;

public abstract class MovableEntity extends Entity  implements Movable{
	protected int vx;
	protected int vy;
	protected boolean onAir;
	protected int direction; //1 right 0 left

	public MovableEntity(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	


@Override
	public void move() {
		// TODO Auto-generated method stub
		if (vx == 0 && vy == 0) {
			return;
		}
		if (vx != 0) {
			direction = vx > 0 ? 1 : 0;
		}
		position.second += vy;
		position.first += vx;
		if (position.second < 0) {
			position.second = 0;
			vy = 0;
		}
		if (position.first < 0) {
			position.first = 0;
			vx = 0;
		}
		if (position.first + getWidth() > GameMap.getMapLength()) {
			position.first = GameMap.getMapLength() - getWidth();
			vx = 0;
		}
		checkCollide();
	}


public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

	

@Override
	public int getDirection() {
		return direction;
	}

public abstract void checkCollide();
public abstract void fixCollide(Entity other);

}
