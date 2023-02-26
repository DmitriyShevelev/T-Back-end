import java.lang.reflect.*;
import java.util.Arrays;
public class TestAppl {

	public static void main(String[] args) {
		if (args.length == 0) {
			printAllTestMethods(20);
			
			
		} else {
			printTestMethod(args, 10);
		}
		printStaticMethod("anyMethod");
		

	}

	private static void printStaticMethod(String name) {
		try {
			Method method = TestMethods.class.getMethod(name);
			method.invoke(TestMethods.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void printTestMethod(String[] args, int nRunsDef) {
		int nRuns = args.length > 1 ? Integer.parseInt(args[1]) : nRunsDef;
		try {
			Method method = TestMethods.class.getDeclaredMethod(args[0], int.class);
			method.setAccessible(true);
			method.invoke(new TestMethods(), nRuns);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void printAllTestMethods(int nRuns)  {
		TestMethods obj = new TestMethods();
		
		Class<TestMethods> clazz = TestMethods.class;
		Method[] methods = clazz.getDeclaredMethods();
		Arrays.stream(methods).filter(m -> m.getName().toLowerCase().contains("test"))
		.forEach(m -> {
			m.setAccessible(true);
			try {
				m.invoke(obj, nRuns);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}

}
