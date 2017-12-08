package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import logic.GameMap;
import model.utility.ClassResourceUtility;
import view.GameCanvas;

public class Boss extends Enemy{
	
	protected static List<Image> imageFrame;
	
	private static final int BOSSWIDTH = 55;
	private static final int BOSSHEIGHT = 80;
	
	static {
		imageFrame = new ArrayList<>();
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/Boss/1R.png"), BOSSWIDTH, BOSSHEIGHT, true, false));
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/Boss/2R.png"), BOSSWIDTH, BOSSHEIGHT, true, false));
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/Boss/1L.png"), BOSSWIDTH, BOSSHEIGHT, true, false));
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/Boss/2L.png"), BOSSWIDTH, BOSSHEIGHT, true, false));
	}
  
	private int walkState;
	
	public Boss(int x, int y) {
		super(x, y, BOSSWIDTH, BOSSHEIGHT);
		vx = 0;
		vy = 0;
		onAir = true;
		this.findDirectionOfHero();
		hp = 200;
		walkState = 2;
	}
	

	
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(imageFrame.get(walkState), position.first - GameCanvas.getCurrentInstance().getStartX(), position.second);
		gc.setLineWidth(3);
		gc.strokeRect(position.first - GameCanvas.getCurrentInstance().getStartX(), position.second, width, height);
	}
	
	@Override
	public void move() {
		super.move();
		if (direction == 1) {
			if (vy != 0) {
				walkState = 1;
			} else if (vx == 0) {
				walkState = 0;
			} else {
				walkState++;
				walkState %= 2;
			}
		} else {
			if (direction == 0) {
				if (vy != 0) {
					walkState = 3;
				} else if (vx == 0) {
					walkState = 2;
				} else {
					walkState = walkState == 2 ? 3 : 2;
				}
			}
		}
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

public Entity fireLightning() {
		Entity lightning = new LightningBolt(5);
GameMap.addEntity(lightning);
return lightning;
	}
}