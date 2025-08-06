package object;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class KWICObjectLoader extends ClassLoader {

	public Object loadObject(String className) {
     try {
            ClassLoader loader = this.getClass().getClassLoader();
            Class<?> aClass = loader.loadClass(className);
            Constructor<?> constructor = aClass.getConstructor();
            return constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
        return null;
	}

}