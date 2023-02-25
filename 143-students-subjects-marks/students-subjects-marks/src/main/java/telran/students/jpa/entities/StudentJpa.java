package telran.students.jpa.entities;
import javax.persistence.*;

import telran.students.dto.Student;
@Entity
@Table(name = "students")
public class StudentJpa {
@Id
	int stid;
@Column(nullable = false, unique = true)
String name;
public static StudentJpa build(Student student) {
	StudentJpa res = new StudentJpa();
	res.name = student.name;
	res.stid = student.stid;
	return res;
}
public Student getStudentDto() {
	Student res = new Student();
	res.name = name;
	res.stid = stid;
	return res;
}

}
