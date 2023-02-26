package telran.performance.files;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import telran.performance.PerformanceTest;



public class CopyPerfomanceTestAppl {

	private static final int N_RUNS = 1;
	private static final int BUFFER_SIZE = 1024 * 1024;
	private static final int BUFFER_SIZE_CREATE = 1024 * 1024;
	private static final long FILE_SIZE = 3L * 1024 * 1024 * 1024;
	private static final String sourceFile = "testSrc";
	private static final String destFile = "testDest";

	public static void main(String[] args) {
		setUp();
		PerformanceTest copyBuffer = new CopyPerfomanceTest("Buffer", N_RUNS, sourceFile, destFile, BUFFER_SIZE,
				new CopyFilesBuffer());
		PerformanceTest copyReadNBytes = new CopyPerfomanceTest("ReadNBytes", N_RUNS, sourceFile, destFile, BUFFER_SIZE,
				new CopyFilesReadNBytes());
		PerformanceTest copyStandard = new CopyPerfomanceTest("Standard", N_RUNS, sourceFile, destFile, BUFFER_SIZE,
				new CopyFilesStandard());
		PerformanceTest copyTransfer = new CopyPerfomanceTest("Transfer", N_RUNS, sourceFile, destFile, BUFFER_SIZE,
				new CopyFilesTransfer());

		copyBuffer.run();
		copyReadNBytes.run();
		copyStandard.run();
		copyTransfer.run();
		tearDown();

	}

	private static void setUp() {
		try (FileOutputStream outputStream = new FileOutputStream(sourceFile);) { // true = append
			
			byte[] buffer = new byte[BUFFER_SIZE_CREATE];
			long parts = FILE_SIZE / BUFFER_SIZE_CREATE;
			
			for (long i = 0; i < parts; i++) {
				outputStream.write(buffer);
			}
			
		} catch (Exception e) {
			System.out.println(String.format("Error create file %s\n More: %s", sourceFile, e.getMessage()));
		}
	}

	private static void tearDown() {
		String fileName = null;
		try {
			 fileName = sourceFile;
			Files.delete(Paths.get(fileName));
			fileName = destFile;
			Files.delete(Paths.get(fileName));
		} catch (Exception e) {
			System.out.println(String.format("Error delete file %s\n More: %s", fileName, e.getMessage()));
		}
	}

}