package model;

public interface Attackable {
	public int getHp();

	public void setHp(int amount);

	public void decreaseHp(int amount);

	public void dead();
}
