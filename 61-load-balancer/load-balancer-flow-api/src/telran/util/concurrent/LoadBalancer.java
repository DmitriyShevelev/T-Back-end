package telran.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

public class LoadBalancer<T> implements Subscriber<T> {

	private ExecutorService executor;
	private Class<Subscriber<T>> clazz;
	private int nThreads;
	private int bufferSize;
	private Subscription subscription;
	private SubmissionPublisher<T>[] publishers;
	private int counterTasks;
	private int counterReject;

	@SuppressWarnings("unchecked")
	public LoadBalancer(ExecutorService executor, Class<Subscriber<T>> clazz, int nThreads, int bufferSize) {
		this.executor = executor;
		this.clazz = clazz;
		this.nThreads = nThreads;
		this.bufferSize = bufferSize;
		publishers = new SubmissionPublisher[nThreads];
	}

	@Override
	public void onComplete() {
		for (SubmissionPublisher<T> publisher : publishers) {
			publisher.close();
		}
		System.out.println("Complete!");
		executor.shutdown();
	}

	@Override
	public void onError(Throwable throwable) {
		System.out.printf("Error: %s, message: %s\n", throwable.getClass().getSimpleName(), throwable.getMessage());
	}
	
	@Override
	public void onNext(T item) {
		publishers[counterTasks % nThreads].offer(item, (s,  n) -> {
			counterReject++;
			return true;
		});
		counterTasks++;
		subscription.request(1);
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		try {
			var<Subscriber<T>> constructorSubscriber = clazz.getConstructor();
			for (int i = 0; i < nThreads; i++) {
				publishers[i] = new SubmissionPublisher<>(executor, bufferSize);
				publishers[i].subscribe(constructorSubscriber.newInstance());
			}
		} catch (Exception e) {
			throw new RuntimeException(String.format("Exception: %s, message: %s",
					e.getClass().getSimpleName(), e.getMessage()));
		}
		subscription.request(1);
		
		System.out.println("Subscribed!");
	}
	
	public int getRejects() {
		return counterReject;
	}

}