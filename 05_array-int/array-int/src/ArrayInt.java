import java.util.Arrays;

public class ArrayInt {
	static final int MAX_SHORT = (1 << 15) - 1;
	// ar = [15,0, 20, 40, 15]; sum = 30
	public static boolean isSum2(short[] ar, short sum) {
		
		boolean helper[] = new boolean[sum == MAX_SHORT ? sum : sum + 1];
		short first = 0;
		short second = 0;
		boolean res = false;
		for (int i = 0; i < ar.length; i++) {
			first = ar[i];
			if (first <= sum) {
				second = (short) (sum - first);
				if (helper[second]) {
					res = true;
					break;
				} else {
					helper[first] = true;
				}
			}
		}
		return res;
	}
	public static void sort(short ar[]) {
		short counters[] = new short[MAX_SHORT];
		//index of counters is one number from ar
		//value at index is count of the number from ar
		//for example, counter[100] == 3 means 100 occurs in ar 3 times
		fillCounters(counters, ar);
		fillSortedArray(counters, ar);
	}
	private static void fillSortedArray(short[] counters, short[] ar) {
		int arInd = 0;
		for(int i = 0; i < counters.length && arInd < ar.length; i++) {
			for (int j = 0; j < counters[i]; j++) {
				ar[arInd++] = (short) i;
			}
		}
		
	}
	private static void fillCounters(short[] counters, short[] ar) {
		int index = 0;
		for (int i = 0; i < ar.length; i++) {
			index = ar[i];
			counters[index]++;
		}
		
		
	}
	public static int[] addNumber(int ar[], int num) {
		int[] res = Arrays.copyOf(ar, ar.length + 1);
		res[res.length - 1] = num;
		return res;
	}
	public static int[] insertNumber(int ar[], int index, int num) {
		//index [0-ar.length]
		//in the case of wrong index the function returns the same reference
		return null;
	}
}
