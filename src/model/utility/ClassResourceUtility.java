package model.utility;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ClassResourceUtility {

	private static ClassResourceUtility instance = new ClassResourceUtility();

	public static String getResourcePath(String rawPath) {
		return getResource(rawPath).toString();
	}

	public static URI getResourceURI(String rawPath) {
		try {
			return getResource(rawPath).toURI();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static URL getResource(String rawPath) {
		return instance.getClass().getClassLoader().getResource(rawPath);
	}

}
