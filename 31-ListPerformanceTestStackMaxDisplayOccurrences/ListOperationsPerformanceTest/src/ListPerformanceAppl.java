import java.util.*;

import telran.performance.PerformanceTest;

public class ListPerformanceAppl {

	private static final int GET_PROB = 100;
	private static final int N_RUNS = 5000;
	private static final int N_ELEMENTS = 100000;

	public static void main(String[] args) {
		PerformanceTest test1 =
				new ListTest(String.format("Array List getProb=%d ", GET_PROB), 
						N_RUNS, N_ELEMENTS, GET_PROB,
						new ArrayList<Integer>(N_ELEMENTS));
		PerformanceTest test2 =
				new ListTest(String.format("Linked List getProb=%d ", GET_PROB), 
						N_RUNS, N_ELEMENTS, GET_PROB,
						new LinkedList<Integer>());
			test2.run();test1.run();
	

	}

}
