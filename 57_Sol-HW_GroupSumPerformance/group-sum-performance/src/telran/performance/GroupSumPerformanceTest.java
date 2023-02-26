package telran.performance;

public class GroupSumPerformanceTest extends PerformanceTest {
private GroupSum groupSum;
	public GroupSumPerformanceTest(String testName, int nRuns, GroupSum groupSum) {
	super(testName, nRuns);
	this.groupSum = groupSum;
}
	@Override
	protected void runTest() {
		groupSum.computeSum();

	}

}
