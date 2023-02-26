package telran.performance;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.io.*;

public class GroupSumPerformanceAppl {

	private static final String DEFAULT_CONFIG_PATH = "application.properties";
	private static final String DEFAULT_BASE_PACKAGE = "telran.numbers.";
	private static final String BASE_PACKAGE_PROPERTY = "base-package";
	private static final String CLASS_NAMES_PROPERTY = "class-names";
	private static final int N_GROUPS = 100000;
	private static final int N_NUMBERS_GROUP = 10000;
	

	public static void main(String[] args) {
		String configPath = args.length > 0 ? args[0] : DEFAULT_CONFIG_PATH;
		Properties properties = new Properties();
		FileInputStream configFile = null;
		try {
			configFile = new FileInputStream(configPath);
			properties.load(configFile);
			String basePackage = properties.getProperty(BASE_PACKAGE_PROPERTY, DEFAULT_BASE_PACKAGE);
			String classesStr = properties.getProperty(CLASS_NAMES_PROPERTY);
			
			if (classesStr == null) {
				throw new RuntimeException(CLASS_NAMES_PROPERTY + " not found in the configuration file");
			}
			GroupSum[] testedInstances = getTestedInstances(basePackage, classesStr);
			runTests(testedInstances);
		} catch (FileNotFoundException e) {
			System.out.println(configPath + " Configuration file not found");
		} catch (IOException e) {
			System.out.println(configPath + " Corrupted");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static GroupSum[] getTestedInstances(String basePackage, String classesStr) throws Exception {
		String classNames[] = classesStr.split("[, ]+");
		int groups[][] = getGroups();
		return Arrays.stream(classNames).map(n -> {
			try {
				return Class.forName(basePackage  + n)
						.getConstructor(int[][].class).newInstance(groups);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		} )
				.toArray(GroupSum[]::new );
	}

		private static int[][] getGroups() {
			ThreadLocalRandom tlr = ThreadLocalRandom.current();
			int res[][] = new int[N_GROUPS][N_NUMBERS_GROUP];
			long sum = 0;
			for(int i = 0; i < N_GROUPS; i++) {
				for (int j = 0; j < N_NUMBERS_GROUP; j++) {
					res[i][j] = tlr.nextInt();
					sum += res[i][j];
				}
			}
			
			return res;
		}

	private static void runTests(GroupSum[] testedInstances) {
		Arrays.stream(testedInstances).forEach(GroupSumPerformanceAppl::runTest);
		
	}
	private static void runTest(GroupSum testedInstance) {
		if (testedInstance.useExecutor()) {
			runExecutorTests(testedInstance);
		} else {
			new GroupSumPerformanceTest(testedInstance.getClass().getSimpleName(), 1, testedInstance).run();
		}
	}

	private static void runExecutorTests(GroupSum testedInstance) {
		int nThreadValues[] = {1,2,4,10,40, 100, 200, 1000, 10000, 50000};
		for (int nThreads: nThreadValues) {
			testedInstance.setnThreads(nThreads);
			new GroupSumPerformanceTest(testedInstance.getClass().getSimpleName() + " nThreads=" + nThreads, 1, testedInstance).run();
		}
		
	}

}
