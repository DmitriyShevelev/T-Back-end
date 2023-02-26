package telran.employees.services;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import telran.employees.dto.*;

public interface EmployeesMethods extends Serializable{
EmployeesCodes addEmployee(Employee empl);
EmployeesCodes removeEmployee(long id);
EmployeesCodes updateSalary(long id, int newSalary);
EmployeesCodes updateDepartment(long id, String newDepartment);
Employee getEmployee(long id);
Iterable<Employee> getAllEmployees();
Iterable<Employee> getEmployeesBySalary(int fromInclusive, int toExclusive);
Iterable<Employee> getEmployeesByAge(int fromInclusive, int toExclusive);
Iterable<Employee> getEmployeesByDepartment(String department);
Map<String, Integer> getDepartmentsSalary() ;//key-department, value-average salary
List<SalaryRangeEmployees> distributionSalary(int interval);//returns distribution as
// list of objects containing range [minSalary, maxSalary)
// and list of employees with salary in that range
//the result list should be sorted in ascending order of minSalary
void save(String fileName) throws Exception;
static EmployeesMethods getEmployees(String filePath) throws Exception{
	
	//In the case "filePath" exists and contains proper data the method
	//return instance of an object with all employees
	//In the case the "filePath" doesn't exist or contains improper data
	//the method throws exception
	
	try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(filePath))) {
		EmployeesMethods res = (EmployeesMethods) reader.readObject();
		return res;
	}
	
}

}
