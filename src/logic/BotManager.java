package logic;

import java.util.List;
import java.util.Random;

import exception.MapObjectNotFoundException;
import main.App;
import model.Boss;
import model.Bullet;
import model.Enemy;
import model.Entity;
import model.Hero;
import model.Item;
import model.Soldier;
import model.Tank;
import model.utility.MapObjectFactory;
import view.GameCanvas;

public class BotManager {

	private static boolean isBotRunning = false;

	private Hero hero;
	private Random random = new Random();
	private boolean isFirstInitialize;
	private int mapLength;
	private boolean isEnemyRemaining;
	private int internalTick;
	private boolean isBoss;

	public BotManager() {
		this.internalTick = 0;
		this.mapLength = GameMap.getMapLength() > 0 ? GameMap.getMapLength() : 1600;
		this.isFirstInitialize = true;
		this.isBoss = false;
	}

	public void handleCall() throws InterruptedException {
		if (this.isFirstInitialize) {
			Thread.sleep(1500);

			this.isFirstInitialize = false;
			return;

		}
		this.hero = (Hero) GameMap.getEntityObjects().parallelStream().filter(x -> x instanceof Hero).findAny().get();
		this.isEnemyRemaining = false;
		List<Entity> l = GameMap.getEntityObjects();
		for (Entity e : l) {
			if (!(isEnemyRemaining) && (e instanceof Enemy) && !(e instanceof Boss)) {
				this.isEnemyRemaining = true;
			}
			if (e instanceof Item) {
				((Item) e).decreaseTimeToLive(1);
			}
			if (this.isInsideScreen(e)) {
				if (e instanceof Soldier) {
					this.soldierLogic((Soldier) e);
				} else if (e instanceof Tank) {
					this.tankLogic((Tank) e);
				} else if (e instanceof Boss) {
					this.bossLogic((Boss) e);
				}
			}

		}
		spawnItem();
		this.internalTick += 1;
		if (!(this.isBoss) && !(this.isEnemyRemaining)) {
			EnemySpawner.spawnBoss(this.mapLength - 450, 100);
			this.isBoss = true;
		}
	}

	public void soldierLogic(Soldier e) {
		int x = random.nextInt(200);
		// System.out.println("(" + this.internalTick + ", " + e.getPosition().first +
		// ", " + e.getPosition().second + ", "
		// + e.getVx() + ", " + e.getVy() + ", " + e.getHp() + ")");
		if (x < 193) {
			e.findDirectionOfHero(hero);
			if (Math.abs(hero.getPosition().first + hero.getWidth() - e.getPosition().first) >= 15) {
				e.forward(1);
			} else {
				e.halt();
			}
		} else {
			e.halt();
			e.fire();
		}

	}

	public void tankLogic(Tank e) {
		int x = random.nextInt(200);
		if (x >= 195) {
			e.findDirectionOfHero(hero);
			e.fire();
		} else {
			e.findDirectionOfHero(hero);
		}
	}

	public void bossLogic(Boss boss) {
		int x = random.nextInt(200);
		// System.out.println("Boss HP:" + boss.getHp());
		if (boss.getHp() > Boss.DEFAULT_HP / 2) {
			if (x >= 194) {
				boss.fireMeteor(hero.getPosition().first, 0);
			} else if (x >= 192) {
				boss.fireLightning(3);
			} else {
				boss.findDirectionOfHero(hero);
			}
		} else {
			if ((this.isBulletNear(boss)) && (x >= 100)) {
				boss.jump();
				return;
			}
			if (x >= 192) {
				boss.fireMeteorArc(hero.getPosition().first, 0);
			} else if (x >= 190) {
				boss.fireLightning(5);
			} else {
				boss.findDirectionOfHero(hero);
			}
		}
	}

	public void spawnItem() {
		// System.out.println(internalTick);
		if (internalTick != 0 && (this.internalTick % 500 == 0 || (this.internalTick + 50) % 500 == 0
				|| (isBoss && this.internalTick % 200 == 0))) {
			int x = GameCanvas.getCurrentInstance().getStartX() + 50 + random.nextInt(App.SCREEN_WIDTH - 100);
			int y = 100 + random.nextInt(300);
			int id = 200 + random.nextInt(4);
			if (hero.getHp() < 20) {
				id = (random.nextInt(2) == 0) ? 200 : 202;
			}
			try {
				GameMap.addEntity(MapObjectFactory.getMapObjectById(id, x, y));
			} catch (MapObjectNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isBulletNear(Entity e) {
		List<Entity> l = GameMap.getEntityObjects();
		for (Entity b : l) {
			if (b instanceof Bullet) {
				if ((b.getPosition().first - e.getPosition().first < 0)
						&& (b.getPosition().first - e.getPosition().first > -60) && (((Bullet) b).getVx() > 0)) {
					return true;
				} else if ((b.getPosition().first - e.getPosition().first > 0)
						&& (b.getPosition().first - e.getPosition().first < 60) && (((Bullet) b).getVx() < 0)) {
					return true;
				} else {
					continue;
				}
			}
		}
		return false;
	}

	public boolean isInsideScreen(Entity e) {
		if ((e.getPosition().first - e.getWidth() >= GameCanvas.getCurrentInstance().getStartX())
				&& (e.getPosition().first <= GameCanvas.getCurrentInstance().getStartX() + 800)) {
			return true;
		} else {
			return false;

		}
	}

	public static void startBot() {
		BotManager botManager = new BotManager();
		Thread botThread;
		isBotRunning = true;
		botThread = new Thread(() -> {
			while (isBotRunning) {
				try {
					botManager.handleCall();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		botThread.start();
	}

	public static void stopBot() {
		isBotRunning = false;
	}

}
