package telran.students.jpa.entities;
import javax.persistence.*;

import telran.students.dto.Subject;
@Entity
@Table(name="subjects")
public class SubjectJpa {
	@Id
int suid;
	@Column(nullable = false, unique = true)
String subject;
	public static SubjectJpa build(Subject subject) {
		SubjectJpa res = new SubjectJpa();
		res.subject = subject.subject;
		res.suid = subject.suid;
		return res;
	}
	public Subject getSubjectDto() {
		Subject res = new Subject();
		res.subject = subject;
		res.suid = suid;
		return res;
	}

}
