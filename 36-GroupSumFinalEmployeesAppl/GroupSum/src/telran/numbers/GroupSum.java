package telran.numbers;

public abstract class GroupSum {
	protected int[][] numbers;

	public GroupSum(int[][] numbers) {
		
		this.numbers = numbers;
	}

	abstract long getSum();

}
