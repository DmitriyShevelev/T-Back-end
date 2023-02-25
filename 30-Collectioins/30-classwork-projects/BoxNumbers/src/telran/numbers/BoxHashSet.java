package telran.numbers;

import java.util.HashSet;

public class BoxHashSet extends BoxSet {
    public BoxHashSet() {
	this.collection = new HashSet<Integer>();
    }

    @Override
    protected BoxNumbers newBoxNumbers() {
	return new BoxHashSet();
    }
    
}
