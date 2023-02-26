package telran.pulse.monitoring.entities;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection="average_values")
public class SensorDoc {
private int sensorId;
private int value;
private LocalDateTime dateTime;

public int getSensorId() {
	return sensorId;
}
public int getValue() {
	return value;
}
public LocalDateTime getDateTime() {
	return dateTime;
}

}
