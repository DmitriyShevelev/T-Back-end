package telran.numbers;

import java.util.Arrays;

public class GroupSumStream extends GroupSum {

	public GroupSumStream(int[][] numbers) {
		super(numbers);
		
	}

	@Override
	long getSum() {
		return Arrays.stream(numbers)
				.flatMapToInt(Arrays::stream).asLongStream().parallel()
				.sum();

	}

}
