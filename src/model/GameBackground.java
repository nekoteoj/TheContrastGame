package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.utility.ClassResourceUtility;

public class GameBackground implements Renderable {
	
	private static Image baseBackground;
	
	static {
		baseBackground = new Image(ClassResourceUtility.getResourcePath("img/background.jpg"), 800, 600, true, true);
	}
	
	public GameBackground() {
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(baseBackground, 0, 0);
	}

}
