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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", salary=" + salary + ", birthDate=" + birthDate + ", department=" + department
				+ "]";
	}
}
