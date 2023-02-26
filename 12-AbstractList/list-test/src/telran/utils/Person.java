package telran.utils;

public class Person implements Comparable<Person> {
public Person(int id1, String name) {
		
		id = id1;
		this.name = name;
	}
int id;
String name;
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (!(obj instanceof Person))
		return false;
	Person other = (Person) obj;
	if (id != other.id)
		return false;
	return true;
}
@Override
public int compareTo(Person o) {
	
	return id - o.id;
}
public int getId() {
	return id;
}
public String getName() {
	return name;
}

}
