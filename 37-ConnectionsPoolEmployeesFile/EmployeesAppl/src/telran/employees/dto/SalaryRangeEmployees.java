package telran.employees.dto;

import java.util.List;

public class SalaryRangeEmployees {
public int minSalary; 
public int maxSalary;
public List<Employee> employees;// list of employees with
//salary in range[minSalary(inclusive), maxSalary(exclusive))
public SalaryRangeEmployees(int minSalary, int maxSalary, List<Employee> employees) {
	
	this.minSalary = minSalary;
	this.maxSalary = maxSalary;
	this.employees = employees;
}
}
