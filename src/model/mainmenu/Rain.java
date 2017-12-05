package model.mainmenu;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rain {
	
	private static int w = 7;
	private static int h = 20;
	private static Random random = new Random();
	
	private int rainTick; 
	
	private int x;
	private int y;
	
	public Rain(int x, int y) {
		this.x = x;
		this.y = y;
		this.rainTick = 0;
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.rgb(242, 109, 125));
		gc.fillRect(x, y, w, h);
		move();
	}
	
	public void move() {
		if (rainTick > 3) {
			rainTick = 0;
			y += random.nextInt(30) + h;
			if (y >= 400) {
				y = -h;
			}
			x += random.nextInt(21) - 10;
			x = (x + 800) % 800;
		}
		rainTick++;
	}
	
}
