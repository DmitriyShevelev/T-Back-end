package telran.concurrency;

public class MessageBox {
private String message;
public  synchronized void sendMessage(String message) {
	while(this.message != null) {
		try {
			this.wait();
		} catch (InterruptedException e) {
			
		}
	}
	this.message = message;
	this.notifyAll();
	
}
public synchronized String takeMessage() throws InterruptedException {
	while(message == null) {
		this.wait();
	}
	String res = message;
	message = null;
	notifyAll();
	return res;
}
public synchronized String getMessage() {
	String res = message;
	message = null;
	return res;
}
}
