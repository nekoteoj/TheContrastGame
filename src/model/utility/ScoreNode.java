package model.utility;

public class ScoreNode implements Comparable<ScoreNode> {
	
	private String name;
	private int min;
	private int sec;
	
	public ScoreNode(String name, int min, int sec) {
		this.name = name;
		this.min = min;
		this.sec = sec;
	}

	public String getName() {
		return name;
	}

	public int getMin() {
		return min;
	}

	public int getSec() {
		return sec;
	}

	@Override
	public int compareTo(ScoreNode o) {
		if (min != o.min) {
			return Integer.compare(min, o.min);
		}
		if (sec != o.sec) {
			return Integer.compare(sec, o.sec);
		}
		return name.compareTo(o.name);
	}

}
