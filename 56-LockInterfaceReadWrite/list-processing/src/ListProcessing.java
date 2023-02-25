import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ListProcessing extends Thread {
final List<Integer> list;
static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
static final Lock lockRead = lock.readLock();
static final Lock lockWrite =lock.writeLock();
static final AtomicLong counter = new AtomicLong(0);

final int probUpdate;
final int nRuns;
public ListProcessing(List<Integer> list, int probUpdate, int nRuns) {
	this.list = list;
	this.probUpdate = probUpdate;
	this.nRuns = nRuns;
}
@Override
public void run() {
	for (int i = 0; i < nRuns; i++) {
		if (Math.random()*100 < probUpdate) {
			update();
		} else {
			read();
		}
	}
}
private void read() {
		lock(lockRead);
		try {
			int lastIndex = list.size() - 1;
			for (int i = 0; i < 100; i++) {
				list.get(lastIndex);
			} 
		} finally {
			unlock(lockRead);
		}
	
}
private void update() {
		lock(lockWrite);
		try {
			int lastIndex = list.size() - 1;
			list.remove(lastIndex);
			list.add(lastIndex, 100);
		} finally {
			unlock(lockWrite);
		}
	
}
private void unlock(Lock lock) {
	lock.unlock();
	
}
private void lock(Lock lock) {
	while(!lock.tryLock()) {
		counter.incrementAndGet();
	}
	
}
}
