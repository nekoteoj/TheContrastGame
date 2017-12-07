package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import logic.GameMap;
import model.utility.ClassResourceUtility;
import view.GameCanvas;

public class LightningBolt extends Entity implements Renderable{

	private static AudioClip lightningSound;
	static {
	lightningSound = new AudioClip(ClassResourceUtility.getResourcePath("sound/lightning.wav"));
	}
	
	public LightningBolt(int damage) {
		super(0,0,(int) GameCanvas.getCurrentInstance().getWidth(), (int) GameCanvas.getCurrentInstance().getHeight());
	GameMap.getEntityObjects().parallelStream().filter(x -> x instanceof Hero).forEach(e -> ((Hero)e).decreaseHp(damage));	
lightningSound.play();	
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

}
