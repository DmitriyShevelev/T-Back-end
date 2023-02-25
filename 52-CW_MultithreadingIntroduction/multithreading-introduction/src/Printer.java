
public class Printer extends Thread {
private char symbol;
private int nRuns;
public Printer(char symbol, int nRuns) {
	super();
	this.symbol = symbol;
	this.nRuns = nRuns;
}
@Override
public void run() {
	for (int i = 0; i < nRuns; i++) {
		System.out.println(symbol);
		try {
			sleep(1);
			//natural exit
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
		
	}
}
}
