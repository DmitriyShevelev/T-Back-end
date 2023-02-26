package telran.concurrency.consumer;

import telran.concurrency.MessageBox;

public class Consumer extends Thread {
private  MessageBox messageBox;

public Consumer(MessageBox messageBox) {
	this.messageBox = messageBox;
	setDaemon(true);
}

public void setMessageBox(MessageBox messageBox) {
	this.messageBox = messageBox;
}  
@Override
public void run() {
	while(true) {
		try {
			String message = messageBox.takeMessage();
			System.out.printf("thread: %s, message: %s\n", getName(), message);
		} catch (InterruptedException e) {
			//TODO
		}
	}
}
}
