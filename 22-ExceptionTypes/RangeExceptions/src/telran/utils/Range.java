package telran.utils;

public class Range {
private int min;
private int max;
public Range (int min, int max) {
	if (min >= max) {
		throw new IllegalArgumentException(String.format
				("min %d can't be greater or equal max %d", min, max));
	}
	this.min = min;
	this.max = max;
}
/**
 * 
 * @param number
 * @throws GreaterMaxRangeException
 * @throws LessMinRangeException
 */
public void checkNumber(int number) throws GreaterMaxRangeException,
LessMinRangeException {
	if (number > max) {
		throw new GreaterMaxRangeException();
	}
	if (number < min) {
		throw new LessMinRangeException();
	}
}
}
