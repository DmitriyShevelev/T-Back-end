import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
public class ListProcessingAppl {
private static final int N_NUMBERS = 1000;
private static final int N_THREADS = 100;
private static final int PROB_UPDATE = 0;
private static final int N_RUNS = 1000;

public static void main(String[] args) {
	
	ListProcessing[] threads = startThreads();
	waitThreads(threads);
	System.out.println("counter: "+ ListProcessing.counter);
}

private static void waitThreads(ListProcessing[] threads) {
	Arrays.stream(threads).forEach(t -> {
		try {
			t.join();
		} catch (InterruptedException e) {
			
		}
	});
	
}

private static ListProcessing[] startThreads() {
	List<Integer> list = new CopyOnWriteArrayList<>();
	fillList(list);
	ListProcessing[] res = new ListProcessing[N_THREADS];
	for (int i = 0; i < N_THREADS; i++) {
		res[i] = new ListProcessing(list, PROB_UPDATE, N_RUNS);
		res[i].start();
	}
	return res;
}

private static void fillList(List<Integer> list) {
	for (int i = 0; i < N_NUMBERS; i++) {
		list.add(100);
	}
	
}
}
