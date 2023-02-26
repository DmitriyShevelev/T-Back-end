package telran.numbers.net;

import telran.numbers.*;
import java.net.*;
import java.io.*;
public class CalculatorTcpServer {
private int port;
private CalculatorOperations calculator;
private ServerSocket serverSocket;
public CalculatorTcpServer(int port, CalculatorOperations calculator) throws IOException {
	this.port = port;
	this.calculator = calculator;
	serverSocket = new ServerSocket(port);
}
public void run() {
	System.out.println("Server is listening on the port " + port);
	while (true) {
		try {
			Socket socket = serverSocket.accept();
			runClient(socket);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
private void runClient(Socket socket) {
	try (BufferedReader reader =
			new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream writer = new PrintStream(socket.getOutputStream())) {
		while(true) {
			String line = reader.readLine();
			if(line == null) {
				socket.close();
				break;
			}
			try {
				writer.println(calculator.compute(line));
			} catch (Exception e) {
				writer.println(e.getMessage());
			}
		}
		
	}catch (Exception e) {
		System.out.println(e.getMessage());
	}
	
}
}
