package model.utility;

/*
 * C++ like pair objects
 */
public class Pair<K, V> {
	public K first;
	public V second;

	public Pair() {
	}

	public Pair(K first, V second) {
		this.first = first;
		this.second = second;
	}

	public static <K, V> Pair<K, V> makePair(K first, V second) {
		return new Pair<>(first, second);
	}
}
