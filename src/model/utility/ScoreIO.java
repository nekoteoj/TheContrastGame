package model.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.PriorityQueue;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import view.GamePane;
import view.ViewManager;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

public class ScoreIO {

	private static final ScoreIO instance = new ScoreIO();

	private String playerName;

	protected ScoreIO() {

	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public void askPlayerName() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Input your name");
		dialog.setHeaderText("Could we ask your name?");
		dialog.setContentText("your name : ");
		Optional<String> result = dialog.showAndWait();	
		String name = result.orElse("").trim();
		name.replaceAll("\\s+", "_");
		if (name == null || name.length() <= 0) {
			name = "CP_ENG";
			Alert alert = new Alert(AlertType.INFORMATION, "Name cannot be empty, your name is CP_ENG", ButtonType.OK);
			alert.setTitle("Empty name input");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
		playerName = name;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void addNewScore(long endTime) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("score.dat", true))) {
			long clearTime = endTime - ((GamePane) ViewManager.getInstance().getPane("game")).getStartTime();
			long second = (long) (clearTime / 1000000000.0);
			long minute = second / 60;
			second = second % 60;
			askPlayerName();
			String name = getPlayerName();
			bw.write(name + " " + minute + " " + second + "\n");
			bw.flush();
		} catch (IOException e) {
			Platform.runLater(() -> {
				Alert alert = new Alert(AlertType.ERROR, "Cannot write score to a file.", ButtonType.OK);
				alert.setHeaderText(null);
				alert.setTitle("Error Occured!#@");
			});
		}
	}

	public void showScoreBoard() {
		PriorityQueue<ScoreNode> pq = new PriorityQueue<>();
		try (BufferedReader br = new BufferedReader(new FileReader("score.dat"))) {
			for (String line : (Iterable<String>) br.lines()::iterator) {
				String[] in = line.trim().split("\\s+");
				try {
					int min = Integer.parseInt(in[1]);
					int sec = Integer.parseInt(in[2]);
					pq.add(new ScoreNode(in[0], min, sec));
				} catch (Exception e) {
					
				}
			}
			String content = "";
			for (int i = 0; i < 5 && !pq.isEmpty(); i++) {
				ScoreNode sn = pq.poll();
				content += (i + 1) + ".      Name:    " + sn.getName() + "     Time:    " + sn.getMin() + "." + sn.getSec() + "\n";
			}
			Alert alert = new Alert(AlertType.INFORMATION, content, ButtonType.OK);
			alert.setHeaderText("Scoreboard");
			alert.setTitle("Scoreboard");
			alert.showAndWait();
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.INFORMATION, "No score record file found.", ButtonType.OK);
			alert.setHeaderText("Scoreboard");
			alert.setTitle("Scoreboard");
			alert.showAndWait();
		}
	}

	public static ScoreIO getInstance() {
		return instance;
	}

}
