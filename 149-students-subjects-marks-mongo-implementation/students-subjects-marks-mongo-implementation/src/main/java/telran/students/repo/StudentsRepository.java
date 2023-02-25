package telran.students.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.students.entities.StudentDoc;

public interface StudentsRepository extends MongoRepository<StudentDoc, Integer> {

	StudentDoc findByName(String name);

	List<StudentDoc> findByNameIn(List<String> names);

}
