import java.io.*;
public class LineOutputStreamsAppl {

	private static final int N_LINES = Integer.MAX_VALUE / 10;

	public static void main(String[] args) throws FileNotFoundException {
		try(PrintStream printStream = new PrintStream("myFileStream.txt");
		PrintWriter printWriter = new PrintWriter("myFileWriter.txt");) {
			
		for (int i = 0; i < N_LINES; i++) {
			printStream.println("hello");
		}
		}

	}

}
