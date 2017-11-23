package logic;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import model.Entity;
import model.GameBackground;
import model.NormalHero;
import model.Renderable;
import model.map.Floor;
import model.map.TestFloor;

public class GameMap {
	
	private static List<Renderable> renderObjects = new CopyOnWriteArrayList<>();
	private static List<Entity> entityObjects = new CopyOnWriteArrayList<>();
	
	public GameMap() {
		
	}
	
	public void initialize() {
		renderObjects.add(new GameBackground());
		renderObjects.add(new NormalHero(0, 0));
		entityObjects.add((Entity) renderObjects.get(1));
		entityObjects.add(new TestFloor(0, 475, 800, 125));
		entityObjects.add(new TestFloor(0, 425, 200, 50));
		renderObjects.add((Renderable) entityObjects.get(1));
		renderObjects.add((Renderable) entityObjects.get(2));
		entityObjects.add(new TestFloor(200, 200, 100, 50));
		renderObjects.add((Renderable) entityObjects.get(3));
		GravityManager.startGravity(renderObjects);
		MoveManager.startMove(renderObjects);
	}

	public static List<Renderable> getRenderObjects() {
		return renderObjects;
	}

	public static List<Entity> getEntityObjects() {
		return entityObjects;
	}
	
}
