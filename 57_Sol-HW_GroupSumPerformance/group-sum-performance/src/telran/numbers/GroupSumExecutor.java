package telran.numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import telran.performance.GroupSum;
/*pv1*/
public class GroupSumExecutor extends GroupSum {

	

	public GroupSumExecutor(int[][] groups) {
		super(groups);
	}

	@Override
	public boolean useExecutor() {
		return true;
	}

	@Override
	public long computeSum() {
		ExecutorService executor = Executors.newFixedThreadPool(nThreads);
		List<OneGroupSum> tasks =  startGroups(executor, groups);
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			throw new IllegalStateException("No interrupt should be performed");
		}
		return tasks.stream().mapToLong(OneGroupSum::getRes).sum();
	}

	private List<OneGroupSum> startGroups(ExecutorService executor, int[][] groups) {
			List<OneGroupSum> res = new ArrayList<>();
			for (int i = 0; i < groups.length; i++) {
				OneGroupSum groupSum = new OneGroupSum(groups[i]);
				res.add(groupSum);
				executor.execute(groupSum);
			}
			return res;
		}
	}

