package telran.students.service.interfaces;

import java.util.List;

import telran.students.dto.*;

public interface StudentsService {
void addStudent(Student student);
void addSubject(Subject subject);
Mark addMark(Mark mark);
List <StudentSubjectMark> getMarksStudentSubject(String name, String subject);
List<String> getBestStudents();//returns names of students having average mark greater than average mark of all students
List<String> getTopBestStudents(int nStudents); //returns names of nStudents best students
List <Student> getTopBestStudentsSubject(int nStudents, String subject); 
List<StudentSubjectMark> getMarksOfWorstStudents(int nStudents); //returns data about marks for nStudents worst student
List<IntervalMarks> marksDistibution(int interval);
List<String> jpqlQuery(String jpql);
List<String> sqlQuery(String sql);
}
