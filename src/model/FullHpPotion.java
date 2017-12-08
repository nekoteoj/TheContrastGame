package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import model.utility.ClassResourceUtility;

public class FullHpPotion extends Item {
private static AudioClip soundPotion;

static {
	soundPotion = new AudioClip(ClassResourceUtility.getResourcePath("sound/full_potion.wav"));
}

	public FullHpPotion(int x, int y) {
		super(x, y, 10, 10);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc) {
		 gc.setFill(Color.BLUE);
		gc.fillRect(this.position.first, this.position.second, 10, 10);
	}

	@Override
	public void use() {
		 this.getHero().increaseHp(100);
		 soundPotion.play();
		super.use();
	}
	
	

}
