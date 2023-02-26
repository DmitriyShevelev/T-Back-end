package telran.performance;

import java.lang.reflect.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import telran.performance.annotations.Test;

public class PerformanceFrameworkAppl {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("<usage> - one argument with full class name");
			return;
		}
		try {
			Class<?> clazz = Class.forName(args[0]);
			Object obj = clazz.getConstructor().newInstance();
			 List<Method> testMethods = Arrays.stream(clazz.getDeclaredMethods())
					.filter(m -> m.isAnnotationPresent(Test.class)).toList();
			 runTests(obj, testMethods);
		}catch (Exception e) {
			System.out.println(e.toString());
			
		}

	}

	private static void runTests(Object obj, List<Method> testMethods) {
		testMethods.forEach(m -> run(obj, m));
		
	}
	private static void run(Object obj, Method method) {
		Test anTest = method.getAnnotation(Test.class);
		int nRuns = anTest.nRuns();
		Instant start = Instant.now();
		method.setAccessible(true);
		IntStream.range(0, nRuns).forEach(_i -> {
			try {
				method.invoke(obj);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		System.out.printf("method %s, nRuns %d, running time %d\n",method.getName(), nRuns,
				ChronoUnit.MILLIS.between(start, Instant.now()));
	}

}
