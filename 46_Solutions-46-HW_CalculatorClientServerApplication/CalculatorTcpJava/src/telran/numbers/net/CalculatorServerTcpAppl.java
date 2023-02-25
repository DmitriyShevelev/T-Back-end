package telran.numbers.net;

import telran.net.TcpJavaServer;
import telran.numbers.*;

public class CalculatorServerTcpAppl {

	private static final int PORT = 5000;

	public static void main(String[] args) {
		CalculatorOperations calculatorOperations = new CalculatorOperationsImpl();
		TcpJavaServer server =
				new TcpJavaServer(PORT, new CalculatorProtocol(calculatorOperations));
		server.run();

	}

}
