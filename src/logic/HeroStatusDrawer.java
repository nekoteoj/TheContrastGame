package logic;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.App;

public class HeroStatusDrawer {

	private static final Font font = new Font("Press Start 2P", 13);

	private HeroStatusReader reader;
	private GraphicsContext gc;

	public HeroStatusDrawer() {
		reader = new HeroStatusReader();
	}

	public void draw(GraphicsContext gc) {
		this.gc = gc;
		drawBackground();
		drawStatus();
	}

	private void drawBackground() {
		gc.setFill(Color.rgb(50, 50, 50, 0.8));
		gc.fillRect(0, App.SCREEN_HEIGHT - 50, App.SCREEN_WIDTH, 50);
	}

	private void drawStatus() {
		gc.setFill(Color.WHITE);
		gc.setFont(font);
		gc.setTextBaseline(VPos.TOP);
		gc.fillText("HP : ", 10, App.SCREEN_HEIGHT - 32);
		gc.setFill(Color.GRAY);
		gc.fillRect(40, App.SCREEN_HEIGHT - 35, 200, 20);
		gc.setFill(Color.CORNFLOWERBLUE);
		gc.fillRect(40, App.SCREEN_HEIGHT - 35, reader.readHp() * 200 / reader.readMaxHp(), 20);
		gc.setFill(Color.WHITE);
		gc.fillText(reader.readHp() + "/" + reader.readMaxHp(), 100, App.SCREEN_HEIGHT - 30);

		gc.setFill(Color.WHITE);
		gc.fillText("MP : ", 260, App.SCREEN_HEIGHT - 32);
		gc.setFill(Color.GRAY);
		gc.fillRect(290, App.SCREEN_HEIGHT - 35, 200, 20);
		gc.setFill(Color.LIMEGREEN);
		gc.fillRect(290, App.SCREEN_HEIGHT - 35, reader.readMp() * 200 / reader.readMaxMp(), 20);
		gc.setFill(Color.WHITE);
		gc.fillText(reader.readMp() + "/" + reader.readMaxMp(), 350, App.SCREEN_HEIGHT - 30);

		gc.setFill(Color.WHITE);
		gc.fillText(reader.getHeroMode(), App.SCREEN_WIDTH - 150, App.SCREEN_HEIGHT - 32);
	}

}
