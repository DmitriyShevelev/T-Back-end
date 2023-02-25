package telran.students.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.students.entities.SubjectDoc;

public interface SubjectsRepository extends MongoRepository<SubjectDoc, Integer> {

}
