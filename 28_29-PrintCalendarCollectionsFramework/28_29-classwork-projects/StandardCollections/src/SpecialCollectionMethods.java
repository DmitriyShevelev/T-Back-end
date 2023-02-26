import java.util.*;
public class SpecialCollectionMethods {
public static void main(String[] args) {
	List<Integer> list = Arrays.asList(3, 2, 1, 4, 5);
	//list.add(10); unsupported operation exception
//	list.set(0,  -100);
//	list.sort(Comparator.naturalOrder());
	final List<Integer> list1 = new ArrayList<>(list);
	list1.add(10);
	List<Integer> list2 = list1.subList(0, 3);
	list2.add(200);
	list1.sort(SpecialCollectionMethods::ourComparator);
	
	list1.forEach(SpecialCollectionMethods::printMethod);
	System.out.println("\n\n");
	TreeSet<Integer> tree = new TreeSet<>(list1);
	tree.forEach(SpecialCollectionMethods::printMethod);
	TreeSet<Integer> treeSub = new TreeSet<Integer>((TreeSet<Integer>) tree.subSet(4,true, 200, true));
	System.out.println();
	treeSub.forEach(SpecialCollectionMethods::printMethod);
	treeSub.clear();
	System.out.println();
	tree.forEach(SpecialCollectionMethods::printMethod);
	
}
public static void printMethod(Integer num) {
	System.out.print(num + " ");
}
public static int ourComparator(Integer i1, Integer i2) {
	if (i1 % 2 ==1 && i2 % 2 == 0) {
		return 1;
	}
	if (i1 % 2 == 0 && i2 % 2 == 1) {
		return -1;
	}
	if (i1 % 2 == 1 && i2 % 2 == 1) {
		return i1 - i2;
	}
	return i2 - i1;
}
}
