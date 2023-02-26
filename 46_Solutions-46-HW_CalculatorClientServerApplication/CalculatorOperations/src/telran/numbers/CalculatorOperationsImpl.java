package telran.numbers;

import telran.text.Calculator;

public class CalculatorOperationsImpl implements CalculatorOperations {

	@Override
	public int add(int op1, int op2) {
		
		return op1 + op2;
	}

	@Override
	public int subtract(int op1, int op2) {
		
		return op1 - op2;
	}

	@Override
	public int divide(int op1, int op2) {
		
		return op1 / op2;
	}

	@Override
	public int compute(String expression) {
		
		return Calculator.calculate(expression);
	}

	@Override
	public int multiply(int op1, int op2) {
		
		return op1 * op2;
	}

}
