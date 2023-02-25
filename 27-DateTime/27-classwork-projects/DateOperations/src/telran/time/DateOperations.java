package telran.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class DateOperations {
/**
 * 
 * @param from
 * @return nearest date of Friday, 13
 * if from is null the current date is implied 
 */
	public static LocalDate nextFriday13(LocalDate from) {
		if (from == null) {
			from = LocalDate.now();
		}
		var day13 = getFirst13(from);
		return getFriday13(day13);
	}

	private static LocalDate getFriday13(LocalDate day13) {
		
		while (day13.getDayOfWeek() != DayOfWeek.FRIDAY) {
			day13 = day13.plusMonths(1);
		}
		return day13;
	}

	private static LocalDate getFirst13(LocalDate from) {
		
		 do{
			from = from.plusDays(1);
		}while (from.getDayOfMonth() != 13);
		return from;
	}
	public static int workingDays(LocalDate from, LocalDate to,
			DayOfWeek[] daysOff) {
		List<DayOfWeek> listDaysOff = Arrays.asList(daysOff);
		
		int count = 0;
		while (!from.equals(to)) {
			if (!listDaysOff.contains(from.getDayOfWeek())) {
				count ++;
			}
			from = from.plusDays(1);
		}
		return count;
	}

	
}
