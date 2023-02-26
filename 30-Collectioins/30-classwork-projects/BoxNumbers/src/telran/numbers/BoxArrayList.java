package telran.numbers;

import java.util.ArrayList;

public class BoxArrayList extends BoxCollection {
    public BoxArrayList() {
	this.collection = new ArrayList<Integer>();
    }

    @Override
    protected BoxNumbers newBoxNumbers() {
	return new BoxArrayList();
    }
    
}
