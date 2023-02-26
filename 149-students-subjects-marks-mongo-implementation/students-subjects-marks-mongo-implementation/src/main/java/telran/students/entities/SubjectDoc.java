package telran.students.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="subjects")
public class SubjectDoc {
	@Id
	int suid;
	String subject;
	public SubjectDoc(int suid, String subject) {
		this.suid = suid;
		this.subject = subject;
	}
	public int getSuid() {
		return suid;
	}
	public String getSubject() {
		return subject;
	}
	
	
	

}
