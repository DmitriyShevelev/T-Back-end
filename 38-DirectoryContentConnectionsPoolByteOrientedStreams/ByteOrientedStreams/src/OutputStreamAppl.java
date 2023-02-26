import java.io.*;
public class OutputStreamAppl {
	private static final int N_STRINGS = Integer.MAX_VALUE / 2;
	private static final int N_PARTS = 8;
	

	public static void main(String[] args) throws IOException {
		FileOutputStream outputStream =
				new FileOutputStream("myFile.txt");
		String hello = "hello";
		int partOne = N_STRINGS / N_PARTS;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < partOne; i++) {
			sb.append(hello);
		}
		byte[] buffer = sb.toString().getBytes();
		for (int i = 0; i < N_PARTS; i++) {
			outputStream.write(buffer);
			
		}
		outputStream.close();

	}

}
