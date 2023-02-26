package telran.numbers.net;

import java.io.IOException;

import telran.numbers.CalculatorOperationsImpl;

public class ServerCalculatorAppl {

	public static void main(String[] args)  {
		try {
			CalculatorTcpServer server = new CalculatorTcpServer(2000, new CalculatorOperationsImpl());
			server.run();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
