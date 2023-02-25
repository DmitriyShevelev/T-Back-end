package telran.students.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.students.dto.Mark;
import telran.students.dto.Student;
import telran.students.dto.Subject;
import telran.students.service.interfaces.*;
import telran.students.jpa.entities.MarkJpa;
import telran.students.jpa.entities.StudentJpa;
import telran.students.jpa.entities.SubjectJpa;
import telran.students.jpa.repo.*;
@Service
public class StudentsServiceJpa implements StudentsService{
StudentsRepository studentsRepository;
SubjectsRepository subjectsRepository;
MarksRepository marksRepository;
@PersistenceContext
EntityManager em;
@Autowired
	public StudentsServiceJpa(StudentsRepository studentsRepository, SubjectsRepository subjectsRepository,
		MarksRepository marksRepository) {
	
	this.studentsRepository = studentsRepository;
	this.subjectsRepository = subjectsRepository;
	this.marksRepository = marksRepository;
}

	@Override
	public void addStudent(Student student) {
		studentsRepository.save(StudentJpa.build(student));
		
	}

	@Override
	public void addSubject(Subject subject) {
		subjectsRepository.save(SubjectJpa.build(subject));
		
	}

	@Override
	@Transactional 
	public Mark addMark(Mark mark) {
		StudentJpa student = studentsRepository.findById(mark.stid).orElse(null);
		SubjectJpa subject = subjectsRepository.findById(mark.suid).orElse(null);
		if (student != null && subject != null) {
			MarkJpa markJpa = new MarkJpa(mark.mark, student, subject);
			marksRepository.save(markJpa);
			return mark;
		}
		
		return null;
	}

	@Override
	public List<StudentSubjectMark> getMarksStudentSubject(String name, String subject) {
		
		return marksRepository.findByStudentNameAndSubjectSubject(name, subject);
	}

	@Override
	public List<String> getBestStudents() {
		
		return marksRepository.findBestStudents();
	}

	@Override
	public List<String> getTopBestStudents(int nStudents) {
		
		return marksRepository.findTopBestStudents(nStudents);
	}

	@Override
	public List<Student> getTopBestStudentsSubject(int nStudents, String subject) {
		
		return studentsRepository.findTopBestStudentsSubject(nStudents, subject)
				.stream().map(StudentJpa::getStudentDto).toList();
	}

	@Override
	public List<StudentSubjectMark> getMarksOfWorstStudents(int nStudents) {
		
		return marksRepository.findMarksOfWorstStudents(nStudents);
	}

	@Override
	public List<IntervalMarks> marksDistibution(int interval) {
		
		return marksRepository.findMarksDistribution(interval);
	}

	@Override
	public List<String> jpqlQuery(String jpql) {
		Query query = em.createQuery(jpql);
		return getResult(query);
	}

	private List<String> getResult(Query query) {
		List result = query.getResultList();
		if (result.isEmpty()) {
			return Collections.EMPTY_LIST;
		}
		return result.get(0).getClass().isArray() ? multiProjectionRequest(result) :
			simpleRequest(result);
	}

	private List<String> multiProjectionRequest(List<Object[]> result) {
		
		return  result.stream().map(Arrays::deepToString).toList();
	}

	private List<String> simpleRequest(List<Object> result) {
		
		return result.stream().map(Object::toString).toList();
	}

	@Override
	public List<String> nativeQuery(String sql) {
		Query query = em.createNativeQuery(sql);
		return getResult(query);
	}

	@Override
	@Transactional
	public List<Student> removeStudents(int avgMark, int nMarks) {
		List<StudentJpa> listJpa =
				studentsRepository.findStudentsForDeletion(avgMark, nMarks);
		listJpa.forEach(studentsRepository::delete);
		//studentsRepository.deleteStudents(avgMark, nMarks);
		return listJpa.stream().map(StudentJpa::getStudentDto).toList();
	}

}
