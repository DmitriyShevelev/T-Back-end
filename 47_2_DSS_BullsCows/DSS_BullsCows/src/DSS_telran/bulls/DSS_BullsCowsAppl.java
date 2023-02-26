// These desktop application is a game "Bulls and Cows". 

package DSS_telran.bulls;

import java.util.Random;
import java.util.Scanner;

public class DSS_BullsCowsAppl {

//	private static final int BASE = 10; // Usually we play in the field of BASE = 10 (decimal representation of a
										// number).
	private static final int DIGITS_AMOUNT = 4; // Number of figures in the target (secret) number.
	private static final int TARGET_DIGIT_START = 1;
	private static final int TARGET_DIGIT_FINISH = 9;

	public static void main(String[] args) {
		StringBuilder str = new StringBuilder();
		new Random().ints(TARGET_DIGIT_START, TARGET_DIGIT_FINISH + 1).distinct().limit(DIGITS_AMOUNT)
				.forEach(i -> str.append(i));
		String targetStr = str.toString();
		boolean guessed = false;
		int guesses = 0;
		do {
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			int bulls = 0;
			int cows = 0;
			System.out.print("Target = " + targetStr + " (cheating!!!). "); // Cheating for testing purposes.
			System.out.print("Guess a " + DIGITS_AMOUNT
					+ "-digit number, containing figures from 1 to 9, with no duplicate digits: ");
			String guessStr = input.nextLine();

			if (guessStr.length() < DIGITS_AMOUNT)
				continue;
			
			guesses++;
			for (int i = 0; i < DIGITS_AMOUNT; i++) {
				if (guessStr.charAt(i) == targetStr.charAt(i)) {
					bulls++;
				} else if (targetStr.contains(guessStr.charAt(i) + "")) { // Change char to CharSequence.
					cows++;
				}
			}
			if (bulls >= DIGITS_AMOUNT) {
				guessed = true;
			} else {
				System.out.println(cows + " Cows and " + bulls + " Bulls.");
			}
		} while (!guessed);
		System.out.println("You won after " + guesses + " guesses!");
	}

//	public static boolean hasDuplicates(int num) {
//		boolean[] digs = new boolean[BASE];
//		while (num > 0) {
//			if (digs[num % BASE])
//				return true;
//			digs[num % BASE] = true;
//			num /= BASE;
//		}
//		return false;
//	}
}
