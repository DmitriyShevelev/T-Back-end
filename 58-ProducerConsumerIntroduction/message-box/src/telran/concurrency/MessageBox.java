package telran.concurrency;

public class MessageBox {
private String message;
public  synchronized void sendMessage(String message) {
	while(this.message != null) {
		try {
			wait();
		} catch (InterruptedException e) {
			
		}
	}
	this.message = message;
	this.notify();
	
}
public synchronized String takeMessage() throws InterruptedException {
	while(message == null) {
		this.wait();
	}
	String res = message;
	message = null;
	notify();
	return res;
}
}
