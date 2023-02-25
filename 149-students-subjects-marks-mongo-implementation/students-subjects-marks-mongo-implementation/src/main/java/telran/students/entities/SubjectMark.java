package telran.students.entities;

public class SubjectMark {
	String subject;
	int mark;
	public SubjectMark(String subject, int mark) {
		this.subject = subject;
		this.mark = mark;
	}
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public String getSubject() {
		return subject;
	}
	@Override
	public String toString() {
		return "SubjectMark [subject=" + subject + ", mark=" + mark + "]";
	}
	
	
}
