package telran.pulse.monitoring.service;

import java.time.LocalDateTime;
import java.util.List;

import telran.pulse.monitoring.dto.VisitData;

public interface VisitsService {
void addPatient(int patientId, String name);
void addDoctor(String email, String name);
void addVisit(int patientId, String email, LocalDateTime dateTime);
List<VisitData> getVisits(int patientId, LocalDateTime from, LocalDateTime to);
}
