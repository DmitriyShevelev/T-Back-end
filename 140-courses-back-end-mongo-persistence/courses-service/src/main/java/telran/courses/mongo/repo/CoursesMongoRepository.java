package telran.courses.mongo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.courses.mongo.documents.CourseDocument;

public interface CoursesMongoRepository extends MongoRepository<CourseDocument, Integer> {

}
