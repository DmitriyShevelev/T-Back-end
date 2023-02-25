package telran.courses.service;



import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.courses.api.dto.Course;
import telran.courses.mongo.documents.CourseDocument;
import telran.courses.mongo.repo.CoursesMongoRepository;
@Service
public class CoursesServiceMongoImpl implements CoursesService {
	@Value("${app.id.max: 999999}")
	int maxId;
	@Value("${app.id.min: 100000}")
	int minId;
@Autowired
	CoursesMongoRepository coursesRepository;
	@Override
	public Course addCourse(Course course) {
		course.id = generateId();
		CourseDocument courseDoc = CourseDocument.build(course);
		return coursesRepository.insert(courseDoc).getCourseDto();
	}

	@Override
	public Course removeCourse(int id) {
		Course course = getCourse(id);
		if (course != null) {
			coursesRepository.deleteById(id);
		}
		
		return course;
	}

	@Override
	public boolean exists(int id) {
		
		return coursesRepository.existsById(id);
	}

	@Override
	@Transactional
	public Course updateCourse(int id, Course course) {
		
		Course res = getCourse(id);
		if (res != null) {
			course.id = id;
			CourseDocument courseDoc = CourseDocument.build(course);
			coursesRepository.save(courseDoc);
		}
		return res;
	}

	@Override
	public Course getCourse(int id) {
		CourseDocument courseDoc = coursesRepository.findById(id).orElse(null);
		return courseDoc == null ? null : courseDoc.getCourseDto();
	}

	@Override
	public List<Course> getAllCourses() {
		
		return coursesRepository.findAll().stream().map(CourseDocument::getCourseDto).toList();
	}

	@Override
	public void restore() {
		

	}

	@Override
	public void save() {
		

	}
	private Integer generateId() {
	    ThreadLocalRandom random = ThreadLocalRandom.current();
	    int randomId;

	    do {
	        randomId = random.nextInt(minId, maxId);
	    } while (exists(randomId));
	    return randomId;
	}

}
