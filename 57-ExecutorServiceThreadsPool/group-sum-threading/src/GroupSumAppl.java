import java.util.concurrent.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
public class GroupSumAppl {

	private static final int N_THREADS = 4; //1
	private static final int N_GROUPS = 10_000; //100_000
	private static final int N_NUMBERS_GROUP = 9_000; //10_000



	public static void main(String[] args) throws InterruptedException {
		int groups[][] = getGroups();
		Instant start = Instant.now();
		ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);
		List<OneGroupSum> tasks =  startGroups(executor, groups);
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.HOURS);
		System.out.println(tasks.stream().mapToLong(t -> t.res).sum());
		System.out.println(ChronoUnit.MILLIS.between(start, Instant.now()));
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
		ThreadLocalRandom tlr = ThreadLocalRandom.current();
		int res[][] = new int[N_GROUPS][N_NUMBERS_GROUP];
		long sum = 0;
		for(int i = 0; i < N_GROUPS; i++) {
			for (int j = 0; j < N_NUMBERS_GROUP; j++) {
				res[i][j] = tlr.nextInt();
				sum += res[i][j];
			}
		}
		System.out.println(sum);
		return res;
	}

}
