package telran.numbers;

public class GroupSumLoop extends GroupSum {

	public GroupSumLoop(int[][] numbers) {
		super(numbers);
		
	}

	@Override
	long getSum() {
		long sum = 0L;
		for(int[] arr: numbers) {
			for(int num: arr) {
				sum += num;
			}
		}
		return sum;
	}

}
