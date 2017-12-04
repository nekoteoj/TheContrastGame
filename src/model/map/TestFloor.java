package model.map;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Renderable;
import view.GameCanvas;

public class TestFloor extends Floor implements Renderable {

	Random ran = new Random();
	int r = ran.nextInt(256);
	int g = ran.nextInt(256);
	int b = ran.nextInt(256);
	
	public TestFloor(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setStroke(Color.rgb(r, g, b));
		gc.setLineWidth(4);
		gc.strokeRect(position.first - GameCanvas.getCurrentInstance().getStartX(), position.second, width, height);
	}

}
