package logic;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import model.GameBackground;
import model.NormalHero;
import model.Renderable;

public class GameMap {
	
	private static List<Renderable> renderObjects = new CopyOnWriteArrayList<>();
	
	public GameMap() {
		
	}
	
	public void initialize() {
		renderObjects.add(new GameBackground());
		renderObjects.add(new NormalHero());
		GravityManager.startGravity(renderObjects);
		MoveManager.startMove(renderObjects);
	}

	public static List<Renderable> getRenderObjects() {
		return renderObjects;
	}
	
}
