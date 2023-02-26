import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
public class CopyAppl {
private static final int N_BYTES = 1_000_000;

public static void main(String[] args) throws Exception{
	//args[0] = path to source file
	//args[1] = path to destination file
	if (args.length < 2) {
		System.out.println("Too few arguments");
		return;
	}
	copy(args[0], args[1]);
}

public static void copy(String pathSrc, String pathDest) throws Exception {
	 FileInputStream inputStream = new FileInputStream(pathSrc);
	 OutputStream outputStream = new FileOutputStream(pathDest);
	 byte buffer[] = new byte[N_BYTES];
		int readBytes = 0;
		while ((readBytes = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, readBytes);
		}
     outputStream.close();
     inputStream.close();
 }
	
}
