package exception;

public class MapObjectNotFoundException extends Exception {
	public MapObjectNotFoundException() {
		super();
	}
	
	@Override
	public void printStackTrace() {
		System.err.println("Invalid Map Object Creation");
		super.printStackTrace();
	}
}
