package model;

import logic.GameMap;

public class MeteorArc extends MeteorStrike {

	@Override
	public void move() {
		int heroCenter = ((Hero) GameMap.getEntityObjects().parallelStream().filter(x -> x instanceof Hero).findAny().get()).getPosition().first + 25;
		System.out.println("HeroCenter:" + heroCenter + ", meteorCenter:" + this.centerX);
				if (heroCenter < this.centerX) {
				position.first -= 1;
				} else if (heroCenter > this.centerX) {
				position.first += 1;
				}
				position.first = position.first < 0? 0: position.first;
				this.centerX = (position.first+ (position.first +this.meteorWidth))/2;
						position.second += vy;
						if (Math.abs(position.second - startPosition.second) >= 600) {
							dead();
						}
						checkCollide();
		
	}

	public MeteorArc(int x, int y, int target) {
		super(x, y, target);
		// TODO Auto-generated constructor stub
	}

}
