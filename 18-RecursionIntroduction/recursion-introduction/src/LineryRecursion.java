
public class LineryRecursion {

	public static long pow(int a, int b) {
		//TODO with no cycles or no multiply operation
		return 0;
	}
	public static int sum(int ar[]) {
		
		return sumR(0, ar);
	}
	private static int sumR(int index, int[] ar) {
		if (index >= ar.length) {
			return 0;
		}
		return ar[index] + sumR(index + 1, ar);
	}
	public static int square(int x) {
		//no cycles, no multiply, no additional methods, no static fields
		return 0;
	}
	public static boolean isSubstring(String str, String substr) {
		//TODO write function
		//boolean isSubstring (String str, String substr)
		//that returns true if a given 'substr' is indeed the substring of a given 'string'.
		//Challenges: 1. To apply only following methods of the class String:
		//charAt(int ind); String substring(int ind); int length(); 2. No cycles;
		return false;
	}

}
