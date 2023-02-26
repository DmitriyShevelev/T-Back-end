package telran.employees.services;

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

}
