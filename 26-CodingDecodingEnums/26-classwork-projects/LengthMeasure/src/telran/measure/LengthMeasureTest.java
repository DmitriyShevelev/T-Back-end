package telran.measure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LengthMeasureTest {

	@Test
	void convertTest() {
		Length obj = new Length(10, LengthUnit.M);
		assertEquals(1000, obj.convert(LengthUnit.CM).getLength());
	}
	
	@Test
	void compareTest() {
		Length obj1 = new Length(10, LengthUnit.M);
		Length obj2 = new Length(10, LengthUnit.CM);
		assertTrue(obj1.compareTo(obj2) > 0);

		Length obj3 = new Length(10, LengthUnit.M);
		Length obj4 = new Length(100, LengthUnit.M);
		assertTrue(obj3.compareTo(obj4) < 0);
		
		Length obj5 = new Length(100, LengthUnit.M);
		Length obj6 = new Length(100, LengthUnit.M);
		assertEquals(0, obj5.compareTo(obj6));
	}
	
	@Test
	void betweenTest() {
		Length obj1 = new Length(10, LengthUnit.M);
		Length obj2 = new Length(100, LengthUnit.M);
		var res = LengthUnit.CM.between(obj1, obj2);
		
		assertEquals(9000, res);
	}


}
