import java.util.Arrays;

public class OneGroupSum implements Runnable {
int group[];
long res;
	public OneGroupSum(int[] group) {
	this.group = group;
}
	@Override
	public void run() {
		res = Arrays.stream(group).asLongStream().sum();

	}
	
}