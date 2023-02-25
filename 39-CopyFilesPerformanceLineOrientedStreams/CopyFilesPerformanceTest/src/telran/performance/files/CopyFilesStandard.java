package telran.performance.files;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CopyFilesStandard implements CopyFiles {

	@Override
	public void copy(String sourcePath, String destPath, int bufferSize) {
		try {
			Path source = Paths.get(sourcePath);
			Path dest = Paths.get(destPath);
			Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			System.out.println(copyError(e, sourcePath, destPath));
		} 

	}
}
