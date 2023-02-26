package telran.performance;

public abstract class PerformanceTest {
	private String testName;
	private int nRuns;
	
public PerformanceTest(String testName, int nRuns) {
	
		this.testName = testName;
		this.nRuns = nRuns;
	}
public void run() {
	long start = System.currentTimeMillis();
	for (int i = 0; i < nRuns; i++) {
		runTest();
	}
	printResult(System.currentTimeMillis() - start);
}
private void printResult(long diffTime) {
	System.out.printf("Test: %s; Amount of runs: %d; Running Time: %d\n",
			testName, nRuns, diffTime);
	
}
protected abstract void runTest();
	

}
