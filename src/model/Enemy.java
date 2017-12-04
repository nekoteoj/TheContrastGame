package model;

public abstract class Enemy extends Entity implements GravityAffected, Renderable, Attackable, Movable {

	public Enemy(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

}
