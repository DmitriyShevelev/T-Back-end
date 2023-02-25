import java.util.Arrays;

import telran.lang.reflection.Address;
import telran.lang.reflection.Person;

public class ObjectInfoAppl {
public static void main(String[] args) {
	Address ad1 = new Address("Sokolov", "Lod");
	Address ad2 = new Address("Orlozorov", null);
	Person prs1 = new Person("Moshe", 123, ad1);
	Person prs2 = new Person("Kolya", 124, ad2);
	prs1.setPerson(prs2);
	Person prs3 = new Person("Serg", 125, null);
	prs2.setPerson(prs3);
	prs3.setPerson(prs1);
	ObjectInfo.printObjectInfo(prs1);
	System.out.println("------------------\n");
	ObjectInfo.printObjectInfo(prs3);
	System.out.println("------------------\n");
	ObjectInfo.printObjectInfo(prs2);
	System.out.println("------------------\n");
	ObjectInfo.printObjectInfo(new String("Hello"));
	System.out.println("------------------\n");
	ObjectInfo.printObjectInfo(Arrays.asList(1, 2, 3));
	System.out.println("------------------\n");
	ObjectInfo.printObjectInfo(1);
	System.out.println("------------------\n");
	ObjectInfo.printObjectInfo(new Object());
	
}
}
