package logic;

import java.util.Random;

import model.Boss;
import model.Soldier;
import model.Tank;

public class EnemySpawner {
	static Random random = new Random();

	public EnemySpawner(int min, int max, int soldier, int tank) {
		EnemySpawner.spawnSoldier(min, max, soldier, 0);
		EnemySpawner.spawnTank(min, max, tank, 0);
		EnemySpawner.spawnBoss(max - 400, 0);
	}

	public int checkFloorLevel(int x) {
		return 0; // this should be checking that at position x the floor is at what y value.
	}

	public static void spawnSoldier(int x_min, int x_max, int amount) {
		EnemySpawner.spawnSoldier(x_min, x_max, amount, 0);
	}

	public static void spawnSoldier(int x_min, int x_max, int amount, int y_floor) {

		while (amount > 0) {
			int x;

			x = random.nextInt(x_max - x_min) + x_min;
			GameMap.addEntity(new Soldier(x, y_floor));
			amount -= 1;
		}
	}

	public static void spawnTank(int x_min, int x_max, int amount) {
		EnemySpawner.spawnTank(x_min, x_max, amount, 0);
	}

	public static void spawnTank(int x_min, int x_max, int amount, int y_floor) {

		while (amount > 0) {
			int x;

			x = random.nextInt(x_max - x_min) + x_min;
			GameMap.addEntity(new Tank(x, y_floor));
			amount -= 1;
		}
	}

	public static void spawnBoss(int x, int y) {
		GameMap.addEntity(new Boss(x, y));
	}

}
