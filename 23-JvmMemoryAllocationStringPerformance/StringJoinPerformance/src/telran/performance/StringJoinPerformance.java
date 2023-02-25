package telran.performance;

public class StringJoinPerformance extends PerformanceTest {
	String[] array;
	StringsJoin join;
	public StringJoinPerformance(int nStrings, StringsJoin joinImpl,
			String testName, int nRuns) {
		super(testName, nRuns);
		array = new String[nStrings];
		for (int i = 0; i < nStrings; i++) {
			array[i] = "string";
		}
		this.join = joinImpl;
		
	}
	@Override
	protected void runTest() {
		
		join.join(";", array);
	}

}
