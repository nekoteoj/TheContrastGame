package model.utility;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import model.mainmenu.Rain;

public class RainMaker {
	
	private static List<Rain> rainList;
	private static Random random;
	
	static {
		rainList = new CopyOnWriteArrayList<>();
		random = new Random();	
		for (int i = 0; i < 75; i++) {
			rainList.add(new Rain(random.nextInt(801), random.nextInt(401)));
		}
	}
	
	public static List<Rain> getRainList() {	
		return rainList;
	}
	
}
