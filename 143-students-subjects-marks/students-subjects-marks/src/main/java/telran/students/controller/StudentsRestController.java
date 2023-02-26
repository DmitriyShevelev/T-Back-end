package telran.students.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import telran.students.dto.QueryDto;
import telran.students.dto.QueryType;
import telran.students.dto.Student;
import telran.students.service.interfaces.*;

@RestController
@RequestMapping("/students")
public class StudentsRestController {
	@Autowired
	StudentsService studentsService;
	@GetMapping("/subject/mark")
public List<StudentSubjectMark> getStudentSubjectMark(String name, String subject) {
	return studentsService.getMarksStudentSubject(name, subject);
}
	@GetMapping("/best")
	public List<String> getBestStudents(@RequestParam(required = false, defaultValue = "0",
	name="amount")
	int nStudents) {
		
		return nStudents == 0 ? studentsService.getBestStudents() :
			studentsService.getTopBestStudents(nStudents);
	}
	@GetMapping("/best/subject")
	public List<Student> getBestStudentsSubject(@RequestParam(name="amount")int nStudents, String subject ) {
		
		return studentsService.getTopBestStudentsSubject(nStudents, subject);
	}
	@GetMapping("/worst/marks")
	public List<StudentSubjectMark> getMarksOfWorstStudents(@RequestParam (name="amount")int nStudents) {
		return studentsService.getMarksOfWorstStudents(nStudents);
	}
	@GetMapping("/distribution/marks")
	public List<IntervalMarks> getMarksDistribution(int interval) {
		return studentsService.marksDistibution(interval);
	}
	@PostMapping("/query")
	public List<String> getQueryResult(@RequestBody QueryDto queryDto) {
		return queryDto.type == QueryType.JPQL ? studentsService.jpqlQuery(queryDto.query) :
			studentsService.sqlQuery(queryDto.query);
	}
	
}
