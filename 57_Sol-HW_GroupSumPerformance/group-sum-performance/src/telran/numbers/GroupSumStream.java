package telran.numbers;

import java.util.Arrays;

import telran.performance.GroupSum;

public class GroupSumStream extends GroupSum {

	public GroupSumStream(int[][] groups) {
		super(groups);
		
	}

	@Override
	public boolean useExecutor() {
		
		return false;
	}

	@Override
	public long computeSum() {
		
		return Arrays.stream(groups).flatMapToInt(Arrays::stream)
				.asLongStream().sum();
	}

}
