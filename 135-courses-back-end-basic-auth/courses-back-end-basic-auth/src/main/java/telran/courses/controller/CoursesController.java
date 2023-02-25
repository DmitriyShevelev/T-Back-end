package telran.courses.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import telran.courses.api.dto.Course;
import telran.courses.service.CoursesService;

import static telran.courses.api.ApiConstants.*;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.validation.Valid;

@RestController
@RequestMapping(COURSES_MAPPING)
@Validated
@CrossOrigin
public class CoursesController {
	static Logger LOG = LoggerFactory.getLogger(CoursesController.class);
	@Autowired
private CoursesService coursesService;
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	 Course addCourse(@RequestBody @Valid Course course) {
		Course courseAdded = coursesService.addCourse(course);
		LOG.debug("added course with id {}",courseAdded.id);
		return courseAdded;
	}
	@GetMapping
	List<Course> getCourses() {
		List<Course> courses = coursesService.getAllCourses();
		LOG.trace("getting {} courses", courses.size());
		return courses;
	}
	@GetMapping("/{id}")
	ResponseEntity<?> getCourse(@PathVariable(name = "id") int id) {
		
		Course course = coursesService.getCourse(id);
		if(course != null) {
			LOG.debug("getting courses with id {}", id);
		} else {
			LOG.error("course with id {} not found", id);
		}
		
		return course == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("corse with id: %d not found",
				id)) : ResponseEntity.ok(course);
	}
	@DeleteMapping("/{id}")
	ResponseEntity<?> removeCourse(@PathVariable(name = "id") int id) {
		Course course = coursesService.removeCourse(id);
		if(course != null) {
			LOG.debug("course with id {} removed", id);
		} else {
			LOG.error("course with id {} not found", id);
		}
		return course == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("corse with id: %d not found",
				id)) : ResponseEntity.ok(course);
	}
	@PutMapping("/{id}")
	ResponseEntity<?> updateCourse(@PathVariable(name = "id") int id,
			@RequestBody @Valid Course newCourse) {
		if (newCourse.id != id) {
			LOG.error("dismatching id value: parameter {} but in course object {}",
					id, newCourse.id);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("id parameter doesn't match id value in new course");
		}
		Course course = coursesService.updateCourse(id, newCourse);
		if(course != null) {
			LOG.debug("course with id {} updated", id);
		} else {
			LOG.error("course with id {} not found", id);
		}
		return course == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("corse with id: %d not found",
				id)) : ResponseEntity.ok(course);
	}
	@PostConstruct
	void restore() {
		LOG.info("restoring courses");
		coursesService.restore();
	}
	@PreDestroy
	void save() {
		LOG.info("saving courses");
		coursesService.save();
	}
	
	
	
	
	
	
}
