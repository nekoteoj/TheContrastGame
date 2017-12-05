package logic;

import model.Hero;
import model.NormalHero;

public class HeroStatusReader {
	
	private int maxHp = 100;
	Hero hero;
	
	public HeroStatusReader(Hero hero) {
		if (hero instanceof NormalHero) {
			maxHp = NormalHero.DEFAULT_HP;
		}
		this.hero = hero;
	}
	
	public int readHp() {
		return hero.getHp();
	}
	
	public int readMaxHep() {
		return maxHp;
	}
	
}
