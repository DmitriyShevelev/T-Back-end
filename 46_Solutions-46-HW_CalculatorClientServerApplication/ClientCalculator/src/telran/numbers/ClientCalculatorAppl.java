package telran.numbers;

import telran.numbers.net.CalculatorOperationsTcpJava;
import telran.numbers.net.CalculatorTcpClient;
import telran.view.*;

public class ClientCalculatorAppl {

	public static void main(String[] args) {
		
		InputOutput io = new ConsoleInputOutput();
		try {
			CalculatorOperations calculatorOperations = new CalculatorOperationsTcpJava("localhost", 5000);
			//CalculatorOperations calculatorOperations = new CalculatorTcpClient("localhost", 2000);
			//CalculatorOperations calculatorOperations = new CalculatorOperationsImpl();
			Menu menu = new Menu("Calculator", CalculatorActions.getCalculatorItems(calculatorOperations));
			menu.perform(io);
		} catch (Exception e) {
			io.writeObjectLine(e.getMessage());
		}

	}

}
