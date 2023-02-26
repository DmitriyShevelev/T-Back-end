import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
public class InputStreamAppl {
private static final int N_BYTES = 1;
	public static void main(String[] args) throws IOException {
		

			
			FileInputStream inputStream = new FileInputStream("MyFile.txt");
			byte[] buffer = new byte[N_BYTES];
			long counter = 0;
			int readBytes = 0;
			while((readBytes = inputStream.read(buffer)) > 0) {
				counter += readBytes;
				
			}
		System.out.println(counter);	
		System.out.println(Files.getAttribute(Paths.get("MyFile.txt"), "size"));

	}

}
