package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import logic.GameMap;
import main.App;
import model.utility.ClassResourceUtility;
import view.GameCanvas;

public class LightningBolt extends Entity implements Renderable{

	private static AudioClip lightningSound;
	private static List<Image> imageFrame;
	
	static {
		imageFrame = new ArrayList<>();
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/LightningBolt/1.png"), App.SCREEN_WIDTH, App.SCREEN_HEIGHT, true, false));
		imageFrame.add(new Image(ClassResourceUtility.getResourcePath("img/model/LightningBolt/2.png"), App.SCREEN_WIDTH, App.SCREEN_HEIGHT, true, false));
		lightningSound = new AudioClip(ClassResourceUtility.getResourcePath("sound/lightning.wav"));
	}
	
	private int imageTick = 0;
	private int imageState = 0;
	
	public LightningBolt(int damage) {
		super(0,0, App.SCREEN_WIDTH, App.SCREEN_HEIGHT);
	GameMap.getEntityObjects().parallelStream().filter(x -> x instanceof Hero).forEach(e -> ((Hero)e).decreaseHp(damage));	
lightningSound.play();	
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (imageTick == 45) {
			dead();
		}
		imageState = (imageTick / 15) % 2;
		gc.setGlobalAlpha(0.6);
		gc.drawImage(imageFrame.get(imageState), 0, 0);
		gc.setGlobalAlpha(1.0);
		imageTick++;	
	}

	private void dead() {
		GameMap.removeEntity(this);
	}

}
