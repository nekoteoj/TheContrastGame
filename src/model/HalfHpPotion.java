package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import model.utility.ClassResourceUtility;
import view.GameCanvas;

public class HalfHpPotion extends Item {
	private static AudioClip soundPotion;

	private static Image image;
	private static final int POTIONWIDTH = 20;
	private static final int POTIONHEIGHT = 30;
	
	static {
		soundPotion = new AudioClip(ClassResourceUtility.getResourcePath("sound/half_potion.wav"));
		image = new Image(ClassResourceUtility.getResourcePath("img/model/HalfHpPotion/1.png"), POTIONWIDTH, POTIONHEIGHT, true, false);
	}

		public HalfHpPotion(int x, int y) {
			super(x, y, POTIONWIDTH, POTIONHEIGHT);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void draw(GraphicsContext gc) {
//			 gc.setFill(Color.PINK);
//			 gc.fillRect(this.position.first - GameCanvas.getCurrentInstance().getStartX(), this.position.second, POTIONWIDTH, POTIONHEIGHT);
			 gc.drawImage(image, position.first - GameCanvas.getCurrentInstance().getStartX(), position.second);
		}

		@Override
		public void use(Hero hero) {
			 hero.increaseHp(50);
			 soundPotion.play();
			super.use(hero);
		}
		
		

	}
