package logic;

import java.util.List;

import model.Movable;
import model.Renderable;

public class MoveManager {
	
	private static Thread move = null;
	
	public static void startMove(List<? extends Renderable> objects) {
		if (move == null || move.isInterrupted()) {
			move = new Thread(() -> {
				for (;;) {
					objects.parallelStream()
						.filter(x -> x instanceof Movable)
						.map(x -> (Movable) x)
						.forEach(Movable::move);
					try {
						Thread.sleep(40);
					} catch (Exception except) {
						System.out.println("Cannot Sleep Thread!!!");
						except.printStackTrace();
					}
				}
			});
		}
		move.start();
	}
	
	public static void stopGravity() {
		move.interrupt();
	}
}
