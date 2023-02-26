import java.io.*;
public class LineInputStreamAppl {

	public static void main(String[] args) throws IOException {
	try(BufferedReader reader = new BufferedReader(new FileReader("myFile.txt"));){
		reader.lines().forEach(System.out::println);
	}
	
	

	}

}
