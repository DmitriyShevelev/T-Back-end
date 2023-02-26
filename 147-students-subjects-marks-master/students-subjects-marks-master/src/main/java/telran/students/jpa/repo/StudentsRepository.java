package telran.students.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.students.dto.Student;
import telran.students.jpa.entities.StudentJpa;

public interface StudentsRepository extends JpaRepository<StudentJpa, Integer> {
	@Query(value="select stid, name from students join marks on stid=student_stid "
			+ "join subjects on suid=subject_suid where subject=:subject "
			+ "group by stid, name order by avg(mark) desc limit :nStudents", nativeQuery=true)
	List<StudentJpa> findTopBestStudentsSubject(@Param("nStudents") int nStudents,
			@Param("subject") String subject);

	@Modifying
	@Query(value = "delete from StudentJpa where stid"
			+ " in (select student.stid from MarkJpa group by student.stid"
			+ " having avg(mark) < :avgMark and count(*) < :nMarks)")
	int deleteStudents(@Param("avgMark") double avgMark, @Param("nMarks") long nMarks);
	
	@Query(value = "select s from StudentJpa s where stid"
			+ " in (select student.stid from MarkJpa group by student.stid"
			+ " having avg(mark) < :avgMark and count(*) < :nMarks)")
	List<StudentJpa> findStudentsForDeletion
	(@Param("avgMark") double avgMark, @Param("nMarks") long nMarks);
}
