package model.map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.utility.ClassResourceUtility;
import view.GameCanvas;

public class Box extends RenderableMapObject {
	
	private static final int BOXWIDTH = 50;
	private static final int BOXHEIGHT = 50;
	
	private static final Image image;
	
	static {
		image = new Image(ClassResourceUtility.getResourcePath("img/model/map/Box/1.png"), BOXWIDTH, BOXHEIGHT, true, true);
	}
	
	private int amount;
	
	public Box(int x, int y, int amount) {
		super(x, y, BOXWIDTH * amount, BOXHEIGHT);
		this.amount = amount;
	}

	@Override
	public void draw(GraphicsContext gc) {
		for (int i = 0; i < amount; i++) {
			gc.drawImage(image, position.first + BOXWIDTH * i - GameCanvas.getCurrentInstance().getStartX(), position.second);
		}
	}
}
