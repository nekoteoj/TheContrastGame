package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import model.utility.ClassResourceUtility;

public class HalfMpPotion extends Item {
	private static AudioClip soundPotion;

	static {
		soundPotion = new AudioClip(ClassResourceUtility.getResourcePath("sound/half_potion.wav"));
	}

		public HalfMpPotion(int x, int y) {
			super(x, y, 10, 10);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void draw(GraphicsContext gc) {
			 gc.setFill(Color.PINK);
			gc.fillOval(this.position.first, this.position.second, 10, 10);
		}

		@Override
		public void use(Hero hero) {
			 hero.increaseMp(50);
			 soundPotion.play();
			super.use(hero);
		}
		
		

	}
