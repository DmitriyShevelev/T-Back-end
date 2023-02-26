package telran.pulse.monitoring.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import telran.pulse.monitoring.entities.JumpDoc;

public interface JumpsRepository extends MongoRepository<JumpDoc, ObjectId> {

}
