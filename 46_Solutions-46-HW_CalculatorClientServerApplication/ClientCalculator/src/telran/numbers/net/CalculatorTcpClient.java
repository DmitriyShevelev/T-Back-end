package telran.numbers.net;

import java.io.*;
import java.net.*;

import telran.view.EndOfInputException;
import static telran.numbers.net.ClientTcp.*;

public class CalculatorTcpClient implements ClientTcp, Closeable {
	Socket socket;
	PrintStream writer;
	BufferedReader reader;

	public CalculatorTcpClient(String host, int port) throws Exception {
		socket = new Socket(host, port);
		writer = new PrintStream(socket.getOutputStream());
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	private int processRequest(String expression) {
		try {
			writer.println(expression);
			String response = reader.readLine();
			if (response == null) {
				throw new EndOfInputException();
			}
			try {
				int result = Integer.parseInt(response);
				return result;
			} catch (NumberFormatException e) {
				throw new RuntimeException(response);
			}

		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public int add(int op1, int op2) {

		return processRequest(getExpression(op1, op2, op2 < 0 ? "-" : "+"));
	}

	

	@Override
	public int subtract(int op1, int op2) {
		return processRequest(getExpression(op1, op2, op2 < 0 ? "+" : "-"));
	}

	@Override
	public int divide(int op1, int op2) {
		int res = processRequest(getExpression(op1, op2, "/"));
		
		return op2 < 0 ? -res : res;
	}

	@Override
	public int multiply(int op1, int op2) {
		int res =  processRequest(getExpression(op1, op2, "*"));
		return op2 < 0? -res : res;
	}

	@Override
	public int compute(String expression) {

		return processRequest(expression);
	}

	@Override
	public void close() throws IOException {
		socket.close();

	}

}
