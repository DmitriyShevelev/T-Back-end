package telran.students.dto;

import java.util.Objects;

public class Student {
public int stid;
public String name;
public Student() {
}
public Student(int stid, String name) {
	super();
	this.stid = stid;
	this.name = name;
}
@Override
public int hashCode() {
	return Objects.hash(name, stid);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Student other = (Student) obj;
	return Objects.equals(name, other.name) && stid == other.stid;
}

}
