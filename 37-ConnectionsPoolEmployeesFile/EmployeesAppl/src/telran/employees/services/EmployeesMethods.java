package telran.employees.services;

import java.util.List;
import java.util.Map;

import telran.employees.dto.*;

public interface EmployeesMethods {
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

}
