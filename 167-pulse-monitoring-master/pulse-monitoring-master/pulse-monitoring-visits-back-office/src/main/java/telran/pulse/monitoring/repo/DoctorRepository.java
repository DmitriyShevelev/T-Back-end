package telran.pulse.monitoring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.pulse.monitoring.entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, String> {

}
