package telran.concurrency;

import telran.concurrency.consumer.Consumer;
import telran.concurrency.producer.Producer;

public class Controller {

	private static final int N_CONSUMERS = 30;
	private static final int N_MESSAGES = 15000;
	public static void main(String[] args) throws InterruptedException {
		MessageBox messageBox = new MessageBox();
		Producer producer = new Producer(messageBox, N_MESSAGES);
		Consumer consumers[] = new Consumer[N_CONSUMERS];
		startConsumers(messageBox, consumers);
		producer.start();
		producer.join();
		finishConsumers(consumers);
		
		System.out.println(Consumer.count);

	}

	private static void startConsumers(MessageBox messageBox, Consumer[] consumers) {
		for(int i = 0; i < N_CONSUMERS; i++) {
			consumers[i] = new Consumer(messageBox);
			consumers[i].start();
		}
	}

	private static void finishConsumers(Consumer[] consumers) throws InterruptedException {
		for (Consumer consumer: consumers) {
			consumer.finish();
			consumer.interrupt();
			consumer.join();
		}
		
	}

	
	}