package telran.performance;

public abstract class GroupSum {
private static final int DEFAULT_NUMER_THREADS = 4;
protected int groups[][];
protected int nThreads = DEFAULT_NUMER_THREADS;

public GroupSum(int[][] groups) {
	super();
	this.groups = groups;
}
abstract public boolean useExecutor();
abstract public long computeSum();
public void setnThreads(int nThreads) {
	this.nThreads = nThreads;
}
}
