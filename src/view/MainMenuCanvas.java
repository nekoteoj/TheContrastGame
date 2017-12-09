package view;

import java.util.List;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import main.App;
import model.mainmenu.Menu;
import model.mainmenu.Rain;
import model.utility.RainMaker;

public class MainMenuCanvas extends Canvas {

	GraphicsContext gc = getGraphicsContext2D();
	private int logoTick = 0;
	private int logoSize = 10;
	private int maxLogoTick = 0;
	private List<Rain> rains;
	private Menu menu;

	public MainMenuCanvas(Menu menu) {
		super();
		this.setWidth(App.SCREEN_WIDTH);
		this.setHeight(App.SCREEN_HEIGHT);
		this.rains = RainMaker.getRainList();
		this.menu = menu;
	}

	public void draw() {
		drawBackground();
		drawRain();
		drawLogo();
		drawForeground();
		drawMenu();
	}

	public void drawBackground() {
		gc.setFill(Color.rgb(178, 255, 89));
		gc.fillRect(0, 0, App.SCREEN_WIDTH, App.SCREEN_HEIGHT);
	}

	public void drawForeground() {
		gc.setFill(Color.rgb(50, 50, 50));
		gc.fillRect(0, 400, 800, 200);
	}

	public void drawRain() {
		rains.forEach(rain -> rain.draw(getGraphicsContext2D()));
	}

	public void drawMenu() {
		gc.setTextBaseline(VPos.CENTER);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFont(new Font("Press Start 2P", 30));
		List<String> list = menu.getItemList();
		for (int i = 0; i < list.size(); i++) {
			gc.setFill(Color.WHITE);
			gc.fillText(list.get(i), App.SCREEN_WIDTH / 2, 450 + i * 50);
			if (menu.getSelectedIndex() == i) {
				gc.setFill(Color.RED);
				gc.fillText(list.get(i), App.SCREEN_WIDTH / 2, 450 + i * 50);
			}
		}
	}

	public void drawLogo() {
		gc.setFill(Color.rgb(245, 124, 0));
		gc.setStroke(Color.rgb(104, 54, 30));
		gc.setLineWidth(5);
		gc.setTextBaseline(VPos.CENTER);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFont(new Font("Press Start 2P", logoSize));
		gc.fillText("The Contrast", App.SCREEN_WIDTH / 2, 200);
		gc.strokeText("The Contrast", App.SCREEN_WIDTH / 2, 200);
		logoTick++;
		if (logoTick > 5 && maxLogoTick == 0) {
			logoSize += 10;
			logoTick = 0;
		}
		if (logoSize >= 60) {
			if (maxLogoTick < 40) {
				maxLogoTick++;
			} else {
				logoSize = 10;
				maxLogoTick = 0;
			}
		}
	}

}
