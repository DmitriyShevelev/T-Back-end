import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.*;

public class StreamsIntroductionAppl {

	private static final int N_NUMBERS = 1_000_000;
	private static final int MIN_VALUE = 1;
	private static final int MAX_VALUE = Integer.MAX_VALUE;

	public static void main(String[] args) {
//		Random gen = new Random();
//		gen.ints(1, 10).filter(n -> {
//			//System.out.printf("Filter works on number %d\n", n);
//			return n % 2 == 0;
//		}).map(num -> num * 5).forEach(num -> System.out.print(num + " "));
//	int ar[] = {10, 25, 30, 45, 50, 65,70};
//	displayArray(ar);
		List<Integer> list = Arrays.asList(10, 1 ,2,3,4);
//		double avg = list.stream().filter(n -> n % 2 == 0)
//		.mapToInt(x -> x).max().orElse(0);
//		System.out.println(avg);
		//What do I want to get?
		//odd: 1, 3
		//even: 2, 4
//		Map<String, List<Integer>> map = 
//		list.stream()
//		.collect(Collectors.groupingBy(num -> num % 2 == 0 ?
//				"even" : "odd"));
//		map.entrySet().stream().sorted((e1, e2)->
//		e2.getKey().compareTo(e1.getKey()))
//		.forEach(StreamsIntroductionAppl::displayInFormat);
//		
//		System.out.println
//		(list.stream()
//		.collect(Collectors.groupingBy(num -> num % 2 == 0 ?
//				"even" : "odd", Collectors.counting())));
//
//		int []array = getRandomNumbers(N_NUMBERS, MIN_VALUE,
//				MAX_VALUE);
//		displayCountsByDigitsAmount(array);
//		
		
//			String strings[] = {"a","lmn", "ab", "ab", "a", "ba","lmn", "lmn"};
//			//output
//			//lmn -> 3
//			//a -> 2
//			//ab -> 2
//			//ba -> 1
//			displayOccurrences(strings);
		int [] array = getRandomNumbers(N_NUMBERS, MIN_VALUE, MAX_VALUE);
		displayDigitOccurrences(array);
		

	}
	private static void displayDigitOccurrences(int[] array) {
		//Example, 
		//1: <occurrences>
		//2: .......
		Arrays.stream(array)
		.mapToObj(Integer::toString)
		/*.flatMap(str -> Arrays.stream(str.split("")))*/
		.flatMapToInt(String::chars).boxed()
		.collect(Collectors.groupingBy(x->x, Collectors.counting()))
		.entrySet().stream().sorted((e1, e2) ->
		Long.compare(e2.getValue(), e1.getValue()))
		
		
		.forEach(e -> System.out.printf("%c: %d\n",e.getKey(),
				e.getValue() ));
		
		
	}
	private static void displayOccurrences(String[] strings) {
		Arrays.stream(strings).collect(Collectors.groupingBy(str -> str,
				Collectors.counting())).entrySet().stream()
		.sorted((a, b) -> {
		    int res = Long.compare(b.getValue(), a.getValue());

		    return res == 0 ? a.getKey().compareTo(b.getKey()) : res;
		}).forEach(e -> {
		    System.out.printf("%s -> %d\n", e.getKey(), e.getValue());
		});
		
	}
	private static void displayCountsByDigitsAmount(int[] array) {
		System.out.println(
			Arrays.stream(array).boxed()
			.collect(Collectors.groupingBy(num ->
			num.toString().length(), Collectors.counting()))
					
				);
		
	}
	private static int[] getRandomNumbers(int nNumbers, int minValue, int maxValue) {
		return new Random().ints( nNumbers, minValue, maxValue).toArray();
	}
	private static void displayInFormat(Map.Entry<String,
			List<Integer>> e) {
		{
			String k = e.getKey();
			List<Integer> v = e.getValue();
			System.out.print(k + ": ");
			System.out.print(v.get(0));
			int size = v.size();
			for (int i = 1; i < size; i++) {
				System.out.printf(", %d", v.get(i));
			}
			System.out.println();
		}
	}

	private static void displayArray(int[] ar) {
//			Arrays.stream(ar).parallel()
//			.forEach(x -> System.out.print(x + " "));
		new Random().ints(0, ar.length).distinct()
		.limit(ar.length).forEach(i -> System.out.print(ar[i] + " "));
		
		
	}
	

}
