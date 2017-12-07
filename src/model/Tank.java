package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import logic.GameMap;
import model.utility.ClassResourceUtility;
import view.GameCanvas;

public class Tank extends Enemy {

	public Tank(int x, int y) {
		super(x, y, 50, 100);
		vx = 0;
		vy = 0;
		onAir = false;
		this.findDirectionOfHero();
		hp = 50;
	}
	

	
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.AQUA);
		gc.fillRect(position.first - GameCanvas.getCurrentInstance().getStartX(), position.second, width, height);
	}
	
	public Bullet fire() {
		Bullet bullet = new TankBullet(this.position.first + this.width, this.position.second + this.height / 2, 1, this.direction);
GameMap.addEntity(bullet);
return bullet;
		
	}



	@Override
	public void dead() {
		 new AudioClip(ClassResourceUtility.getResourcePath("sound/tank_explode.wav")).play();
		super.dead();
	}

	@Override
	public void decreaseHp(int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkCollide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fixCollide(Entity other) {
		// TODO Auto-generated method stub
		
	}
}
