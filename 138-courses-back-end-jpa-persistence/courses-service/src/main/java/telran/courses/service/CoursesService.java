package telran.courses.service;

import java.util.List;

import telran.courses.api.dto.Course;

public interface CoursesService {
Course addCourse(Course course);
Course removeCourse(int id);
boolean exists(int id);
Course updateCourse(int id, Course course);
Course getCourse(int id);
List<Course> getAllCourses();
void restore();
void save();

}
