
public class PrinterController {

	private static final int N_THREADS = 7;	//10
	private static final int N_NUMBERS = 80;//100
	private static final int N_PARTITIONS = 10;
	
	

	public static void main(String[] args) {
		Printer.setPartitions(N_PARTITIONS);
		Printer.setOverall(N_NUMBERS);
		Printer[] printers = new Printer[N_THREADS];
		creatingPrinters(printers);
		printers[0].interrupt();
		
	}

	

	private static void creatingPrinters(Printer[] printers) {
		printers[0]=new Printer(1);
		for(int i=1; i<printers.length; i++){
			printers[i]=new Printer(i+1);
			printers[i-1].setNext(printers[i]);
			printers[i-1].start();
		}
		printers[printers.length-1].setNext(printers[0]);
		printers[printers.length-1].start();
	}

}
