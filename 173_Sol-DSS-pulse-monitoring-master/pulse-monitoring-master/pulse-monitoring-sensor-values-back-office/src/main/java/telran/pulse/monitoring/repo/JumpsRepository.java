package telran.pulse.monitoring.repo;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import telran.pulse.monitoring.entities.JumpDoc;

public interface JumpsRepository extends MongoRepository<JumpDoc, ObjectId> {

	long countBySensorIdAndDateTimeBetween(int sensorId, LocalDateTime from, LocalDateTime to);

}
