
public class RecursionIntroduction {
public static void main(String[] args) {
	System.out.println(factorial(4));
}
static long factorial(int a) {
	if (a == 0) {
		return 1;
	}
	return a * factorial(a - 1);
	
}
}
