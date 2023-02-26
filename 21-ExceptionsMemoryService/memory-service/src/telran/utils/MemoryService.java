package telran.utils;

public class MemoryService {
public static int getMaxAvailableMemory() {
	int res = Integer.MAX_VALUE;
	boolean running = true;
	while(running) {
		try {
			byte ar[] = new byte[res];
			running = false;
		} catch (OutOfMemoryError e) {
			res /= 2;
		}
	}
	return res;
}
}
