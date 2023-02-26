import java.util.Arrays;
import java.util.*;

public class StreamsIntroductionAppl {

	public static void main(String[] args) {
//		Random gen = new Random();
//		gen.ints(1, 10).filter(n -> {
//			//System.out.printf("Filter works on number %d\n", n);
//			return n % 2 == 0;
//		}).map(num -> num * 5).forEach(num -> System.out.print(num + " "));
//	int ar[] = {10, 25, 30, 45, 50, 65,70};
//	displayArray(ar);
		List<Integer> list = Arrays.asList(1 ,2,3,4);
		double avg = list.stream().filter(n -> n % 2 == 0)
		.mapToInt(x -> x).average().orElse(0);
		System.out.println(avg);
		

	}

	private static void displayArray(int[] ar) {
//			Arrays.stream(ar).parallel()
//			.forEach(x -> System.out.print(x + " "));
		new Random().ints(0, ar.length).distinct()
		.limit(ar.length).forEach(i -> System.out.print(ar[i] + " "));
		
		
	}

}
