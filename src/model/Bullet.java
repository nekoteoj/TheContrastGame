package model;



import logic.GameMap;
import model.utility.Pair;

public abstract class Bullet extends MovableEntity implements Renderable{
	protected int target; //0 to attack enemy, 1 to attack hero
	protected Pair<Integer, Integer> startPosition;
	protected int attackPoint;
	protected int velocity;
	
	
	
	public Bullet(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public void dead() {
		GameMap.getEntityObjects().remove(this);
		GameMap.getRenderObjects().remove(this);
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
	public void checkCollide() {
		for (Entity e : GameMap.getEntityObjects()) {
			if (this != e && e.isCollide(this)) {
				if (target == 0 && e instanceof Enemy) {
					fixCollide(e);
					dead();
					break;
				} else if (target == 1 && e instanceof Hero) {
					fixCollide(e);
					dead();
					break;
				}
			}
		}
	}

	@Override
	public void fixCollide(Entity other) {
		if (!(other instanceof Attackable)) {
			return;
		}
		((Attackable) other).decreaseHp(this.attackPoint);
	}

	

	

}
