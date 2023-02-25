package telran.concurrency.consumer;

import java.util.concurrent.atomic.AtomicInteger;

import telran.concurrency.MessageBox;

public class Consumer extends Thread {
private  MessageBox messageBox;
public static AtomicInteger count = new AtomicInteger();
private volatile boolean running = true;
public void finish() {
	this.running = false;
}

public Consumer(MessageBox messageBox) {
	this.messageBox = messageBox;
	
}

public void setMessageBox(MessageBox messageBox) {
	this.messageBox = messageBox;
}  
@Override
public void run() {
	String message = null;
	while(running) {
		try {
			message = messageBox.takeMessage();
			printMessage(message);
		} catch (InterruptedException e) {

			}
		}
	while ((message = messageBox.getMessage()) != null) {
		printMessage(message);
	}
}

private void printMessage(String message) {
	count.incrementAndGet();
	System.out.printf("thread: %s, message: %s\n", getName(), message);
}
}