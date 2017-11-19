package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameBackground implements Renderable {
	
	private static Image baseBackground;
	
	static {
		baseBackground = new Image("background.jpg", 800, 600, true, true);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(baseBackground, 0, 0);
	}

}
