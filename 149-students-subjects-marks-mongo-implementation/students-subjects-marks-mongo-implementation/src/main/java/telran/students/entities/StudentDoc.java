package telran.students.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="students")
public class StudentDoc {
	@Id
	int stid;
	String name;
	List<SubjectMark> marks = new ArrayList<>();
	public StudentDoc(int stid, String name) {
		super();
		this.stid = stid;
		this.name = name;
	}
	public int getStid() {
		return stid;
	}
	public String getName() {
		return name;
	}
	public List<SubjectMark> getMarks() {
		return marks;
	}
	@Override
	public String toString() {
		return "StudentDoc [stid=" + stid + ", name=" + name + ", marks=" + marks + "]";
	}
	
	
	
}
