package logic;

import java.util.List;
import java.util.Random;

import model.Boss;
import model.Entity;
import model.Hero;
import model.Soldier;
import model.Tank;
import view.GameCanvas;

public class BotManager {

private Hero hero;
private Random random = new Random();
private int internalTick;



public BotManager () {
	this.internalTick = 0;			
}


public void handleCall () {
	if (this.internalTick < 100) {
		this.internalTick += 1;
		
		return;
		
	}
	
	if (this.internalTick % 5 != 0) {
	this.internalTick += 1;	
	return;
	}
	
	this.hero = (Hero) GameMap.getEntityObjects().parallelStream().filter(x -> x instanceof Hero).findAny().get();
	
List<Entity> l = GameMap.getEntityObjects();
for(Entity e: l) {
	if (this.isInsideScreen(e)) {
	if (e instanceof Soldier) {
		this.soldierLogic((Soldier)e);
	} else if (e instanceof Tank) {
	this.tankLogic((Tank)e);	
	}
	}
	
}
this.internalTick += 1;
}

public void soldierLogic(Soldier e) {
	int x = random.nextInt(100);
	System.out.println("(" + this.internalTick + ", " + e.getPosition().first + ", " + e.getPosition().second + ", " + e.getVx() + ", " + e.getVy() + ", " + e.getHp() + ")");
	if (x < 97) {
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
	int x = random.nextInt(150);
	if (x >= 146) {
		e.findDirectionOfHero(hero);
		e.fire();
	} else {
	e.findDirectionOfHero(hero);	
	}
}

public void bossLogic() {
	
}

public boolean isInsideScreen(Entity e) {
if ((e.getPosition().first-e.getWidth() >= GameCanvas.getCurrentInstance().getStartX()) && (e.getPosition().first <= GameCanvas.getCurrentInstance().getStartX()+800)) {	
	return true;
} else {
	return false;

}
}

}


