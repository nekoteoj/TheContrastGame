package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import logic.GameMap;
import model.utility.ClassResourceUtility;
import view.GameCanvas;

public class Boss extends Enemy{
	public static final int DEFAULT_HP = 200;
	
	public Boss(int x, int y) {
		super(x, y, 50, 50);
		vx = 0;
		vy = 0;
		onAir = true;
		this.findDirectionOfHero();
		this.hp = Boss.DEFAULT_HP;
	}
	

	
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.GOLD);
		gc.fillRect(position.first - GameCanvas.getCurrentInstance().getStartX(), position.second, width, height);
	}
	
	public Bullet fire() {
		Bullet bullet = new CriticalBullet(this.position.first + this.width, this.position.second + this.height / 2, 1, this.direction);
GameMap.addEntity(bullet);
return bullet;
		
	}



	@Override
	public void dead() {
		 new AudioClip(ClassResourceUtility.getResourcePath("sound/boss_explode.wav")).play();
		super.dead();
	}

	public Bullet fireMeteor(int x, int y) {
		Bullet bullet = new MeteorStrike(x, y, 1);
GameMap.addEntity(bullet);
return bullet;
	}

public Bullet fireMeteorArc(int x, int y) {
		Bullet bullet = new MeteorArc(x, y, 1);
GameMap.addEntity(bullet);
return bullet;
	}

public Entity fireLightning(int damage) {
		Entity lightning = new LightningBolt(damage);
GameMap.addEntity(lightning);
return lightning;
	}

public void jump() {
	if (!(this.isOnAir())) {
this.setVy(-20);
this.setOnAir(true);
}
}
}