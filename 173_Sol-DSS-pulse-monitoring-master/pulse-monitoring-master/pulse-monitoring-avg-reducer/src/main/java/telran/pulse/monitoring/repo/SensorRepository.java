package telran.pulse.monitoring.repo;

import org.springframework.data.repository.CrudRepository;

import telran.pulse.monitoring.entities.SensorList;

public interface SensorRepository extends CrudRepository<SensorList, Integer> {

}
