package telran.pulse.monitoring.entities;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import telran.pulse.monitoring.dto.SensorJump;

@Document(collection = "jumps")
public class JumpDoc {
	private int sensorId;
	private int previousValue;
	private int value;
	private LocalDateTime dateTime;
	public static JumpDoc build(SensorJump sensorJump) {
		JumpDoc jumpDoc = new JumpDoc();
		jumpDoc.sensorId = sensorJump.sensorId;
		jumpDoc.previousValue = sensorJump.previousValue;
		jumpDoc.value = sensorJump.currentValue;
		jumpDoc.dateTime = LocalDateTime.ofInstant
				(Instant.ofEpochMilli(sensorJump.timestamp), ZoneId.systemDefault());
		return jumpDoc;
	}
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
