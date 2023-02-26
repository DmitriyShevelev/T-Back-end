
public class Gruzovik extends Thread {
private long load;
private int nLoads;
private static final Object mutex = new Object();
private static long elevator1;
private static long elevator2;
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


static private synchronized void load2(long load) {
	elevator2 += load;
	
}


static private void load1(long load) {
	synchronized (mutex) {
		elevator1 += load;
	}
	
}


public static long getElevator1() {
	return elevator1;
}


public static long getElevator2() {
	return elevator2;
}

}
