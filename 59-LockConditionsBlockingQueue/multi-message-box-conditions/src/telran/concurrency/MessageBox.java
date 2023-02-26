package telran.concurrency;
import java.util.*;
import java.util.concurrent.locks.*;
public class MessageBox {
private static final int DEFAULT_LIMIT = 10;
private Queue<String> messages = new LinkedList<>();
private Lock monitor = new ReentrantLock();
public final int limit;

private Condition producerWaiting = monitor.newCondition();
private Condition consumerWaiting = monitor.newCondition();
public MessageBox() {
	limit = DEFAULT_LIMIT;
}
public MessageBox(int limit) {
	this.limit = limit;
}
public   void sendMessage(String message) {
	monitor.lock();
	try {
		while (messages.size() == limit) {
			try {
				producerWaiting.await();
			} catch (InterruptedException e) {

			}
		}
		messages.offer(message);
		consumerWaiting.signal();
	} finally {
		monitor.unlock();
	}
	
}
public  String takeMessage() throws InterruptedException {
	monitor.lock();
	try {
		while (messages.isEmpty()) {
			consumerWaiting.await();
		}
		String res = messages.remove();
		
		producerWaiting.signal();
		return res;
	} finally {
		monitor.unlock();
	}
}
public String getMessage() {
	monitor.lock();
	try {
		String res = messages.poll();
		
		return res;
	} finally {
		monitor.unlock();
	}
}
}
