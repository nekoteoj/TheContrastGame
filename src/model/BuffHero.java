package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import logic.GameMap;
import main.App;
import model.utility.ClassResourceUtility;
import view.GameCanvas;

public class BuffHero extends Hero {

protected static List<Image> imageFrame;

	private static AudioClip buffSound;
	
	static {
		imageFrame = new ArrayList<>();
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/BuffHero/1R.png"), 64, 81, true, false));
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/BuffHero/2R.png"), 64, 81, true, false));
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/BuffHero/1L.png"), 64, 81, true, false));
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/BuffHero/2L.png"), 64, 81, true, false));
		buffSound = new AudioClip(ClassResourceUtility.getResourcePath("sound/buffsound.mp3"));
	}

	
	public static int DEFAULT_HP = 100;
	
	private int walkState;
	private int effectTick;
	
	public BuffHero(int x, int y, int vx, int vy, int direction) {
		super(x, y, 64, 81);
		this.vx = vx;
		this.vy = vy;
		onAir = true;
		this.direction = direction;
		hp = 100;
		walkState = (direction == 1 ? 0 : 2);
		buffSound.play();
		effectTick = 0;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		if (effectTick < 15) {
			effectTick++;
			gc.setFill(Color.rgb(255, 255, 0, effectTick / 15.0 * 0.4 + 0.6));
			gc.fillRect(position.first - 10 - GameCanvas.getCurrentInstance().getStartX(), 0, width + 20, App.SCREEN_HEIGHT);
		}
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
	
	@Override
	public void increaseMp(double amount) {
		 this.setMp(100.0);
		
	}

	@Override
	public void decreaseMp(double amount) {
		 
		return;
	}

	@Override
	public void decreaseHp(int amount) {
		 
		return;
	}
	


	
	
	

	}
