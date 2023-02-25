package telran.utils;

public class RangeAppl {

	public static void main(String[] args) {
		if (args.length < 2) {
			throw new  IllegalArgumentException("main should "
					+ "contain two integer numbers");
		}
		int min = Integer.parseInt(args[0]);
		int max = Integer.parseInt(args[1]);
		int number = 50;
		Range range = new Range(min, max);
		try {
			range.checkNumber(number);
			System.out.printf("number %d is in range [%d-%d]",
					number, min, max);
		} catch (GreaterMaxRangeException e) {
			System.out.println("handling exception for numbers greater maximal value");
		} catch (LessMinRangeException e) {
			System.out.println("handling exception for numbers less minimal value");
		}

	}

}
