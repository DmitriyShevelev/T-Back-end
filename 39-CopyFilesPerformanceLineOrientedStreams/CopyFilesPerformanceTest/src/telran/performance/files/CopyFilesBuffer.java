package telran.performance.files;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class CopyFilesBuffer implements CopyFiles {

	@Override
	public void copy(String srcPath, String destPath, int bufSize) {
		try (FileInputStream inputStream = new FileInputStream(srcPath);
		 OutputStream outputStream = new FileOutputStream(destPath);){
			 byte buffer[] = new byte[bufSize];
				int readBytes = 0;
				while ((readBytes = inputStream.read(buffer)) > 0) {
					outputStream.write(buffer, 0, readBytes);
				}
		} catch(Exception e) {
			System.out.println(copyError(e, srcPath, destPath));
		}
		
	     
	 }
		
	}

