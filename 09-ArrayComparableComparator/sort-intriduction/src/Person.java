
public class Person implements Comparable<Person>{
private int id;
private int age;
public Person(int id, int age) {
	
	this.id = id;
	this.age = age;
}
@Override
public int compareTo(Person o) {
	
	return id - o.id;
}
@Override
public String toString() {
	return "Person [id=" + id + ", age=" + age + "]";
}
public int getId() {
	return id;
}
public int getAge() {
	return age;
}

}
