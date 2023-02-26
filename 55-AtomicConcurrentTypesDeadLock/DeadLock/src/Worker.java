
public class Worker extends Thread {
static final private Object m1 = new Object();
static final private Object m2 = new Object();
public void f1()  {
	synchronized (m1) {
		try {
			sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized(m2) {
			
		}
		
	}
}
public void f2() {
	synchronized (m1) {
		synchronized(m2) {
			
		}
		
	}
}
@Override
public void run() {
	f1();
	f2();
}
}
