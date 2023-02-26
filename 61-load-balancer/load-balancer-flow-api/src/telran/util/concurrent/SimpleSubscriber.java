package telran.util.concurrent;
import java.util.concurrent.Flow.*;

public class SimpleSubscriber implements Subscriber<Integer> {
Subscription subscription;
	@Override
	public void onComplete() {
		System.out.println("Completed");

	}

	@Override
	public void onError(Throwable throwable) {
		System.out.printf("Error: %s, message: %s\n",
				throwable.getClass().getSimpleName(), throwable.getMessage());

	}

	@Override
	public void onNext(Integer item) {
		System.out.printf("Thread: %s | receiving number: %s\n", 
				Thread.currentThread().getName(), item);
		try {
			Thread.sleep(10);//imitation of slow subscriber
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		subscription.request(1);
		
		

	}

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		System.out.println("Subscribed");
		this.subscription.request(1);

	}

}
