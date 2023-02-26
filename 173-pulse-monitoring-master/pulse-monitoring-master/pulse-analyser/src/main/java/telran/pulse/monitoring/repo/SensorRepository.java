package telran.pulse.monitoring.repo;

import org.springframework.data.repository.CrudRepository;

import telran.pulse.monitoring.entities.SensorLastValues;

public interface SensorRepository extends CrudRepository<SensorLastValues, Integer> {

}
