package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

import exception.MapObjectNotFoundException;
import model.Entity;
import model.GameBackground;
import model.NormalHero;
import model.Renderable;
import model.Soldier;
import model.map.Floor;
import model.map.MapObject;
import model.map.TestFloor;
import model.utility.ClassResourceUtility;
import model.utility.MapObjectFactory;

public class GameMap {
	
	private static List<Renderable> renderObjects = new CopyOnWriteArrayList<>();
	private static List<Entity> entityObjects = new CopyOnWriteArrayList<>();
	
	public GameMap() {
		
	}
	
	public void initialize() {
		renderObjects.clear();
		entityObjects.clear();
		renderObjects.add(new GameBackground());
		renderObjects.add(new NormalHero(0, 0));
		renderObjects.add(new Soldier(400, 0));
		entityObjects.add((Entity) renderObjects.get(1));
		entityObjects.add((Entity) renderObjects.get(2));
		/*entityObjects.add(new TestFloor(0, 475, 800, 125));
		entityObjects.add(new TestFloor(0, 425, 200, 50));
		renderObjects.add((Renderable) entityObjects.get(1));
		renderObjects.add((Renderable) entityObjects.get(2));
		entityObjects.add(new TestFloor(200, 200, 100, 20));
		renderObjects.add((Renderable) entityObjects.get(3));*/
		GravityManager.startGravity(renderObjects);
		MoveManager.startMove(renderObjects);
	}
	
	public void stop() {
		renderObjects.clear();
		entityObjects.clear();
		GravityManager.stopGravity();
		MoveManager.stopMove();
	}

	public static List<Renderable> getRenderObjects() {
		return renderObjects;
	}

	public static List<Entity> getEntityObjects() {
		return entityObjects;
	}
	
	public void loadMap(URI fileURI) throws MapObjectNotFoundException {
		try (Stream<String> stream = Files.lines(Paths.get(fileURI))) {
			for (String line : (Iterable<String>) stream::iterator) {
				
				line = line.trim();
				
				// Ignore line Comment and empty line
				if (line.length() <= 0 || line.charAt(0) == '#') {
					continue;
				}
		
				int[] param = Stream.of(line.split("\\s+"))
						.mapToInt(Integer::parseInt)
						.toArray();
				int id = param[0];
				int[] objectParam = new int[param.length-1];
				
				for (int i = 1; i < param.length; ++i) {
					objectParam[i-1] = param[i];
				}
				
				try {
					MapObject mo = MapObjectFactory.getMapObjectById(id, objectParam);
					entityObjects.add(mo);
					if (mo instanceof Renderable) {
						renderObjects.add((Renderable) mo);
					}
				} catch (MapObjectNotFoundException e) {
					System.out.println("Load map error, Create map with nothing");
					throw e;
				}
				
			}
		} catch (IOException e) {
			System.err.println("Error : " + "Cannot load file from URI : " + fileURI);
			e.printStackTrace();
		}
	}
	
	public void loadMap(int map) {
		try {
			loadMap(ClassResourceUtility.getResourceURI("map/map" + map + ".dat"));
		} catch (MapObjectNotFoundException e) {
			System.err.println("Internal Map file not found");
			e.printStackTrace();
		}
	}
	
	public static void addEntity(Entity e) {
		entityObjects.add(e);
		if (e instanceof Renderable) {
			renderObjects.add((Renderable) e);
		}
	}
	
	public static void clearEntity() {
		renderObjects.clear();
		entityObjects.clear();
	}
	
}
