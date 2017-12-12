package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

import exception.MapObjectNotFoundException;
import javafx.scene.media.AudioClip;
import model.Entity;
import model.GameBackground;
import model.Renderable;
import model.utility.ClassResourceUtility;
import model.utility.MapObjectFactory;

public class GameMap {

	private static List<Renderable> renderObjects = new CopyOnWriteArrayList<>();
	private static List<Entity> entityObjects = new CopyOnWriteArrayList<>();

	private static final AudioClip backgroundMusic;

	static {
		backgroundMusic = new AudioClip(ClassResourceUtility.getResourcePath("sound/gamebackgroundsound.wav"));
	}

	private static int mapLength = 0;

	public GameMap() {

	}

	public void initialize() {
		clearEntity();
		renderObjects.add(new GameBackground());
		GravityManager.startGravity(renderObjects);
		MoveManager.startMove(renderObjects);
		BotManager.startBot();
		mapLength = 0;
		backgroundMusic.setCycleCount(AudioClip.INDEFINITE);
		backgroundMusic.play();
	}

	public void stop() {
		clearEntity();
		GravityManager.stopGravity();
		MoveManager.stopMove();
		BotManager.stopBot();
		mapLength = 0;
		backgroundMusic.stop();
	}

	public static List<Renderable> getRenderObjects() {
		return renderObjects;
	}

	public static List<Entity> getEntityObjects() {
		return entityObjects;
	}

	public void loadMap(URI fileURI) throws MapObjectNotFoundException {
		mapLength = 0;
		try (Stream<String> stream = Files.lines(Paths.get(fileURI))) {
			loadMap(stream);
		} catch (IOException e) {
			System.err.println("Error : " + "Cannot load file from URI : " + fileURI);
			e.printStackTrace();
		}
	}

	public void loadMap(int map) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(ClassResourceUtility.getResourceStream("map/map" + map + ".dat")))) {
			loadMap(br.lines());
		} catch (MapObjectNotFoundException e) {
			System.err.println("Internal Map file invalid");
			e.printStackTrace();
		} catch (IOException e1) {
			System.err.println("Cannot open internal map file");
			e1.printStackTrace();
		}
	}

	public void loadMap(Stream<String> stream) throws MapObjectNotFoundException {
		for (String line : (Iterable<String>) stream::iterator) {

			line = line.trim();

			// Ignore line Comment and empty line
			if (line.length() <= 0 || line.charAt(0) == '#') {
				continue;
			}

			int[] param = Stream.of(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();
			int id = param[0];
			int[] objectParam = new int[param.length - 1];

			for (int i = 1; i < param.length; ++i) {
				objectParam[i - 1] = param[i];
			}

			if (id == 9999 && objectParam.length == 4) {
				new EnemySpawner(objectParam[0], objectParam[1], objectParam[2], objectParam[3]);
				continue;
			} else if (id == 10000 && objectParam.length == 4) {
				EnemySpawner.spawnSoldier(objectParam[0], objectParam[1], objectParam[2], objectParam[3]);
				continue;
			} else if (id == 10001 && objectParam.length == 4) {
				EnemySpawner.spawnTank(objectParam[0], objectParam[1], objectParam[2], objectParam[3]);
				continue;
			} else if (id == 10002 && objectParam.length == 2) {
				EnemySpawner.spawnBoss(objectParam[0], objectParam[1]);
				continue;
			}

			try {
				Entity mo = MapObjectFactory.getMapObjectById(id, objectParam);
				entityObjects.add(mo);
				if (mo instanceof Renderable) {
					renderObjects.add((Renderable) mo);
				}
				if (mo.getWidth() + mo.getPosition().first > mapLength) {
					mapLength = mo.getWidth() + mo.getPosition().first;
				}
			} catch (MapObjectNotFoundException e) {
				System.out.println("Load map error, Create map with nothing");
				throw e;
			}

		}
	}

	public static void addEntity(Entity e) {
		entityObjects.add(e);
		if (e instanceof Renderable) {
			renderObjects.add((Renderable) e);
		}
	}

	public static void removeEntity(Entity e) {
		entityObjects.remove(e);
		if (e instanceof Renderable) {
			renderObjects.remove((Renderable) e);
		}
	}

	public static void clearEntity() {
		renderObjects.clear();
		entityObjects.clear();
	}

	public static int getMapLength() {
		return mapLength;
	}

}
