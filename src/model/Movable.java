package model;

public interface Movable {
	public void move();

	public void setVx(int vx);

	public void setVy(int vy);

	public int getVx();

	public int getVy();

	public void checkCollide();

	public void fixCollide(Entity other);

	public int getDirection();
}
