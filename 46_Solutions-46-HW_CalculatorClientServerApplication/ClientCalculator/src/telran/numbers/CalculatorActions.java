package telran.numbers;

import java.io.Closeable;
import java.io.IOException;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import telran.text.StringRegExpression;
import telran.view.InputOutput;
import telran.view.Item;

public class CalculatorActions {
private static CalculatorOperations calculatorOperations;
private static String expressionMessage = "Expression: <signless integer>"
		+ "<operator><signless integer>...";
private static Predicate<String> exprPredicate =
Pattern.compile(StringRegExpression.arithmeticExpression())
.asPredicate();
public static Item[] getCalculatorItems(CalculatorOperations calculatorOperations) {
	CalculatorActions.calculatorOperations = calculatorOperations;
	Item [] items = {
		Item.of("Add numbers", CalculatorActions::add)	,
		Item.of("Subtract numbers", CalculatorActions::subtract)	,
		Item.of("Multiply numbers", CalculatorActions::multiply)	,
		Item.of("Divide numbers", CalculatorActions::divide)	,
		Item.of("Compute expression", CalculatorActions::expression)	,
		Item.of("Exit", io -> {
			if (calculatorOperations instanceof Closeable ) {
				try {
					((Closeable)calculatorOperations).close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage());
				}
			}
		}, true)
	};
	return items;
}
private static void add(InputOutput io) {
	int numbers[] = getTwoNumbers(io);
	io.writeObjectLine(calculatorOperations.add(numbers[0], numbers[1]));
	
}
private static int[] getTwoNumbers(InputOutput io) {
	int[] res = {
		io.readInt("enter first number"),
		io.readInt("enter second number")
	};
	return res;
}
private static void subtract(InputOutput io) {
	int numbers[] = getTwoNumbers(io);
	io.writeObjectLine(calculatorOperations.subtract(numbers[0], numbers[1]));
	
}
private static void multiply(InputOutput io) {
	int numbers[] = getTwoNumbers(io);
	io.writeObjectLine(calculatorOperations.multiply(numbers[0], numbers[1]));
	
}
private static void divide(InputOutput io) {
	int numbers[] = getTwoNumbers(io);
	io.writeObjectLine(calculatorOperations.divide(numbers[0], numbers[1]));
}
private static void expression(InputOutput io) {
	String expressionStr =
			io.readStringPredicate(expressionMessage, "Wrong arithmetic expression",
					//str-> str.matches(StringRegExpression.arithmeticExpression()) );
	exprPredicate);
	io.writeObjectLine(calculatorOperations.compute(expressionStr));
}
}
