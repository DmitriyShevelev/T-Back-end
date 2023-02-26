package telran.employees.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class Employee implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private int salary;
	private LocalDate birthDate;
	private String department;
	public Employee(long id, int salary, LocalDate birthDate,
			String department) {
		
		this.id = id;
		this.salary = salary;
		this.birthDate = birthDate;
		this.department = department;
	}
	public int getSalary() {
		return salary;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public long getId() {
		return id;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
}
