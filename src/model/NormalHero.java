package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.utility.ClassResourceUtility;
import view.GameCanvas;

public class NormalHero extends Hero {

	protected static List<Image> imageFrame;

	static {
		imageFrame = new ArrayList<>();
		// imageFrame.add(new
		// Image(ClassResourceUtility.getResourcePath("img/hero.png"), 70, 99, true,
		// true));
		imageFrame.add(
				new Image(ClassResourceUtility.getResourcePath("img/model/NormalHero/1R.png"), 50, 77, true, false));
		imageFrame.add(
				new Image(ClassResourceUtility.getResourcePath("img/model/NormalHero/2R.png"), 50, 77, true, false));
		imageFrame.add(
				new Image(ClassResourceUtility.getResourcePath("img/model/NormalHero/1L.png"), 50, 77, true, false));
		imageFrame.add(
				new Image(ClassResourceUtility.getResourcePath("img/model/NormalHero/2L.png"), 50, 77, true, false));
	}

	public static int DEFAULT_HP = 100;
	public static int DEFAULT_MP = 100;
	private int walkTick = 0;
	private int walkState = 0;

	public NormalHero(int x, int y) {
		super(x, y, 50, 77);
		vx = 0;
		vy = 0;
		onAir = true;
		direction = 1;
		hp = 100;
		mp = 100;
	}

	public NormalHero(int x, int y, int vx, int vy, int direction) {
		this(x, y);
		this.vx = vx;
		this.vy = vy;
		this.direction = direction;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(imageFrame.get(walkState), position.first - GameCanvas.getCurrentInstance().getStartX(),
				position.second);
		// gc.setLineWidth(3);
		// gc.strokeRect(position.first - GameCanvas.getCurrentInstance().getStartX(),
		// position.second, width, height);
	}

	@Override
	public void move() {
		super.move();
		if (direction == 1) {
			if (vy != 0) {
				walkState = 1;
			} else if (vx == 0) {
				walkState = 0;
			} else {
				if (walkTick > 2) {
					walkState++;
					walkState %= 2;
					walkTick = 0;
				} else {
					walkTick++;
				}
			}
		} else {
			if (direction == 0) {
				if (vy != 0) {
					walkState = 3;
				} else if (vx == 0) {
					walkState = 2;
				} else {
					if (walkTick > 2) {
						walkState = walkState == 2 ? 3 : 2;
						walkTick = 0;
					} else {
						walkTick++;
					}
				}
			}
		}
	}
}
