import java.net.*;
import java.io.*;
public class ClientEchoReverseAppl {

	private static final String HOST = "localhost";
	private static final int PORT =2000;

	public static void main(String[] args) throws Exception{
		Socket socket = new Socket(HOST, PORT);
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
		try(PrintStream writer = new PrintStream(socket.getOutputStream());
				BufferedReader reader =
						new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			while(true) {
				System.out.println("Enter any string or exit");
				String line = consoleReader.readLine();
				if (line == null || line.equals("exit")) {
					socket.close();//server will get null on server's readLine
					break;
				}
				writer.println(line);
				line = reader.readLine();
				if(line == null) {
					break;
				}
				System.out.println(line);
			}
		}

	}

}
