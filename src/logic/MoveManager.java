package logic;

import java.util.List;

import model.Hero;
import model.Movable;
import model.Renderable;
import view.GameCanvas;

public class MoveManager {
	
	private static Thread move = null;
	private static boolean isMoveRunning = false;
	
	public static void startMove(List<? extends Renderable> objects) {
		isMoveRunning = true;
			move = new Thread(() -> {
				while (isMoveRunning) {
					objects.parallelStream()
						.filter(x -> x instanceof Movable)
						.map(x -> (Movable) x)
						.forEach(x -> {
							x.move();
							if (x instanceof Hero) {
								GameCanvas.getCurrentInstance().checkStartPoint((Hero) x);
							}
						});
					try {
						Thread.sleep(40);
					} catch (Exception except) {
						System.out.println("Cannot Sleep Thread!!!");
						except.printStackTrace();
					}
				}
			});
		move.start();
	}
	
	public static void stopMove() {
		isMoveRunning = false;
	}
}
