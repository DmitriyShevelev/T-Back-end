package telran.numbers;

import java.util.Random;
import java.util.stream.Stream;

import telran.performance.PerformanceTest;

public class GroupSumPerformanceAppl {
	static private final int N_GROUPS = 100000;
	static private final int N_NUMBERS_GROUP = 50000;
	static private final int N_RUNS = 1;
	static private final int MIN_VALUE = 1;
	static private final int MAX_VALUE = Integer.MAX_VALUE;

	public static void main(String[] args) {
		int [][] numbers = getRandomNumbers(N_GROUPS,
				N_NUMBERS_GROUP, MIN_VALUE, MAX_VALUE);
		PerformanceTest testCycles = new
				GroupSumPerformanceTest(getTestName("cycles"),
						N_RUNS, new GroupSumLoop(numbers));
		PerformanceTest testStreams = new
				GroupSumPerformanceTest(getTestName("streams"),
						N_RUNS, new GroupSumStream(numbers));
		testCycles.run();
		testStreams.run();

	}

	private static int[][] getRandomNumbers(int nGroups,
			int nNumbersGroup, int minValue, int maxValue) {
		// toArray() with no parameters is applied only in the primitive Streams
		//toArray(<factory method functional interface for any array>),
		// for example toArray(Employee[]::new) <=> toArray(s->new Employee[s])
		//Stream.generate(...) - for generation and opening any stream
		//Supplier Example
		//Stream.generate(()->getRandomEmployee()).limit(100).toArray(Employee[]::new);
		//Employee getRandomEmployee() {return new Employee(randomId, randomSalary.....);}
		Random gen = new Random();
		return Stream.generate(()->gen.ints
				(nNumbersGroup, minValue, maxValue).toArray())
				.limit(nGroups).toArray(int[][]::new);
	}

	private static String getTestName(String test) {
		
		return String.format("test: %s, nGroups: %d, nNumbersInGroup: %d",
				test, N_GROUPS, N_NUMBERS_GROUP);
	}

}
