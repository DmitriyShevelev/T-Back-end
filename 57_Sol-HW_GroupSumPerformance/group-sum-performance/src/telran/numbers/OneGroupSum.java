package telran.numbers;

import java.util.Arrays;

public class OneGroupSum implements Runnable {
	private int group[];
	private long res;

	

	public OneGroupSum(int[] group) {
		this.group = group;
	}

	@Override
	public void run() {
		res = Arrays.stream(group).asLongStream().sum();

	}
	
	public long getRes() {
		return res;
	}

}
