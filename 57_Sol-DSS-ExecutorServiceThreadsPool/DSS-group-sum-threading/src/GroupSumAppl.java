//VM arguments = "-Xmx5g" may give on the particular PC at ~3 sec longer, than in case of empty 
// 	parameter for the N_THREADS example: {1, 2, 4, 10, 40, 100, 200, 1000, 10000, 50000}.

import java.util.concurrent.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class GroupSumAppl {

	private static final int N_GROUPS = 100_000; //8_000;
	private static final int N_NUMBERS_GROUP = 10_000;
	private static int[] N_THREADS = new int[] {1, 2, 4, 10, 40, 100, 200, 1000, 10000, 50000};
	//private static int[] N_THREADS = new int[] {1, 2, 4, 10, 40, 100, 200, 1000, 10000, 50000, 10, 20, 30, 50, 60, 70, 80, 90, 120, 150};
	private static int[][] groups;
			
	public static void main(String[] args) throws InterruptedException {
		groups = getGroups();
		for (int N_THREADS_INDEX = 0; N_THREADS_INDEX < N_THREADS.length; N_THREADS_INDEX++) {
			ThreadsPoolRun(N_THREADS[N_THREADS_INDEX]);
		}
	}

	private static void ThreadsPoolRun (int N_THREADS_CURRENT) throws InterruptedException {
		Instant start = Instant.now();
		System.out.println("Threads pool size: " + N_THREADS_CURRENT);
		ExecutorService executor = Executors.newFixedThreadPool(N_THREADS_CURRENT);
		List<OneGroupSum> tasks = startGroups(executor, groups);
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.HOURS);
		System.out.println("Sum with executor:    " + tasks.stream().mapToLong(t -> t.res).sum());
		System.out.println("Time with executor, ms:       " + ChronoUnit.MILLIS.between(start, Instant.now())+"\n");
	}
	
	private static List<OneGroupSum> startGroups(ExecutorService executor, int[][] groups) {
		List<OneGroupSum> res = new ArrayList<>();
		for (int i = 0; i < N_GROUPS; i++) {
			OneGroupSum groupSum = new OneGroupSum(groups[i]);
			res.add(groupSum);
			executor.execute(groupSum);
		}
		return res;
	}

	private static int[][] getGroups() {
		Instant start = Instant.now();
		ThreadLocalRandom tlr = ThreadLocalRandom.current();
		int res[][] = new int[N_GROUPS][N_NUMBERS_GROUP];
		long sum = 0;
		for(int i = 0; i < N_GROUPS; i++) {
			for (int j = 0; j < N_NUMBERS_GROUP; j++) {
				res[i][j] = tlr.nextInt();
				sum += res[i][j];
			}
		}
		System.out.println("Sum without executor (includes array initialization): " + sum);
		System.out.println("Time without executor (includes array initialization), ms:    " + ChronoUnit.MILLIS.between(start, Instant.now())+"\n");
		start = Instant.now();
		for(int i = 0; i < N_GROUPS; i++) {
			for (int j = 0; j < N_NUMBERS_GROUP; j++) {
				sum += res[i][j];
			}
		}
		System.out.println("Sum without executor (doesn't include array initialization): " + sum);
		System.out.println("Time without executor (doesn't include array initialization), ms:    " + ChronoUnit.MILLIS.between(start, Instant.now())+"\n");
		return res;
	}
}