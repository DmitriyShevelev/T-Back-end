package telran.students.controller;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import telran.students.dto.Mark;
import telran.students.dto.Student;
import telran.students.dto.Subject;
import telran.students.service.interfaces.StudentsService;

@Component
public class StudentsSubjectsMarksGeneration {
	static Logger LOG = LoggerFactory.getLogger("generation");
	@Value("${app.generation.create:false}") 
	boolean needCreateDB;
	@Value("${app.generation.amount: 100}")
	int nMarks;
	@Autowired
	StudentsService studentsService;
	String names[] = { "Abraham", "Sarah", "Itshak", "Rahel", "Asaf", "Yacob", "Rivka", "Yosef", "Benyanim", "Dan",
			"Ruben", "Moshe", "Aron", "Yehashua", "David", "Salomon", "Nefertity", "Naftaly", "Natan", "Asher" };
	String subjects[] = { "Java core", "Java Technologies", "Spring Data", "Spring Security", "Spring Cloud", "CSS",
			"HTML", "JS", "React", "Material-UI" };

	@PostConstruct
	void createDB() {
		if (needCreateDB) {
			addStudents();
			addSubjects();
			addMarks();
			LOG.info("created {} random marks in DB", nMarks);
		}
		
	}
	private int getRandomNumber(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	private void addMarks() {
		IntStream.range(0, nMarks).forEach(i -> addOneMark());
		
		
		
	}
	private void addOneMark() {
		int stid = getRandomNumber(1, names.length);
		int suid = getRandomNumber(1, subjects.length);
		int mark = getRandomNumber(60, 100);
		studentsService.addMark(new Mark(stid, suid, mark));
	}

	private void addSubjects() {
		IntStream.range(0, subjects.length)
		.forEach(i -> {
			studentsService.addSubject(new Subject(i + 1, subjects[i]));
		});
		
	}

	private void addStudents() {
		IntStream.range(0, names.length)
		.forEach(i -> {
			studentsService.addStudent(new Student(i + 1, names[i]));
		});
		
	}

}
