import telran.timing.Timer;

public class TimetTestAppl {

	public static void main(String[] args) throws InterruptedException {
		Timer timer = new Timer();
		
		timer.start();
		//imitation of Application working
		Thread.sleep(5000);
		timer.interrupt();
Thread.sleep(5000);
	}

}
