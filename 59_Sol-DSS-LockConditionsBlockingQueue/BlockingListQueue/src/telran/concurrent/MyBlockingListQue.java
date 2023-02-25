package telran.concurrent;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingListQue<E> implements BlockingQueue<E> {

	private static final int limit = 10;
	private Lock monitor = new ReentrantLock();
	private Queue<E> queue = new LinkedList<>();
	private Condition consumerWaiting = monitor.newCondition();
	private Condition producerWaiting = monitor.newCondition();
	
	@Override
	public E remove() {
		monitor.lock();
		try {
			E res = queue.remove();
			producerWaiting.signal();
			return res;
		} finally {
			monitor.unlock();
		}
	}

	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peek() {
		monitor.lock();
		try {
			return queue.peek();
		} finally {
			monitor.unlock();
		}
	}

	@Override
	public int size() {
		int res;
		monitor.lock();
		try {
			if (E == null) {
				res = 0;
			}
			else {
				res = queue.size(); // Don't use "res > Integer.MAX_VALUE ? Integer.MAX_VALUE : res;" due to queue.size() checked it already.
			}
		}
		finally {
			monitor.unlock();
		}
		return res;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
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
		// no imlement
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offer(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void put(E e) throws InterruptedException {
		monitor.lock();
		try {
			if (e == null) {
				throw new NullPointerException();
			}
			while (size() == limit) {
				producerWaiting.await();
			}
			queue.add(e);
			consumerWaiting.signal(); 		
			}
		finally {
			monitor.unlock();
			}
	}

	@Override
	public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		// no implement
		return false;
	}

	@Override
	public E take() throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		// no implement
		return null;
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
