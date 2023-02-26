package telran.concurrent;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyBlockingListQue<E> implements BlockingQueue<E> {

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		
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
