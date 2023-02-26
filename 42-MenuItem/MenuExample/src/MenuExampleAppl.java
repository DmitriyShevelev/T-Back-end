import telran.view.*;

public class MenuExampleAppl {
static InputOutput io = new ConsoleInputOutput();
	public static void main(String[] args) {
		Item [] items = {
			Item.of("add numbers", iop->{
				int[] numbers = enterTwoNumbers();
				iop.writeObjectLine(numbers[0] + numbers[1]);
			}),	
			Item.of("subtract numbers", iop->{
				int[] numbers = enterTwoNumbers();
				iop.writeObjectLine(numbers[0] - numbers[1]);
			}),	
			Item.of("divide numbers", iop->{
				int[] numbers = enterTwoNumbers();
				iop.writeObjectLine(numbers[0] / numbers[1]);
			}),	
			Item.of("multiply numbers", iop->{
				int[] numbers = enterTwoNumbers();
				iop.writeObjectLine(numbers[0] * numbers[1]);
			}),
			Item.exit()
		};
		Menu menu = new Menu("Calculator!!!", items);
		menu.perform(io);

	}
	static private int [] enterTwoNumbers() {
		int[] res = {
				io.readInt("Enter first number"),
				io.readInt("Enter second number")
		};
		return res;
	}

}
