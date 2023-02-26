package telran.courses.mongo.documents;
import java.time.LocalDate;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import telran.courses.api.dto.Course;
import telran.courses.api.dto.CourseType;
@Document(collection = "courses")
public class CourseDocument {
@Id
int id;
public String courseName;
public String lecturerName;
public int hoursNum;
public int cost;
public LocalDate startDate;
public CourseType type;

String [] dayEvening;
static public CourseDocument build(Course course) {
	CourseDocument res = new CourseDocument();
	res.id = course.id;
	fillCourseDocument(course, res);
	return res;
}
public static void fillCourseDocument(Course course, CourseDocument res) {
	res.courseName = course.courseName;
	res.cost = course.cost;
	res.dayEvening = course.dayEvening;
	res.hoursNum = course.hoursNum;
	res.lecturerName = course.lecturerName;
	res.startDate = LocalDate.parse(course.startDate.subSequence(0, 10));
	res.type = course.type;
}
public Course getCourseDto() {
	Course res = new Course();
	res.cost = cost;
	res.hoursNum = hoursNum;
	res.lecturerName = lecturerName;
	res.dayEvening = dayEvening;
	res.type = type;
	res.courseName = courseName;
	res.startDate = startDate.toString();
	res.id = id;
	return res;
}
}
