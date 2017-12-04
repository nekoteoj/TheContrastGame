package model;

public abstract class Hero extends Entity implements GravityAffected, Renderable, Attackable, Movable {

	public Hero(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

}
