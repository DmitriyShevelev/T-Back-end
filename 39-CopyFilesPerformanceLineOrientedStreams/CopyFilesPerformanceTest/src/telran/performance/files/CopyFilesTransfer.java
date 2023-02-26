package telran.performance.files;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFilesTransfer implements CopyFiles {

	@Override
	public void copy(String sourcePath, String destPath, int bufferSize) {
		try(InputStream inputStream = new FileInputStream(sourcePath);
			OutputStream outputStream = new FileOutputStream(destPath);) {
			
			
			inputStream.transferTo(outputStream);
			
			
		} catch (Exception e) {
			System.out.println(copyError(e, sourcePath, destPath));
		} 

	}

}
