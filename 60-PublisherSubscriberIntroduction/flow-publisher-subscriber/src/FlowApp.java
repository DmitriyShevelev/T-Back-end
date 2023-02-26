import java.util.concurrent.*;

public class FlowApp {

	private static final int N_THREADS = 3;
	private static final int N_ITEMS = 200;
	private static final int BUFFER_SIZE = 16;
static int count = 0;
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);
		SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>(executor,
				BUFFER_SIZE);
		SimpleSubscriber subscriber = new SimpleSubscriber();
		SimpleSubscriber subscriber1 = new SimpleSubscriber();
		publisher.subscribe(subscriber);
		//publisher.subscribe(subscriber);
		//publisher.subscribe(subscriber1);
		
		for (int i = 0; i < N_ITEMS; i++) {
			System.out.printf("Thread: %s | publishing number: %d\n",
					Thread.currentThread().getName(), i);
			publisher.offer(i, (s, n) -> {
				count++;
				s.onError(new IllegalStateException(String.format("rejected: %d", n)));
				return true;
			});
		}
		publisher.close();
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.HOURS);
		System.out.println("Rejects " + count);
		
		

	}

}
