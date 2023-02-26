package telran.students.jpa.entities;
import javax.persistence.*;

@Entity
@Table(name="marks")
public class MarkJpa {
	
	@Id
	@GeneratedValue
int id;
	int mark;
	@ManyToOne
	StudentJpa student;
	@ManyToOne
	SubjectJpa subject;
	public MarkJpa() {
	}
	public MarkJpa(int mark, StudentJpa student, SubjectJpa subject) {
		super();
		this.mark = mark;
		this.student = student;
		this.subject = subject;
	}
	
}
