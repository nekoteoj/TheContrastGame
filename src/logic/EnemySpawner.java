package logic;

import java.util.Random;

import model.Boss;
import model.Soldier;
import model.Tank;

public class EnemySpawner {
	private int min;
	private int max;
	private boolean boss;
	Random random = new Random();
	
	public EnemySpawner(int min, int max, int soldier, int tank, boolean boss) {
	this.min = min;
	this.max = max;
	this.boss = boss;
	this.spawnSoldier(soldier);
	this.spawnTank(tank);
	this.spawnBoss();
	}
	
		public int checkFloorLevel(int x) {
	return 0; //this should be checking that at position x the floor is at what y value. 	
	}
		
		public void spawnSoldier(int soldier) {
			
			while (soldier > 0) {
				int x;
				if (boss) {
			x = random.nextInt(this.max-800)+min;
				} else {
					x = random.nextInt(this.max-200)+min;		
				}
				GameMap.addEntity(new Soldier(x, this.checkFloorLevel(x)));
				soldier -= 1;
			}
		}

public void spawnTank(int tank) {
			
			while (tank> 0) {
				int x;
				if (boss) {
			x = random.nextInt(this.max-800)+min;
				} else {
					x = random.nextInt(this.max-200)+min;		
				}
				GameMap.addEntity(new Tank(x, this.checkFloorLevel(x)));
				tank -= 1;
			}
		}

public void spawnBoss() {
	GameMap.addEntity(new Boss(this.max-300, this.checkFloorLevel(this.max-300)));
}

}
