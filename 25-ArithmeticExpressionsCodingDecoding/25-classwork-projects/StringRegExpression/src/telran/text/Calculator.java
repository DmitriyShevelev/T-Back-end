package telran.text;

public class Calculator {
public static int add(int op1, int op2) {
	
	return op1 + op2;
}
public static int subtract(int op1, int op2) {
	
	return op1 - op2;
}
public static int multiply(int op1, int op2) {
	
	return op1 * op2;
}
public static int divide(int op1, int op2) {
	
	return op1 / op2;
}
public static int calculate(String expr) {
	int res = 0;
	if (expr.matches(StringRegExpression.arithmeticExpression())) {
		expr = expr.trim();
		String arNumbers[] = expr.split("\\D+");
		String operators[] = expr.split("[\\d\\s]+");
		res = Integer.parseInt(arNumbers[0]);
		for (int i = 1; i < arNumbers.length; i++) {
			String operator = operators[i];
			int number = Integer.parseInt(arNumbers[i]);
			
			switch (operator) {
			case "*": res = multiply(res, number); break;
			case "-": res = subtract(res, number); break;
			case "/": res = divide(res, number);
			
				break;
			case "+": res = add(res, number); break;
			default: throw new RuntimeException("Wrong Implementation");
			}
		}
	} else {
		throw new IllegalArgumentException();
	}
	return res;		
}

}
