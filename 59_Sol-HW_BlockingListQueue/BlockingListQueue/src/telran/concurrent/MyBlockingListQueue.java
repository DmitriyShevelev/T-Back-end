package telran.concurrent;

import java.util.*;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class MyBlockingListQueue<E> implements BlockingQueue<E> {
	private static int defaultLimit = Integer.MAX_VALUE;

	public static int getDefaultCapacity() {
		return defaultLimit;
	}

	public static void setDefaultCapacity(int defaultCapacity) {
		MyBlockingListQueue.defaultLimit = defaultCapacity;
	}

	private Lock monitor = new ReentrantLock();
	private Condition consumerWaitingCondition = monitor.newCondition();
	private Condition producerWaitingCondition = monitor.newCondition();
	private Queue<E> queue = new LinkedList<>();
	private int limit;

	public MyBlockingListQueue(int limit) {
		this.limit = limit;
	}

	public MyBlockingListQueue() {
		this(defaultLimit);
	}

	@Override
	public E remove() {

		try {
			monitor.lock();
			E result = queue.remove();
			producerWaitingCondition.signal();
			return result;
		} finally {
			monitor.unlock();
		}
	}
	
	@Override
	public E poll() {
		try {
			monitor.lock();
			E result = queue.poll();
			if (result != null) {
				producerWaitingCondition.signal();
			}
			return result;
		} finally {
			monitor.unlock();
		}
	}

	@Override
	public E element() {
		try {
			monitor.lock();
			return queue.element();
		} finally {
			monitor.unlock();
		}
	}

	@Override
	public E peek() {
		try {
			monitor.lock();
			return queue.peek();
		} finally {
			monitor.unlock();
		}
	}

	@Override
	public int size() {
		try {
			monitor.lock();

			return queue.size();
		} finally {
			monitor.unlock();
		}
	}

	@Override
	public boolean isEmpty() {
		try {
			monitor.lock();
			return queue.isEmpty();
		} finally {
			monitor.unlock();
		}
	}

	@Override
	public Iterator<E> iterator() {
		// no implement
		return null;
	}

	@Override
	public Object[] toArray() {
		// no implement
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// no implement
		return null;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// no implement
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// no implement
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// no implement
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// no implement
		return false;
	}

	@Override
	public void clear() {
		// no implement

	}
	
	@Override
	public boolean add(E e) {
		try {
			monitor.lock();
			if (queue.size() == limit) {
				throw new IllegalStateException();
			}
			boolean res = queue.add(e);
			consumerWaitingCondition.signal();
			return res;
		} finally {
			monitor.unlock();
		}
	}
	
	@Override
	public boolean offer(E e) {
		try {
			monitor.lock();
			if (queue.size() == limit) {
				return false;
			}
			queue.offer(e);
			consumerWaitingCondition.signal();
			return true;
		} finally {
			monitor.unlock();
		}
	}

	@Override
	public void put(E e) throws InterruptedException {
		try {
			monitor.lock();
			while (queue.size() == limit) {
				producerWaitingCondition.await();
			}
			queue.add(e);
			consumerWaitingCondition.signal();

		} finally {
			monitor.unlock();
		}

	}

	@Override
	public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		try {
			monitor.lock();
			while (queue.size() == limit) {
				if (!producerWaitingCondition.await(timeout, unit)) {
					return false;
				}
			}
			queue.add(e);
			consumerWaitingCondition.signal();
			return true;

		} finally {
			monitor.unlock();
		}

	}
	
	@Override
	public E take() throws InterruptedException {
		try {
			monitor.lock();
			while (queue.isEmpty()) {
				consumerWaitingCondition.await();
			}
			E res = queue.poll();
			producerWaitingCondition.signal();
			return res;

		} finally {
			monitor.unlock();
		}
	}

	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		try {
			monitor.lock();
			while (queue.isEmpty()) {
				if (!consumerWaitingCondition.await(timeout, unit)) {
					return null;
				}
			}
			E res = queue.poll();

			producerWaitingCondition.signal();

			
			return res;

		} finally {
			monitor.unlock();
		}
	}

	@Override
	public int remainingCapacity() {
		// no implement
		return 0;
	}

	@Override
	public boolean remove(Object o) {
		// no implement
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// no implement
		return false;
	}

	@Override
	public int drainTo(Collection<? super E> c) {
		// no implement
		return 0;
	}

	@Override
	public int drainTo(Collection<? super E> c, int maxElements) {
		// no implement
		return 0;
	}

}
