package telran.util.concurrent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.atomic.AtomicInteger;

import telran.util.concurrent.LoadBalancer;

public class FlowApp {

	private static final int N_THREADS = 4;
	private static final int N_ITEMS = 600;
	private static final int BUFFER_SIZE = Integer.MAX_VALUE;
	static AtomicInteger count = new AtomicInteger();

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);
		SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>(executor, BUFFER_SIZE);
		SimpleSubscriber subscriber = new SimpleSubscriber();
		LoadBalancer<Integer> loadBalancer = new LoadBalancer<>(executor, (Class<Subscriber<Integer>>)subscriber.getClass(), N_THREADS, BUFFER_SIZE);
		publisher.subscribe(loadBalancer);

		for (int i = 0; i < N_ITEMS; i++) {
			publisher.submit(i);
		}
		publisher.close();
		executor.awaitTermination(1, TimeUnit.HOURS);
		System.out.println("Rejects " + loadBalancer.getRejects());
	}

}

