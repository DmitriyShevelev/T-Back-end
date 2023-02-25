import java.util.ArrayList;

public class JvmMemoryAllocationApp {

	private static final long MG = 1024 * 1024;
static Runtime runtime = Runtime.getRuntime();
	public static void main(String[] args) {
		
		System.out.printf("Maximal memory parameter -Xmx of JVM %dM\n",
				runtime.maxMemory() / MG);
		System.out.println("Before Allocations");
		printMemoryState();
		System.out.println("After Allocations");
		ArrayList<byte[]> allocations = new ArrayList<>();
		int allocationsCount = 0;
		while(true) {
			try {
				long freeMemory = runtime.freeMemory();
				long size = freeMemory < Integer.MAX_VALUE ? freeMemory :
					Integer.MAX_VALUE;
				byte[] ar = new byte[(int)size];
				allocations.add(ar);
				allocationsCount++;
				printMemoryState();
			} catch (OutOfMemoryError e) {
				break;
			}
			System.out.println("amount of allocations: " + allocationsCount);
		}
		
		
		

	}
	static void printMemoryState () {
		System.out.printf("free memory %dM ;  total memory %dM\n",
				runtime.freeMemory() / MG, runtime.totalMemory() / MG );
	}

}
