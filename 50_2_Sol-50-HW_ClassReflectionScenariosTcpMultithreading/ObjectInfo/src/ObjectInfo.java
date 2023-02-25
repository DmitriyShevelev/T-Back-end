import java.util.HashSet;
import java.lang.reflect.*;
public class ObjectInfo {
static public void printObjectInfo(Object obj) {
	HashSet<Object> processedObjects = new HashSet<>();
	System.out.println("    Class name: " + obj.getClass().getName());
	printObjectInfo(obj, processedObjects, 0);
}

private static void printObjectInfo(Object obj, HashSet<Object> processedObjects, int level) {
	if(obj != null) {
		if (processedObjects.contains(obj)) {
			printLevel(level + 1, "recursion reference\n");
			return;
		}
		processedObjects.add(obj);
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field field: fields) {
			Class<?> type = field.getType();
			printNameType(level, field, type);
			boolean flPrimitiveString = isJavaStandard(type);
			if (field.trySetAccessible()) {
				printFieldValue(obj, level, field, flPrimitiveString, processedObjects);
			} else {
				System.out.println("-Not accessible value");
				
			}
			
		} 
	}else {
		printLevel(level + 1, "null\n");
	}
	
	
}



private static void printFieldValue(Object obj, int level, Field field,
		boolean flPrimitiveString, HashSet<Object> processedObjects) {
	try {
		Object value = field.get(obj);
		if (flPrimitiveString) {
			System.out.printf("-%s\n", value);
		} else {
			System.out.println();
			printObjectInfo(value,processedObjects, level + 1);
			
		}
	} catch (Exception e) {
		throw new IllegalStateException("No exception is expected");
	}
	
}
private static void printLevel(int level, String str) {
	System.out.printf("%s%s", " ".repeat(level), str);
}

private static void printNameType(int level, Field field, Class<?> type) {
	String str = String.format("%s-%s", field.getName(),type.getSimpleName() );
	printLevel(level, str);
	
}

private static boolean isJavaStandard(Class<?> type) {
	
	return type.isPrimitive() || type.getName().startsWith("java.");
}
}
