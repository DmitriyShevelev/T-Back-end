import java.util.Scanner;

public class PrinterController {

	public static void main(String[] args) {
		Printer printer = new Printer("123456");
		printer.start();
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String line = scanner.nextLine();
			if (line.equals("q")) {
				break;
			}
			printer.interrupt();
			
		}

	}

}
