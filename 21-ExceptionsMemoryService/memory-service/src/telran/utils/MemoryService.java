package telran.utils;

public class MemoryService {
public static int getMaxAvailableMemory() {
	long max = Integer.MAX_VALUE;
	long min = 0;
	int middle = 0;
	int result = 0;
	while(min <= max) {
		middle = (int) (( min + max ) / 2);
		try {
			byte ar[] = new byte[middle];
			result = middle;
			min = middle + 1;
		} catch (OutOfMemoryError e) {
			max = middle - 1;
		}
	}
	return result;
}
}
