package telran.courses.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.courses.api.dto.Course;
import telran.courses.jpa.entities.CourseJpa;
import telran.courses.jpa.repo.CoursesRepository;
@Service
public class CoursesServiceJpaImpl implements CoursesService {
@Autowired
	CoursesRepository courses;
	@Override
	public Course addCourse(Course course) {
		CourseJpa courseJpa = CourseJpa.build(course);
		CourseJpa resJpa = courses.save(courseJpa);
		return resJpa.getCourseDto();
	}

	@Override
	@Transactional
	public Course removeCourse(int id) {
		Course oldCourse = getCourse(id);
		if (oldCourse != null) {
			courses.deleteById(id);
		}
		
		return oldCourse;
	}

	@Override
	public boolean exists(int id) {
		
		return courses.existsById(id);
	}

	@Override
	@Transactional
	public Course updateCourse(int id, Course course) {
		Course oldCourse = getCourse(id);
		if (oldCourse != null) {
			CourseJpa courseJpa = courses.getById(id);
			CourseJpa.fillCourseJpa(course, courseJpa);
		}
		return oldCourse;
	}

	@Override
	public Course getCourse(int id) {
		CourseJpa resJpa = courses.findById(id).orElse(null);
		return resJpa == null ? null : resJpa.getCourseDto();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> getAllCourses() {
		
		return courses.findAll().stream().map(CourseJpa::getCourseDto).toList();
	}

	@Override
	public void restore() {
		

	}

	@Override
	public void save() {
		

	}

}
