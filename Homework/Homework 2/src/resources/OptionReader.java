package resources;

import java.util.*;

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
		String valueStr = "";
		if (userOptions.containsKey(keyStr)) {			
			valueStr = userOptions.get(keyStr);			
		}
		return valueStr;
	}

}
