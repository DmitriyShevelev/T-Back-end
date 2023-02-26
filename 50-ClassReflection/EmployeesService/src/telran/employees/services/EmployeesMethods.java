package telran.employees.services;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import telran.employees.dto.*;

public interface EmployeesMethods extends Serializable{
EmployeesCodes addEmployee(Employee empl) throws Exception;
EmployeesCodes removeEmployee(long id) throws Exception;
EmployeesCodes updateSalary(long id, int newSalary) throws Exception;
EmployeesCodes updateDepartment(long id, String newDepartment)throws Exception;
Employee getEmployee(long id)throws Exception;
Iterable<Employee> getAllEmployees()throws Exception;
Iterable<Employee> getEmployeesBySalary(int fromInclusive, int toExclusive)throws Exception;
Iterable<Employee> getEmployeesByAge(int fromInclusive, int toExclusive)throws Exception;
Iterable<Employee> getEmployeesByDepartment(String department)throws Exception;
Map<String, Integer> getDepartmentsSalary() throws Exception;//key-department, value-average salary
List<SalaryRangeEmployees> distributionSalary(int interval)throws Exception;//returns distribution as
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
