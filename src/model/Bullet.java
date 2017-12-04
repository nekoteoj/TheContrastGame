package model;

public abstract class Bullet extends Entity implements GravityAffected, Renderable, Attackable, Movable{

	public Bullet(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

}
