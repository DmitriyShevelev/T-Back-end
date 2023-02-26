
public enum WeightUnit {
GR(1.0f), KG(1000.0f), TN(1000000.0f), CN(100000.0f), LBS(453.592f);
	private float value;
	 WeightUnit(float value) {
		this.value = value;
	}
	 public float getValue() {
		 return value;
	 }
	 public float convert (int amount, WeightUnit weightUnit) {
		 return value * amount / weightUnit.value;
	 }
	 
}
