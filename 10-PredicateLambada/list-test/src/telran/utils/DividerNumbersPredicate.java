package telran.utils;

import java.util.function.Predicate;

public class DividerNumbersPredicate implements Predicate<Integer> {
public DividerNumbersPredicate(int divider) {
		
		this.divider = divider;
	}
int divider;
	@Override
	public boolean test(Integer t) {
		
		return t % divider == 0;
	}

}
