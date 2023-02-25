package telran.concurrency;
import java.util.concurrent.locks.*;
public class MessageBox {
private String message;
private Lock monitor = new ReentrantLock();
private Condition producerWaiting = monitor.newCondition();
private Condition consumerWaiting = monitor.newCondition();
public   void sendMessage(String message) {
	monitor.lock();
	try {
		while (this.message != null) {
			try {
				producerWaiting.await();
			} catch (InterruptedException e) {

			}
		}
		this.message = message;
		consumerWaiting.signal();
	} finally {
		monitor.unlock();
	}
	
}
public  String takeMessage() throws InterruptedException {
	monitor.lock();
	try {
		while (message == null) {
			consumerWaiting.await();
		}
		String res = message;
		message = null;
		producerWaiting.signal();
		return res;
	} finally {
		monitor.unlock();
	}
}
public String getMessage() {
	monitor.lock();
	try {
		String res = message;
		message = null;
		return res;
	} finally {
		monitor.unlock();
	}
}
}