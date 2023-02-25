package telran.courses.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import telran.courses.api.ApiConstants;
import telran.courses.api.dto.Course;
import telran.courses.service.CoursesService;
import telran.exceptions.WrongInputDataException;

import static telran.courses.api.ApiConstants.*;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.validation.Valid;
import javax.validation.constraints.*;

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
		LOG.debug("added course with id {}", courseAdded.id);
		return courseAdded;
	}

	@GetMapping
	List<Course> getCourses() {
		LOG.debug("authenticated user {}",
				((UserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername());
		List<Course> courses = coursesService.getAllCourses();
		LOG.trace("getting {} courses", courses.size());
		return courses;
	}

	@GetMapping("/{id}")
	Course getCourse(@PathVariable(name = "id") @Min(ApiConstants.MIN_ID) @Max(ApiConstants.MAX_ID) int id) {

		Course course = coursesService.getCourse(id);
		LOG.debug("getting courses with id {}", id);

		return course;
	}

	@DeleteMapping("/{id}")
	Course removeCourse(@PathVariable(name = "id") @Min(ApiConstants.MIN_ID) @Max(ApiConstants.MAX_ID) int id) {
		Course course = coursesService.removeCourse(id);
		LOG.debug("course with id {} removed", id);
		return course;
	}

	@PutMapping("/{id}")
	Course updateCourse(@PathVariable(name = "id") @Min(ApiConstants.MIN_ID) @Max(ApiConstants.MAX_ID) int id,
			@RequestBody @Valid Course newCourse) {
		if (newCourse.id != id) {
			throw new WrongInputDataException(
					String.format("dismatching id value:" + " parameter %s but in course object %s", id, newCourse.id));
		}
		Course course = coursesService.updateCourse(id, newCourse);
		LOG.debug("course with id {} updated", id);
		return course;
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
