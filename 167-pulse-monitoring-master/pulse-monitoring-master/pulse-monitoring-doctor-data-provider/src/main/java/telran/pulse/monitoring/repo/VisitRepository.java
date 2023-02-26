package telran.pulse.monitoring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.pulse.monitoring.entities.Visit;

public interface VisitRepository extends JpaRepository<Visit, Integer>{
@Query(value="select doctor_email from visits where patient_id=:patientId order by date desc limit 1", nativeQuery = true)	
String getDoctorEmail(@Param("patientId")int patientId);
}
