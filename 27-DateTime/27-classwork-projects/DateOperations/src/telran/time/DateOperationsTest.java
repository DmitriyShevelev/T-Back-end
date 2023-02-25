package telran.time;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

class DateOperationsTest {

	@Test
	void nextFriday13Test() {
		LocalDate from = LocalDate.of(2021, 8, 5);
		LocalDate expected = LocalDate.of(2021, 8, 13);
		assertEquals(expected, DateOperations.nextFriday13(from));
		from = expected;
		LocalDate expected1 = LocalDate.of(2022, 5, 13);
		assertEquals(expected1, DateOperations.nextFriday13(from));
		LocalDate actual = DateOperations.nextFriday13(null);
		assertEquals(13, actual.getDayOfMonth());
		assertEquals(DayOfWeek.FRIDAY, actual.getDayOfWeek());
		assertTrue(actual.compareTo(expected) >= 0);
		System.out.println(DayOfWeek.FRIDAY.getValue());
		
	}
	@Test
	void nextFridayAdjusterTest() {
		LocalDate from = LocalDate.of(2021, 8, 5);
		LocalDate expected = LocalDate.of(2021, 8, 13);
		assertEquals(expected, from.with(new NextFriday13()));
		LocalDateTime  fromLdt = LocalDateTime.of(from, LocalTime.of(0, 0));
		LocalDateTime expectedLdt = LocalDateTime.of(expected, LocalTime.of(0, 0));
		assertEquals(expectedLdt, fromLdt.with(new NextFriday13()));
	
		
		
	}
	@Test
	void workingDaysTest() {
		DayOfWeek[] allDaysOff = DayOfWeek.values();
		DayOfWeek[] noDaysOff = new DayOfWeek[0];
		DayOfWeek[] israelDaysOff = {
				DayOfWeek.FRIDAY, DayOfWeek.SATURDAY
		};
		LocalDate from = LocalDate.of(2021, 8, 5);
		LocalDate to = LocalDate.of(2021, 8, 8);
		assertEquals(ChronoUnit.DAYS.between(from, to),
				DateOperations.workingDays(from, to, noDaysOff));
		assertEquals(0,
				DateOperations.workingDays(from, to, allDaysOff));
		assertEquals(1,
				DateOperations.workingDays(from, to, israelDaysOff));
	}
	@Test
	void workingDaysAdjusterTest() {
		
		int[] noDaysOff = {};
		int[] israelDaysOff = {
				DayOfWeek.FRIDAY.getValue(), DayOfWeek.SATURDAY.getValue()
		};
		LocalDate from = LocalDate.of(2021, 8, 5);
		LocalDate to = LocalDate.of(2021, 8, 8);
		assertEquals(to, from.with(new WorkingDays(3, noDaysOff)));
		
		assertEquals(to, from.with(new WorkingDays(1,israelDaysOff)));
	}

}
