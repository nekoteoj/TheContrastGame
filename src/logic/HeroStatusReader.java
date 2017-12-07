package logic;

import model.BuffHero;
import model.Hero;
import model.NormalHero;

public class HeroStatusReader {
	
	private int maxHp = 100;
	private int maxMp = 100;
	Hero hero = null;
	
	public HeroStatusReader() {
		if (hero instanceof NormalHero) {
			maxHp = NormalHero.DEFAULT_HP;
			maxMp = NormalHero.DEFAULT_MP;
		}
	}
	
	public int readHp() {
		ensureHero();
		if (hero != null) {
			return hero.getHp();
		}
		return 100;
	}
	
	public int readMaxHp() {
		ensureHero();
		if (hero != null) {
			return maxHp;
		}
		return 100;
	}
	
	public int readMp() {
		ensureHero();
		if (hero != null) {
			return hero.getIntMp();
		}
		return 100;
	}
	
	public int readMaxMp() {
		ensureHero();
		if (hero != null) {
			return maxMp;
		}
		return 100;
	}
	
	public String getHeroMode() {
		ensureHero();
		if (hero != null) {
			if (hero instanceof NormalHero) {
				return "Normal Mode";
			} else if (hero instanceof BuffHero) {
				return "Buff Mode";
			}
		}
		return "Initializing";
	}
	
	private void ensureHero() {
		hero = GameMap.getEntityObjects()
				.parallelStream()
				.filter(x -> x instanceof Hero)
				.map(x -> (Hero) x)
				.findAny()
				.orElse(null);
	}
	
}
