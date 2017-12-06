package logic;

import java.util.List;

import model.GravityAffected;
import model.Renderable;

public class GravityManager {

	private static Thread gravity = null;
	private static boolean isGravityRunning = false;

	public static void startGravity(List<? extends Renderable> objects) {
		isGravityRunning = true;
		gravity = new Thread(() -> {
			while (isGravityRunning) {
				objects.parallelStream().filter(x -> x instanceof GravityAffected).map(x -> (GravityAffected) x)
						.forEach(GravityAffected::moveDownGravity);
				try {
					Thread.sleep(100);
				} catch (Exception except) {
					System.out.println("Cannot Sleep Thread!!!");
					except.printStackTrace();
				}
			}
		});
		gravity.start();
	}

	public static void stopGravity() {
		isGravityRunning = false;
	}
}
