
public class Printer extends Thread {
String symbols;
int length;

public Printer(String symbols) {
	this.symbols = symbols;
	length = symbols.length();
	setDaemon(true);
}
@Override
public void run() {
	int i = 0;
	while (true) {
		System.out.println(symbols.charAt(i));
		try {
			sleep(2000);
		} catch (InterruptedException e) {
			i++;
			if (i == length) {
				i = 0;
			}
			
		}
	}
}
}
