package telran.utils;

public class DividerRule implements Rule {
public DividerRule(int divider) {
		
		this.divider = divider;
	}
int divider;
	@Override
	public void checkRule(int number, int min, int max) throws NoRuleMatchException {
		int delta = 0;
		if (number < min || number > max) {
			throw new IllegalArgumentException(String.format("Number %d "
					+ "doesn't belong to range [%d, %d]", number, min, max));
		}
		delta = getDelta(number, min, max);
		if (delta != 0) {
			throw new NoRuleMatchException(delta);
		}

	}
	private int getDelta(int number, int min, int max) {
		int remainder = number % divider;
		if (remainder == 0) {
			return 0;
		}
		
		int deltaAfter = divider - remainder;
		if (number - remainder < min && number + deltaAfter > max) {
			throw new IllegalArgumentException(String.format("impossible"
					+ " find a number divided by %d in range [%d - %d]",
					divider, min, max));
		}
		int delta = 0;
		if (number - remainder < min) {
			delta = deltaAfter; 
		} else if (number + deltaAfter > max) {
			delta = -remainder;
		} else {
			delta = remainder <= deltaAfter ? -remainder : deltaAfter;
		}
		return delta;
		
	}
	
	
	

}
