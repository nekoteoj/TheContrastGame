package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.GameMap;
import model.utility.Pair;
import view.GameCanvas;

public class NormalBullet extends Bullet {
	
	// 0 to attack enemy
	// 1 to attack hero
	protected int target;
	protected int direction;
	protected int vx;
	protected Pair<Integer, Integer> startPosition;

	public NormalBullet(int x, int y, int target, int direction) {
		super(x, y, 4, 4);
		this.target = target;
		startPosition = Pair.makePair(x, y);
		this.direction = direction;
		if (direction == 1) {
			vx = 15;
		} else if (direction == 0) {
			vx = -15;
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillOval(position.first - 2 - GameCanvas.getCurrentInstance().getStartX(), position.second - 2, 4, 4);
	}

	@Override
	public void move() {
		position.first += vx;
		if (Math.abs(position.first - startPosition.first) >= 900) {
			dead();
		}
		checkCollide();
	}

	@Override
	public void setVx(int vx) {
		return;
	}

	@Override
	public void setVy(int vy) {
		return;
	}

	@Override
	public int getVx() {
		return vx;
	}

	@Override
	public int getVy() {
		return 0;
	}

	@Override
	public void checkCollide() {
		for (Entity e : GameMap.getEntityObjects()) {
			if (this != e && e.isCollide(this)) {
				if (target == 0 && e instanceof Enemy) {
					fixCollide(e);
					dead();
				} else if (target == 1 && e instanceof Hero) {
					fixCollide(e);
					dead();
				}
			}
		}
	}

	@Override
	public void fixCollide(Entity other) {
		if (!(other instanceof Attackable)) {
			return;
		}
		((Attackable) other).decreaseHp(2);
	}

	@Override
	public int getDirection() {
		return direction;
	}

	public void dead() {
		GameMap.getEntityObjects().remove(this);
		GameMap.getRenderObjects().remove(this);
	}

}
