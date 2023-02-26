package telran.numbers;

import java.util.TreeSet;

public class BoxTreeSet extends BoxSet {

	public BoxTreeSet() {
		collection = new TreeSet<Integer>(); 
	}
	
	@Override
	protected BoxNumbers newBoxNumbers() {
		return new BoxTreeSet();
	}
	
	@Override
	public void removeInRange(int fromInclusive, int toInclusive) {
		((TreeSet<Integer>) collection).subSet(fromInclusive, true,
				toInclusive, true).clear();
	}
}
