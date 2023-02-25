import java.util.Arrays;

public class Programmer {

public String name;
public String[] technologies;
public Programmer(String name, String[] technologies) {
		super();
		this.name = name;
		this.technologies = technologies;
	}
@Override
public String toString() {
	return "Programmer [name=" + name + ", technologies=" + Arrays.toString(technologies) + "]";
}
}
