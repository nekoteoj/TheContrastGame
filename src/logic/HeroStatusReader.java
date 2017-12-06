package logic;

import model.Hero;
import model.NormalHero;

public class HeroStatusReader {
	
	private int maxHp = 100;
	private int maxMp = 100;
	Hero hero;
	
	public HeroStatusReader(Hero hero) {
		if (hero instanceof NormalHero) {
			maxHp = NormalHero.DEFAULT_HP;
			maxMp = NormalHero.DEFAULT_MP;
		}
		this.hero = hero;
	}
	
	public int readHp() {
		return hero.getHp();
	}
	
	public int readMaxHp() {
		return maxHp;
	}
	
	public int readMp() {
		return hero.getIntMp();
	}
	
	public int readMaxMp() {
		return maxMp;
	}
	
}
