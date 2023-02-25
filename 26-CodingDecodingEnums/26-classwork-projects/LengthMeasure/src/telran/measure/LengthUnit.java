package telran.measure;

public enum LengthUnit {
	MM(1.0f),
	CM(10.0f),
	M(1000.0f),
	IN(25.4f),
	FT(304.8f),
	KM(1000000.0f);
	
	private float value;
	
	LengthUnit(float value){
		this.value = value;
	}
	
	public float getValue() {
		return value;
	}
	
	public float between(Length l1, Length l2) {
		var o1 = l1.convert(this);
		var o2 = l2.convert(this);
		float res = o2.number - o1.number;	
		return res;
	}
}
