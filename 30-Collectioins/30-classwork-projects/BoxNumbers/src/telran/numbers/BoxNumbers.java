package telran.numbers;

public interface BoxNumbers extends Iterable<Integer>{
boolean contains(int num);
void add(int num);
void remove(int num);
int removeRepeated(); //returns amount of removed numbers
void removeInRange(int fromInclusive, int toInclusive);
void removeEven();
BoxNumbers intersection(BoxNumbers other);
}
