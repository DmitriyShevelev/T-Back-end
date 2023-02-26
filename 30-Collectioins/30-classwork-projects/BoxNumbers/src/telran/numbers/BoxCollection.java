package telran.numbers;

import java.util.*;

public abstract class BoxCollection implements BoxNumbers {
	protected Collection<Integer> collection;

	@Override
	public Iterator<Integer> iterator() {

		return collection.iterator();
	}

	@Override
	public boolean contains(int num) {

		return collection.contains(num);
	}

	@Override
	public void add(int num) {
		collection.add(num);

	}

	@Override
	public void remove(int num) {
		collection.remove(num);

	}

	@Override
	public int removeRepeated() {
		// implementation based on HashSet helper
//		HashSet<Integer> helper = new HashSet<>();
//		int sizeOld = collection.size();
//		collection.removeIf(num -> !helper.add(num));
//		return sizeOld - collection.size();
		// implementation based on LinkedHashSet
		LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(collection);
		int colSize = collection.size();
		collection.clear();
		collection.addAll(hashSet);
		return colSize - hashSet.size();
	}

	@Override
	public void removeInRange(int fromInclusive, int toInclusive) {
		collection.removeIf(num -> num >= fromInclusive && num <= toInclusive);

	}

	@Override
	public void removeEven() {
		collection.removeIf(num -> num % 2 == 0);

	}

	protected abstract BoxNumbers newBoxNumbers();

	@Override
	// the method returns new BoxNumbers of the same class
	// new BoxNumbers will contain not repeated numbers existing in both
	// BoxNumber objects: this and other
	public BoxNumbers intersection(BoxNumbers other) {
		BoxNumbers result = newBoxNumbers();
		BoxCollection otherBox = (BoxCollection) other;
		Collection<Integer> helper = 
				otherBox.collection instanceof Set ? 
						otherBox.collection :
							new HashSet<Integer>(otherBox.collection);
		intersectionCollection(result, helper);
		result.removeRepeated();
		return result;
	}
	private void intersectionCollection(BoxNumbers box, Collection<Integer> helper) {
		forEach(num -> {
			if (helper.contains(num)) {
				box.add(num);
			}
		});
	}

}
