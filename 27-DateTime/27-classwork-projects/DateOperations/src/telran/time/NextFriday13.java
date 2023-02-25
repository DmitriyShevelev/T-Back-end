package telran.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextFriday13 implements TemporalAdjuster {

	public Temporal adjustInto(Temporal temporal) {
		do {	    
		    temporal = temporal.plus(1, ChronoUnit.DAYS);
		} while(temporal.get(ChronoField.DAY_OF_MONTH) != 13);
		
		while(temporal.get(ChronoField.DAY_OF_WEEK) != DayOfWeek.FRIDAY.getValue()) {
		    temporal = temporal.plus(1, ChronoUnit.MONTHS);
		}
		
		return temporal;
	    }
}


