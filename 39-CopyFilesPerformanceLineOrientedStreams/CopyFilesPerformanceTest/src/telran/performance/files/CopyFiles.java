package telran.performance.files;

public interface CopyFiles {
	default String copyError(Exception e, String source, String dest) {
		String res = String.format("An error occurred while copying from %s to %s. Error message: %s",
				source, dest,e.getMessage());
		return res;
	}
	void copy(String srcPath, String destPath, int bufSize);
}
