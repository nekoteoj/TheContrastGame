package model;

import javafx.scene.canvas.GraphicsContext;
import logic.GameMap;

public class LightningBolt implements Renderable{
	
	public LightningBolt(int damage) {
	GameMap.getEntityObjects().parallelStream().filter(x -> x instanceof Hero).forEach(e -> ((Hero)e).decreaseHp(damage));	
	
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

}
