package telran.numbers;

import java.util.LinkedList;

public class BoxLinkedList extends BoxCollection {
    public BoxLinkedList() {
	this.collection = new LinkedList<Integer>();
	
    }

    @Override
    protected BoxNumbers newBoxNumbers() {
	return new BoxLinkedList();
    }
    
}
