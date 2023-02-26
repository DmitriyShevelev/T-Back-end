package telran.performance.files;

import telran.performance.PerformanceTest;

public class CopyPerfomanceTest extends PerformanceTest{
	private String sourcePath;
	private String destPath;
	private int bufferSize;
	private CopyFiles copyFiles;
	
	public CopyPerfomanceTest(String testName, int nRuns, String sourcePath, String destPath, int bufferSize,
			CopyFiles copyFiles) {
		super(testName, nRuns);
		this.sourcePath = sourcePath;
		this.destPath = destPath;
		this.bufferSize = bufferSize;
		this.copyFiles = copyFiles;
	}
	
	@Override
	protected void runTest() {
		copyFiles.copy(sourcePath, destPath, bufferSize);
	}

}
