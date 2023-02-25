package telran.performance.files;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFilesReadNBytes implements CopyFiles {

	@Override
	public void copy(String sourcePath, String destPath, int bufferSize) {
		try(InputStream inputStream = new FileInputStream(sourcePath);
				OutputStream outputStream = new FileOutputStream(destPath);) {
			
			
			byte[] buffer;
			while((buffer = inputStream.readNBytes(bufferSize)).length > 0) {			
				outputStream.write(buffer);			
			}
			
		} catch (Exception e) {
			System.out.println(copyError(e, sourcePath, destPath));
		} 

	}
}
