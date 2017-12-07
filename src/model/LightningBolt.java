package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import logic.GameMap;
import model.utility.ClassResourceUtility;

public class LightningBolt implements Renderable{

	private static AudioClip lightningSound;
	static {
	lightningSound = new AudioClip(ClassResourceUtility.getResourcePath("sound/lightning.wav"));
	}
	
	public LightningBolt(int damage) {
	GameMap.getEntityObjects().parallelStream().filter(x -> x instanceof Hero).forEach(e -> ((Hero)e).decreaseHp(damage));	
lightningSound.play();	
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

}
