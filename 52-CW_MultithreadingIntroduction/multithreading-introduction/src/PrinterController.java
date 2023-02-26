import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class PrinterController {

	public static void main(String[] args) throws InterruptedException {
		Instant start = Instant.now();
		Printer printer1 = new Printer('*', 100);
		Printer printer2 = new Printer('#', 100);
		printer1.start();
		printer2.start();
		printer1.join();
		printer2.join();
		System.out.printf("running time of two threads is %d\n",
				ChronoUnit.MILLIS.between(start, Instant.now()));

	}

}
