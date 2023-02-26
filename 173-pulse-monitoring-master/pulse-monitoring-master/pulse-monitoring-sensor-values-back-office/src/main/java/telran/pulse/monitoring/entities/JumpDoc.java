package telran.pulse.monitoring.entities;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "jumps")
public class JumpDoc {
	private int sensorId;
	private int previousValue;
	private int value;
	private LocalDateTime dateTime;
	
	public int getSensorId() {
		return sensorId;
	}
	public int getPreviousValue() {
		return previousValue;
	}
	public int getValue() {
		return value;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	
}
