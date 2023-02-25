package telran.employees.dto;

import java.io.Serializable;

public class UpdateSalaryData implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public long id;
public int salary;
public UpdateSalaryData(long id, int salary) {
	
	this.id = id;
	this.salary = salary;
}

}
