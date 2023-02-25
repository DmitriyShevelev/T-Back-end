package telran.numbers.net;

import java.io.Closeable;

import telran.numbers.CalculatorOperations;

public interface ClientTcp extends CalculatorOperations {
	static  String getExpression(int op1, int op2, String operator) {

		return firstNumberToString(op1) + operator + Math.abs(op2);
	}

	static  String firstNumberToString(int op) {
		String base = op < 0 ? "0" : "";
		return base + op;
	}
}
