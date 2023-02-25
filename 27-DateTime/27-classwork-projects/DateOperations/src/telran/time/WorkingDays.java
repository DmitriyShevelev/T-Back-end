package telran.time;

import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class WorkingDays implements TemporalAdjuster {

int amountOfWorkingDays;
int daysOffValues[];
public WorkingDays(int amountOfWorkingDays, int []daysOffValues) {
	
	this.amountOfWorkingDays = amountOfWorkingDays;
	this.daysOffValues = daysOffValues;
}
	@Override
	public Temporal adjustInto(Temporal temporal) {
		//TODO
		return null;
	}

}
