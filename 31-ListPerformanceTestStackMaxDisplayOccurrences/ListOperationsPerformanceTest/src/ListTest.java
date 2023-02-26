import telran.performance.PerformanceTest;
import java.util.*;

public class ListTest extends PerformanceTest {
	List<Integer> list;
	int nElements;
	int getProb;

	public ListTest(String testName, int nRuns, int nElements, int getProb, List<Integer> list) {
		super(testName, nRuns);
		this.nElements = nElements;
		this.getProb = getProb;
		this.list = list;
	}

	@Override
	protected void runTest() {
		if (list.isEmpty()) {
			for (int i = 0; i < nElements; i++) {
				list.add(100);
			}
		}
		if (getRandomNumber(0, 100) < getProb) {
			list.get(getRandomNumber(0, nElements));
			
		} else {
			list.remove(0);
			list.add(0, 100);
		}

	}

	private static int getRandomNumber(int min, int max) {
		return (int) (min + Math.random() * (max - min));
	}

}
