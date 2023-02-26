import java.lang.reflect.*;
public class FieldAnalyzeAppl {
public static void main(String[] args) {
	printObjInfo(new FieldAnalyze("Hello", new TestMethods(), 10));
}

private static void printObjInfo(Object obj) {
	Field[] fields = obj.getClass().getDeclaredFields();
	for(Field f: fields) {
		try {
			System.out.println(f.get(obj));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
}
