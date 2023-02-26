package telran.pulse.monitoring.service;

import java.time.LocalDateTime;

public interface SensorValuesService {
int getAverageDates(int sensorId, LocalDateTime from, LocalDateTime to);
long getJumpsCountDates (int sensorId, LocalDateTime from, LocalDateTime to);
}
