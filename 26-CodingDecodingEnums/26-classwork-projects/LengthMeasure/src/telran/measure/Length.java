package telran.measure;

public class Length implements Comparable<Length>{
	float number;
	LengthUnit unit;
	
	Length(float number, LengthUnit unit) {
		this.number = number;
		this.unit = unit;
	}

	@Override
	public int compareTo(Length o) {
		
		var o2 = o.convert(unit);
		
		return Float.compare(number, o2.number);
	}
	
	public float getLength() {
		return number;
	}
	
	public Length convert(LengthUnit unit) {
		return new Length(number * this.unit.getValue() / unit.getValue(), unit);
	}

	
	
}
