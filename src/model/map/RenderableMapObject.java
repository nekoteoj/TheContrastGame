package model.map;

import model.Renderable;

public abstract class RenderableMapObject extends MapObject implements Renderable {

	public RenderableMapObject(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

}
