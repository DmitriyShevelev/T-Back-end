import java.util.concurrent.atomic.AtomicLong;

public class Gruzovik extends Thread {
	private long load;
	private int nLoads;
	private static AtomicLong elevator1 = new AtomicLong(0);
	private static AtomicLong elevator2 = new AtomicLong(0);

	public Gruzovik(long load, int nLoads) {
		this.load = load;
		this.nLoads = nLoads;
	}

	@Override
	public void run() {
		for (int i = 0; i < nLoads; i++) {
			load1(load);
			load2(load);
		}
	}

	static private void load2(long load) {
		elevator2.addAndGet(load);

	}

	static private void load1(long load) {
		elevator1.addAndGet(load);

	}

	public static long getElevator1() {
		return elevator1.get();
	}

	public static long getElevator2() {
		return elevator2.get();
	}

}
