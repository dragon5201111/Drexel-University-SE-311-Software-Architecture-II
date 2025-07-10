package resources;

import java.util.*;
import java.util.stream.Collectors;

public class OptionReader {
	private static HashMap<String, String> userOptions = null;
	private static final KWICObjectLoader kwicObjLoader = new KWICObjectLoader();
	
	private OptionReader() {
	}
	
	public static void readOptions() {
		ResourceBundle rb = ResourceBundle.getBundle("resources.config");
		Enumeration<String> keys = rb.getKeys();
		userOptions = new HashMap<>();

		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = rb.getString(key);
			userOptions.put(key, value);
		}
	}
	
	public static Object getObjectFromKey(String keyStr) { 
		Object kwicObj = null;
		if (userOptions.containsKey(keyStr)) {
			String objName;
			objName = userOptions.get(keyStr);
			kwicObj = kwicObjLoader.loadObject(objName);
		}
		return kwicObj;
	}
	
	public static Object getObjectFromStr(String objStr) {
		return kwicObjLoader.loadObject(objStr);
	}

	public static String getString(String keyStr) {
		if (!OptionReader.userOptions.containsKey(keyStr)) {
			throw new NoSuchElementException(keyStr);
		}

		String value = OptionReader.userOptions.get(keyStr);

		// If the value is itself a key, resolve it recursively
		if (userOptions.containsKey(value)) {
			return OptionReader.getString(value);
		}

		return value;
	}

	public static Set<String> getStringSet(String keyStr) {
		// Assumes value for key is in format of: "a,b,c,..."
		return Arrays.stream(OptionReader.getString(keyStr)
						.split(","))
				.map(String::trim)
				.map(s -> s.replaceAll("^\"|\"$", ""))  // remove leading/trailing quotes
				.filter(s -> !s.isEmpty())
				.collect(Collectors.toSet());
	}

}
